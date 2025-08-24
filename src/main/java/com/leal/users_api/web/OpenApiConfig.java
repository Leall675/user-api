package com.leal.users_api.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Para Gerenciamento de Usuários")
                        .version("1.0.0")
                        .description("Documentação da API com OpenAPI 3 e SpringDoc"));
    }

}
