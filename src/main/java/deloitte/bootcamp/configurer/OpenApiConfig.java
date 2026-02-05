package deloitte.bootcamp.configurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Produtos")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de produtos" +
                                "Esta API permite operações CRUD completas (Create, Read, Update, Delete) " +
                                "em produtos, com validações personalizadas e tratamento global de exceções.")
                        .contact(new Contact()
                                .name("Arthur Silveira")
                                .email("arthurmarques2148@gmail.com")
                                .url("https://github.com/warthurzin"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento Local")
                ));
    }
}