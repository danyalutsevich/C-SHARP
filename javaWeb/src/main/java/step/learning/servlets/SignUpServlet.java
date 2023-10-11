package step.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.ServerError;

@Singleton
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String regData = (String) session.getAttribute("reg-data");

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
        session.setAttribute("reg-data", "form processed");
        res.sendRedirect(req.getRequestURI());
    }

}
