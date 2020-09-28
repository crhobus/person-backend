package br.com.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
 * Para acessar via browser utilizar o seguinte endereço: http://localhost:8084/swagger-ui/ (http://localhost:8084/v2/api-docs)
 */
@Configuration
public class SwaggerConfig {

    /*
     * Informa ao Swagger como ele irá extrair as informações das APIs da aplicação
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select() //
                .apis(RequestHandlerSelectors.basePackage("br.com.app.controller")) // Define o pacote onde se encontra as APIs 
                .paths(PathSelectors.any()) // Realiza qualquer tipo de busca
                .build() //
                .apiInfo(apiInfo());
    }

    /*
     * Documentação da parte gráfica
     */
    public ApiInfo apiInfo() {
        return new ApiInfo("RESTful API With Spring Boot 2.2.6", //
                           "Some description about your API.", //
                           "v1", //
                           "Terms Of Service Url", //
                           new Contact("Caio Hobus", "www.exemple.com.br", "your_email@gmail.com"), //
                           "License of API", //
                           "License of URL", Collections.emptyList());
    }

}
