package org.fasilkom.urlshortener;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
class UrlShortenerController {
  private final Map<String, String> urlStorage = new HashMap<>();
}

@ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
class UrlNotFoundException extends RuntimeException {
  public UrlNotFoundException(String message) {
    super(message);
  }
}
