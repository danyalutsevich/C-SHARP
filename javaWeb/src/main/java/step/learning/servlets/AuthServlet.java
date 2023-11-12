package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dao.UserDao;
import step.learning.services.auth.JwtAuth;
import step.learning.services.formParse.FormParseResult;
import step.learning.services.formParse.FormParseService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Singleton
public class AuthServlet extends HttpServlet {

    private final UserDao userDao;
    private final FormParseService formParseService;

    @Inject
    public AuthServlet(UserDao userDao, FormParseService formParseService) {
        this.userDao = userDao;
        this.formParseService = formParseService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {

        FormParseResult parseResult = formParseService.parse(req);
        Map<String, String> fields = parseResult.getFields();
        String email = fields.get("email");
        String password = fields.get("password");

        //        String email = (String) req.getParameter("email");
        //        String password = (String) req.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        String jwt = userDao.login(email, password);
        System.out.println(jwt);

        res.setHeader("Authorization", jwt);
    }

}
