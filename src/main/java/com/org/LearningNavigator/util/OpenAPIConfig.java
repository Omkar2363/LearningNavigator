package com.org.LearningNavigator.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Learning Navigator API",
                version = "1.0",
                description = "API for Learning Navigator Application",
                termsOfService = "http://example.com/terms/",
                contact = @Contact(
                        name = "Support Team",
                        email = "support@example.com",
                        url = "http://example.com/support"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://springdoc.org"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenAPIConfig {
}
