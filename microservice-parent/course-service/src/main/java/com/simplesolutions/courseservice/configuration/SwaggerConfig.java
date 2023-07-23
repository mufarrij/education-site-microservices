package com.simplesolutions.courseservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean definitions related to swagger config
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Configuration
@OpenAPIDefinition(info=@Info(title="Course Restful WebService", description = ""))
public class SwaggerConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    // uncomment below lines for API grouping

//    @Bean
//    GroupedOpenApi userApis() {
//        return GroupedOpenApi.builder().group("courses").pathsToMatch("/**/courses/**").build();
//    }
//
//    @Bean
//    GroupedOpenApi authApis() {
//        return GroupedOpenApi.builder().group("auth").pathsToMatch("/**/auth/**").build();
//    }

}