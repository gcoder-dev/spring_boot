package com.code.web.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(
                title = "Sprnig Boot Web API",
                version = "1.0.0",
                description = "This API provides endpoints for managing user products.",
                contact = @Contact(
                        name = "GCoder-dev", email = "getsaiangulorojas@gmail.com", url = "https://www.GCoder.dev")
                ,license = @License(name = "APACHE 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")


        ),
        servers = @Server(url = "http://localhost:8080", description = "Local Server")
)
@Configuration
public class OpenAPIConfig {
}
