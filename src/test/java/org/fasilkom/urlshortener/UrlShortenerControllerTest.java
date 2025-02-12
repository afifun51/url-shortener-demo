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

  @Test
  public void testCustomShortenUrl() {
    UrlShortenerController urlShortenerController = new UrlShortenerController();
    Map<String, String> shortenUrl = urlShortenerController.customShortenUrl("https://www.google.com", "abc");

    Assert.assertNotNull(shortenUrl);
    Assert.assertEquals(shortenUrl.get("abc"), "https://www.google.com");
  }

  @Test
  public void testListUrl() {
    UrlShortenerController urlShortenerController = new UrlShortenerController();
    urlShortenerController.customShortenUrl("https://www.google.com", "abc");

    Map<String, String> shortenUrl = urlShortenerController.listUrl()

    Assert.assertNotNull(shortenUrl);
    Assert.assertEquals(shortenUrl.get("abc"), "https://www.google.com");
  }
}
