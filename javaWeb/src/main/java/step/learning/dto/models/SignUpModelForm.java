package step.learning.dto.models;


import javax.servlet.http.HttpServletRequest;
import java.util.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;

public class SignUpModelForm {

    public SignUpModelForm(HttpServletRequest req) {
        this.email = (String) req.getParameter("email");
        this.password = (String) req.getParameter("password");
//        this.birthDate = new Date(req.getParameter("date"));
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

    @NotNull(message = "Email is null")
    @NotBlank(message = "Email is blank")
    @Email(message = "Email is Invalid")
    public String email;
    @NotNull(message = "Password is null")
    @NotBlank(message = "Password is blank")
    @Size(message = "Password is too small min 6 symbols", min = 6)
    public String password;
    public Date birthDate;


}
