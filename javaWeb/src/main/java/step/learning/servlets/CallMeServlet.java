package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dao.CallMeDao;
import step.learning.dto.models.CallMeModel;
import step.learning.services.formParse.FormParseResult;
import step.learning.services.formParse.FormParseService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class CallMeServlet extends HttpServlet {

    private FormParseService formParseService;
    private CallMeDao callMeDao;

    @Inject
    public CallMeServlet(FormParseService formParseService, CallMeDao callMeDao) {
        this.formParseService = formParseService;
        this.callMeDao = callMeDao;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        FormParseResult result = formParseService.parse(req);
        CallMeModel callMeModel = new CallMeModel(result);
        callMeDao.create(callMeModel);

        res.sendRedirect("/javaWeb");
    }

}
