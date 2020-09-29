package com.hhtech.botrack.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhtech.botrack.config.AuthoritiesConstants;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);

    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);// "/home";

        if (response.isCommitted()) {

            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isSuperUser = false;
        boolean isAdmin = false;
        boolean isSupervisor = false;
        boolean isUser = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(AuthoritiesConstants.SUPER_USER)) {
                isSuperUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(AuthoritiesConstants.ADMIN)) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(AuthoritiesConstants.SUPERVISOR)) {
                isSupervisor = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(AuthoritiesConstants.USER)) {
                isUser = true;
                break;
            }
        }

        if (isSuperUser) {
            return "/owner/dashboard";
        } else if (isAdmin) {
            return "/admin/dashboard";
        } else if (isSupervisor) {
            return "/supervisor/dashboard";
        } else if (isUser) {
            return "/user/dashboard";
        } else {
            throw new IllegalStateException();
        }

    }
}
