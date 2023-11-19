package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.filters.UserAgentFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(UserAgentFilter.class);

        serve("/").with(HomeServlet.class);
        serve("/filters").with(FiltersServlet.class);
        serve("/ioc").with(IocServlet.class);
        serve("/jsp").with(JspServlet.class);
        serve("/signup").with(SignUpServlet.class);
        serve("/db").with(DbServlet.class);
        serve("/auth/signin").with(AuthServlet.class);
        serve("/callme").with(CallMeServlet.class);

        serveRegex("/\\w\\w/signup").with(SignUpServlet.class);
    }

}
