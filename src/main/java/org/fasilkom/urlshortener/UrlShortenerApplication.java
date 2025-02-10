package org.fasilkom.urlshortener;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@OpenAPIDefinition(
    info = @Info(
        title = "URL Shortener API",
        version = "1.0",
        description = "Spring Boot API for shortening and retrieving URLs"
    )
)
@SpringBootApplication
public class UrlShortenerApplication {
  public static void main(String[] args) {
    SpringApplication.run(UrlShortenerApplication.class, args);
  }
}
