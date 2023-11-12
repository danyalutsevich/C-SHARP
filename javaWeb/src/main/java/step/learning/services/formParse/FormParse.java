package step.learning.services.formParse;

import javax.servlet.http.HttpServletRequest;

public interface FormParse {
    FormParseResult parse(HttpServletRequest request);

}
