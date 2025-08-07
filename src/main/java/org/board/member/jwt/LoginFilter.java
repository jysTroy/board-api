package org.board.member.jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.board.global.exceptions.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFilter extends GenericFilterBean {

    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            tokenService.authenticate(request);
        } catch (UnAuthorizedException e) {
            HttpServletResponse  res = (HttpServletResponse) response;
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }
}
