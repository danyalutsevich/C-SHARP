package step.learning.dto.models;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;

import step.learning.services.parse.*;

public class SignUpModelForm {

    public SignUpModelForm(HttpServletRequest req) throws ServletException, IOException {
        this.email = (String) req.getParameter("email");
        this.password = (String) req.getParameter("password");
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

    public void saveFile(HttpServletRequest req) throws ServletException, IOException {

        String uploadPath = "C:\\Users\\luche\\Desktop\\static\\";
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> fileItems = sf.parseRequest(req);
            for (FileItem item : fileItems) {
                if (item.getFieldName().equals("avatar")) {
                    String fileExtension = FileExtension.getFileExtension(item.getContentType());
                    if (fileExtension == null) {
                        throw new MissingFormatArgumentException("Invalid file format");
                    }
                    String avatarFileName = uploadPath + UUID.randomUUID() + fileExtension;
                    this.avatar = avatarFileName;
                    item.write(new File(avatarFileName));
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
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
