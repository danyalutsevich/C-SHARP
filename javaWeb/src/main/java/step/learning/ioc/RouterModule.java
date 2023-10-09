package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.filters.UserAgentFilter;
import step.learning.servlets.FiltersServlet;
import step.learning.servlets.HomeServlet;
import step.learning.servlets.IocServlet;
import step.learning.servlets.JspServlet;

public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(UserAgentFilter.class);

        serve("/").with(HomeServlet.class);
        serve("/filters").with(FiltersServlet.class);
        serve("/ioc").with(IocServlet.class);
        serve("/jsp").with(JspServlet.class);
    }

}
