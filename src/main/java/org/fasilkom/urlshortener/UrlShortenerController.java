package org.fasilkom.urlshortener;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
  public Map<String, String> customShortenUrl(@RequestParam String longUrl, @RequestParam String customUrl) {
    urlStorage.put(customUrl, longUrl);
    return Map.of("shortUrl", customUrl);
  }

  @PostMapping("/list-url")
  public Map<String, String> listUrl() {
    return urlStorage;
  }
  
}

@ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
class UrlNotFoundException extends RuntimeException {
  public UrlNotFoundException(String message) {
    super(message);
  }
}
