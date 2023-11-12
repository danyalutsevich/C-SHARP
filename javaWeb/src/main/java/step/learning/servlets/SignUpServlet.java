package step.learning.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import step.learning.dao.UserDao;
import step.learning.dto.entities.User;
import step.learning.dto.models.SignUpModelForm;
import step.learning.services.formParse.FormParse;
import step.learning.services.formParse.FormParseResult;
import step.learning.services.formParse.FormParseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Singleton
@MultipartConfig(
        fileSizeThreshold = 0,
        maxFileSize = 5242880,       // 5 MB
        maxRequestSize = 20971520)
public class SignUpServlet extends HttpServlet {

    public final UserDao userDao;
    public final FormParse formParseService;
    private final static Gson gson = new GsonBuilder().serializeNulls().create();

    @Inject
    public SignUpServlet(UserDao userDao, FormParse formParseService) {
        this.userDao = userDao;
        this.formParseService = formParseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object regData = session.getAttribute("reg-data");
        Object message = session.getAttribute("reg-message");

        if (regData != null) {
            session.removeAttribute("reg-data");
            session.removeAttribute("reg-message");
            req.setAttribute("reg-data", regData);
            req.setAttribute("reg-message", message);
        }

        req.setAttribute("page-body", "signUp.jsp");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            System.out.println(req.getParameter("email"));
            System.out.println(req.getParameter("password"));

            FormParseResult result = formParseService.parse(req);

            SignUpModelForm model = new SignUpModelForm(result);
            String message = userDao.register(model);


            HttpSession session = req.getSession();
            session.setAttribute("reg-data", model.validate());
            session.setAttribute("reg-message", message);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        res.sendRedirect(req.getRequestURI());
    }

}
