package com.joe2shi.siamese.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("Siamese Auth Service", "/siamese-auth/swagger.json?group=siamese-auth"));
        resources.add(swaggerResource("Siamese File Service", "/siamese-file/swagger.json?group=siamese-file"));
        resources.add(swaggerResource("Siamese User API", "/siamese-user-interface/swagger.json?group=siamese-user-interface"));
        resources.add(swaggerResource("Siamese User Service", "/siamese-user-service/swagger.json?group=siamese-user-service"));
        resources.add(swaggerResource("Siamese Item API", "/siamese-item-interface/swagger.json?group=siamese-item-interface"));
        resources.add(swaggerResource("Siamese Item Service", "/siamese-item-service/swagger.json?group=siamese-item-service"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String url) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(url);
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }
}
