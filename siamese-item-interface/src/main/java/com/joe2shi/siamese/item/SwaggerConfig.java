package com.joe2shi.siamese.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
            .globalOperationParameters(parameters)
            .host("api.joe2shi.com/siamese-item-interface")
            .groupName("siamese-item-interface")
            .useDefaultResponseMessages(Boolean.FALSE)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.joe2shi.siamese.item.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Siamese Item API")
            .description("")
            .version("1.0")
            .build();
    }
}
