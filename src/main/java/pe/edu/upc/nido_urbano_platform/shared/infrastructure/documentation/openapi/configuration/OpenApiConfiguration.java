package pe.edu.upc.nido_urbano_platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI NidoUrbanoPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Nido Urbano Platform API")
                        .description("Nido Urbano Platform application REST API documentation.")
                        .version("v1.1.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Nido Urbano Platform Documentation")
                        .url("https://github.com/orgs/SI729-2402-WS51-Grupo-3-Nido-Urbano/repositories"));
        return openApi;
    }
}