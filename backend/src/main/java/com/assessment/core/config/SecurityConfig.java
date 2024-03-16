package com.assessment.core.config;

import com.assessment.core.dto.generic.GenericErrorRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/actuator/**").permitAll().requestMatchers("/swagger-ui/**","/swagger-ui.html", "/v3/api-docs/**","/v3/api-docs").permitAll().requestMatchers("/**").authenticated()).exceptionHandling(ele -> ele.authenticationEntryPoint(failureHandler()))           .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(Customizer.withDefaults())
        );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationEntryPoint failureHandler() {
        return (request, response, ex) -> {
            log.error("Exception happened in failure handler", ex);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ServletOutputStream out = response.getOutputStream();
            new ObjectMapper().writeValue(out, new GenericErrorRes("HTTP-403", "Unable to authenticate the API call"));
            out.flush();
        };
    }


}
