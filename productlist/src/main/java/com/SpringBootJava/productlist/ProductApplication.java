package com.SpringBootJava.productlist;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Product Service REST API documentation",
                description = "Product Service REST API",
                version = "v1",
                contact = @Contact(
                        name = "Krishna Malviya",
                        email = "kkrishnammalviya@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Sharepoint URL Product Service API",
                url = "example.com"

        )
)

@SpringBootApplication
@Slf4j
public class ProductApplication {
//    private static final Logger log = LoggerFactory.getLogger(ProductApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);

        String str = "test";
        log.info("Product Service {} started successfully!", str);
//        log.warn("this is a warning message");
//        log.debug("this is a debug message");
//        log.error("this is an error message");
//        log.trace("this is a trace message");

	}

}
