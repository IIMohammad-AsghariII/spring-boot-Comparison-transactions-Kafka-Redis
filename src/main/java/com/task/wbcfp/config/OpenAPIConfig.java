package com.task.wbcfp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

/**
 * This class is OpenAPI  config
 *
 */
@Configuration
public class OpenAPIConfig {
    @Value("${task.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("https://www.linkedin.com/in/tiimohammad-asghariiit/");
        contact.setName("Mohammad Asghari");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("This project compares the received transaction amount field in Kafka with the transaction in Redis")
                .version("1.0")
                .contact(contact)
                .description("This project compares the received transaction amount field in Kafka with the transaction in Redis").termsOfService("https://www.linkedin.com/in/tiimohammad-asghariiit/")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}