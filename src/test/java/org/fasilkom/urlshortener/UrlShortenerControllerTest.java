package org.fasilkom.urlshortener;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test(groups = "small")
public class UrlShortenerControllerTest {

  @Test
  public void testShortenUrl() {
    UrlShortenerController urlShortenerController = new UrlShortenerController();
    Map<String, String> shortenUrl = urlShortenerController.shortenUrl("https://www.google.com");

    Assert.assertNotNull(shortenUrl);
  }
}
