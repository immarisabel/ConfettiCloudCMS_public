/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.googleSearchApi;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log
public class GoogleBooksApiService {

 private final RestTemplate restTemplate;
 private final String apiKey;

 public GoogleBooksApiService(RestTemplate restTemplate, @Value("${keys.gBooks}") String apiKey) {
  this.restTemplate = restTemplate;
  this.apiKey = apiKey;
 }

 public String getApiUri(String query) {
  return "https://www.googleapis.com/books/v1/volumes?q=" + query + "&download=epub&key=" + apiKey + "&maxResults=40";
 }

 public String getBookDetailsFromResponse(String url) {
  return restTemplate.getForObject(url, String.class);
 }


}