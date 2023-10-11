package step.learning.servlets;

import com.google.inject.Singleton;
import com.google.inject.name.Named;
import step.learning.services.hash.HashService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Singleton
public class IocServlet extends HttpServlet {

    private final HashService hashService;

    @Inject
    public IocServlet(@Named("Digest-hash")HashService hashService){
        this.hashService = hashService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        req.setAttribute("hash", this.hashService.hash("123"));
        req.setAttribute("page-body", "ioc.jsp");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, res);
    }
}
