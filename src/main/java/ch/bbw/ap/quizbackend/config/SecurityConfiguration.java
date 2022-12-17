package ch.bbw.ap.quizbackend.config;

import ch.bbw.ap.quizbackend.authentication.AuthenticationFilter;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import ch.bbw.ap.quizbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        http.authorizeHttpRequests().requestMatchers( "/game/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET).permitAll();
        http.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/registrate/**", "/login").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }


}
