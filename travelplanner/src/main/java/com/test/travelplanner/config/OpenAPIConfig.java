package com.test.travelplanner.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;  
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// http://localhost:8081/swagger-ui.html
@Configuration  
public class OpenAPIConfig {  
    
    @Bean  
    public OpenAPI customOpenAPI() {  
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()  
                        .title("Travel Planner API")
                        .version("1.0")  
                        .description("API 接口文档")  
                        .contact(new Contact()  
                                .name("travel planner")
                                .email("developer@example.com")));  
    }  
}