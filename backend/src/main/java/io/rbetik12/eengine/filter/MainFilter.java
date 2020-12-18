package io.rbetik12.eengine.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MainFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getRequestURI().equals("/api/auth/create") || req.getRequestURI().equals("/api/auth/signIn")) {
            chain.doFilter(request, response);
        }
        else {
            HttpSession session = req.getSession(false);
            if (session == null) {
                res.setStatus(403);
                res.getWriter().println("No active session is found!");
                return;
            }
            if (session.getAttribute("Id") != null) {
                chain.doFilter(request, response);
            }
            else {
                res.setStatus(403);
                res.getWriter().println("Incorrect session!");
            }
        }
    }
}
