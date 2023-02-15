package com.Dialisis.DialisisPeritoneal.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Dialisis.DialisisPeritoneal.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API KidneyHealth",
                "La API REST del sistema KidneyHealth para pacientes con ERC y que realizan el tratamiento de Dialisis Peritoneal",
                "v1",
                "",
                new Contact("Leider M,Jheyner L y Matilde A.", "", "leideryesidmm@ufps.edu.co"),
                "", "", Collections.emptyList());
    }

}
