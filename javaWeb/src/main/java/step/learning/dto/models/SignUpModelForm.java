package step.learning.dto.models;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;

import step.learning.services.formParse.FormParseResult;
import step.learning.services.parse.*;

public class SignUpModelForm {

    private final String[] allowedFileExtensions = {".jpg", ".jpeg", ".png", ".ico", "gif"};

    public SignUpModelForm(FormParseResult res) throws ServletException, IOException {

        Map<String, String> fields = res.getFields();
        this.email = fields.get("email");
        this.password = fields.get("password");
        this.saveAvatar(res);
    }

    public ArrayList<String> validate() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<SignUpModelForm>> violations = validator.validate(this);

        ArrayList<String> messages = new ArrayList<>();
        for (ConstraintViolation<SignUpModelForm> violation : violations) {
            messages.add(violation.getMessage());
        }

        validatorFactory.close();

        return messages.toString().isEmpty() ? new ArrayList<String>() : messages;
    }

    public void saveAvatar(FormParseResult res) throws ServletException, IOException {

        String uploadPath = "C:\\Users\\luche\\Desktop\\static\\";
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        Map<String, FileItem> files = res.getFiles();

        FileItem avatar = files.get("avatar");

        try {
            String fileExtension = FileExtension.getFileExtension(avatar.getContentType());
            if (fileExtension == null) {
                throw new MissingFormatArgumentException("Invalid file format");
            } else if (!Arrays.asList(this.allowedFileExtensions).contains(fileExtension)) {
                throw new InvalidObjectException(fileExtension + " extension is not allowed");
            }
            String avatarFileName = uploadPath + UUID.randomUUID() + fileExtension;
            this.avatar = avatarFileName;
            avatar.write(new File(avatarFileName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @NotNull(message = "Email is null")
    @NotBlank(message = "Email is blank")
    @Email(message = "Email is Invalid")
    public String email;
    @NotNull(message = "Password is null")
    @NotBlank(message = "Password is blank")
    @Size(message = "Password is too small min 6 symbols", min = 6)
    public String password;
    public String avatar;

}
