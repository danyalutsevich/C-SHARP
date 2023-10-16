package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.dto.models.SignUpModelForm;

import javax.servlet.ServletException;
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
import java.util.Set;

@Singleton
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();

        System.out.println(req.getQueryString());
        System.out.println(req.getAttribute("reg-login"));
        System.out.println(req.getParameter("reg-login"));

        SignUpModelForm model = new SignUpModelForm(req);


        session.setAttribute("reg-data", model.validate());
        res.sendRedirect(req.getRequestURI());
    }

}
