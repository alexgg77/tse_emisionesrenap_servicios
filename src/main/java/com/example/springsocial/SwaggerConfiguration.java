package com.example.springsocial;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration   {
     
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        			.ignoredParameterTypes(AuthenticationPrincipal.class)
        			.select()
        			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
        			.paths(PathSelectors.any()).build()
        			.securitySchemes(Lists.newArrayList(apiKey()))
        			.securityContexts(Arrays.asList(securityContext()))
        			.apiInfo(getApiInfo())
        			.tags(
        					new Tag("Auth","Metodo para validacion de credencias",1)
        			);
        
    }
    
    private ApiKey apiKey() {
    	return new ApiKey("apiKey", "Authorization", "header");
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
            .forPaths(PathSelectors.any()).build();
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
            "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("apiKey",
            authorizationScopes));
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfoBuilder()
    			.title("TSE - RENAP - SERVICIO RECEPCION FALLECIDOS")
    			.version("1.0.0")
    			.description("DOCUMENTACIÓN DE LOS CONTROLADORES QUE SE UTILIZAN PARA EL SERVICIO DE RECEPCION DE REPORTES DE FALLECIDOS, INCLUYE METODOS, EJEMPLOS DE MODELOS REQUERIDOS Y TIPOS DE RESPUESTA")
    			.contact(new Contact("TSE","https://tse.org.gt","admin@tse.org.gt"))
    			.license("SPRING BOOT")
    			.build();
    }
}