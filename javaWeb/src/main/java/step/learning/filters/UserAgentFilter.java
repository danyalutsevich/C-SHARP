package step.learning.filters;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import com.github.ua_parser.Parser;
//import com.github.ua_parser.UserAgent;
//import com.github.ua_parser.UserAgentParser;


@Singleton
public class UserAgentFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String userAgent = req.getHeader("User-Agent");
        System.out.println(userAgent);

        req.setAttribute("user-agent", userAgent);


//        Parser parser = new Parser();
//        UserAgent userAgent = parser.parse(user_agent_string);
//
//        // Extract browser, OS, and device information
//        String browserName = userAgent.family;
//        String osName = userAgent.os.family;
//        String deviceType = userAgent.device.family;


        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {
        this.filterConfig = null;
    }
}
