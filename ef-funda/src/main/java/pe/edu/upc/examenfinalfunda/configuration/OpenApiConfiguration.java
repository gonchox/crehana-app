package pe.edu.upc.examenfinalfunda.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "crehanaOpenApi")
    public OpenAPI crehanaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Crehana Application API")
                        .description("Crehana API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));
    }
}
