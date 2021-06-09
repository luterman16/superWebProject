package by.mmarshal.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding params")})
public class MainFilter implements Filter {
    private String code;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)){
            servletRequest.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("text/html");
            servletResponse.setCharacterEncoding("UTF-8");
        }
//        if(servletRequest.getCharacterEncoding()== null){
//                servletRequest.setCharacterEncoding("UTF-8");
//            }

            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
