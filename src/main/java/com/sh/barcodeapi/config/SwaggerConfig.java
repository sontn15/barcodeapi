package com.sh.barcodeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sh.barcodeapi.web.rest"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .version("1.0")
                .license("Apache 2.0")
                .title("Barcode Management Service")
                .description("Barcode Management Service using spring boot to build restful api")
                .contact(new Contact("@Anonymous", "@Not available", "abc123@gmail.com"))
                .build();
    }
}
