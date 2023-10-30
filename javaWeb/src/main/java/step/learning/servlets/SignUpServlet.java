package step.learning.servlets;

import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import step.learning.dto.models.SignUpModelForm;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object regData = session.getAttribute("reg-data");

        if (regData != null) {
            session.removeAttribute("reg-data");
            req.setAttribute("reg-data", regData);
        }

        req.setAttribute("page-body", "signUp.jsp");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {

            HttpSession session = req.getSession();

            System.out.println(req.getParameter("email"));
            System.out.println(req.getParameter("password"));


            SignUpModelForm model = new SignUpModelForm(req);
            model.saveFile(req);

            session.setAttribute("reg-data", model.validate());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        res.sendRedirect(req.getRequestURI());
    }

}
