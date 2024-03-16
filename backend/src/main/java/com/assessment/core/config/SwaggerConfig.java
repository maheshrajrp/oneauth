package com.assessment.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String tokenUrl;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String authorizationUrl;

    @Value("${app.core.environment}")
    private String env;


    @Bean
    public OpenAPI configuredOpenAPI() {
        String title = String.format("Core API %s", env);
        String description = "Swagger for Customers Core";
        String version = "0.01";
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes("Authentication", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .description("Authentication")
                                )
                ).security(Collections.singletonList(
                        new SecurityRequirement().addList("Authentication")
                ))
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                );
    }
}
