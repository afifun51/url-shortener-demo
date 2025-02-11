package org.fasilkom.urlshortener;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
