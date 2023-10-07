package com.task.wbcfp;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class WbcfpApplication {

    private static final Logger log = LoggerFactory.getLogger(WbcfpApplication.class);
    private final Environment env;

    public WbcfpApplication(Environment env) {
        this.env = env;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {

        //SpringApplication.run(SpringbootRestApiApplication.class, args);
        SpringApplication app = new SpringApplication(WbcfpApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("\n****************************************************************\n\t" +
                        "Application Name: \t'{}' \n\t" +
                        "Application URL: \thttp://localhost:{}\n\t" +
                        "Swagger UI URL: \t{}/swagger-ui/index.html\n" +
                        "****************************************************************",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("task.openapi.dev-url")

        );

    }

}