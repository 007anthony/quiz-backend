package ch.bbw.ap.quizbackend.authentication;

import ch.bbw.ap.quizbackend.common.CredentialHelper;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import ch.bbw.ap.quizbackend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialHelper credentialHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            UserWithCredentials user = userService.getUserByToken(token);
            if(user != null) {
                Authentication authentication = new PreAuthenticatedAuthenticationToken((User) user, token,
                        Arrays.stream(user.getRoles()).map((role) -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
                authentication.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
