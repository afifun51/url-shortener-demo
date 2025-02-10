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

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
class UrlShortenerController {
  private final Map<String, String> urlStorage = new HashMap<>();

  @PostMapping("/shorten")
  public Map<String, String> shortenUrl(@RequestParam String longUrl) {
    String shortUrl = UUID.randomUUID().toString().substring(0, 8);
    urlStorage.put(shortUrl, longUrl);
    return Map.of("shortUrl", shortUrl);
  }

  @PostMapping("/shorten-custom")
  public Map<String, String> shortenUrlCustom(@RequestParam String longUrl, @RequestParam String custom) {
    urlStorage.put(custom, longUrl);
    return Map.of("shortUrl", custom);
  }

  @PostMapping("/urls")
  public Map<String, String> urls() {
    return urlStorage;
  }

  @GetMapping("/{shortUrl}")
  public Map<String, String> getOriginalUrl(@PathVariable String shortUrl) {
    String longUrl = urlStorage.get(shortUrl);
    if (longUrl == null) {
      throw new UrlNotFoundException("Short URL not found");
    }
    return Map.of("longUrl", longUrl);
  }
}

@ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
class UrlNotFoundException extends RuntimeException {
  public UrlNotFoundException(String message) {
    super(message);
  }
}
