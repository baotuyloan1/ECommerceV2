package com.bnd.ecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author BAO 6/29/2023
 */
@Configuration
public class OpenAPIConfig {

    @Value("${ecommerce.openapi.dev-url}")
    private String devUrl;

    @Value("${ecommerce.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact =  new Contact();
        contact.setEmail("nguyenducbaokey@gmail.com");
        contact.setName("Nguyen Duc Bao");
        contact.setUrl("https://github.com/baotuyloan1");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info().title("Ecommerce API").version("1.0").contact(contact).description("This API for FE Ecommerce").termsOfService("https://www.termsofservicegenerator.net/").license(mitLicense);

    return new OpenAPI()
        .info(info)
        .servers(List.of(devServer, prodServer))
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme(){
        return  new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
    }
}
