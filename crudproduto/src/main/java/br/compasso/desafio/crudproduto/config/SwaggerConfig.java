package br.compasso.desafio.crudproduto.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.*;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.compasso.desafio.crudproduto"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {


        ApiInfo apiInfo = new ApiInfo(
                "MICROSERVICE",
                "CRUD PRODUCT - API",
                "1.0",
                "Terms of Service",
                new Contact("Moisés Carlos Ferreira",
                        "https://github.com/eumoisescf7",
                        "moisescarlos7@gmail.com"),
                "Apache Licence Version 2.0",
                "https:/www.apache.org/licence.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}
