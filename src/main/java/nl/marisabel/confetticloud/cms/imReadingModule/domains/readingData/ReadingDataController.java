/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.ReadingDataNotFoundByIsbnException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = {"${url.mapping.imreading.v1}/reading/"})
@Tag(name = "imReading Module:  reading data ", description = "manage the reading data for each book")
public class ReadingDataController {

 private final ReadingDataServiceImplementation readingDataService;

 @PostMapping
 public ResponseEntity<ReadingDataDTO> addReadingData(@RequestBody ReadingDataDTO readingDataRequestBody) {
  ReadingDataDTO newReadingData = readingDataService.addReadingDataToBook(readingDataRequestBody);
  return newReadingData != null ? ResponseEntity.ok(newReadingData) : ResponseEntity.notFound().build();
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<ReadingDataDTO> getAllReadingDataForABookByIsbn(@PathVariable String isbn) {
  return ResponseEntity.ok(readingDataService.getAllReadingDataForABook(isbn));
 }

 @PutMapping("/{isbn}")
 public ResponseEntity<ReadingDataDTO> updateReadingData(@PathVariable String isbn, @RequestBody ReadingDataDTO readingDataRequestBody) {
  return ResponseEntity.ok(readingDataService.updateReadingData(isbn, readingDataRequestBody));
 }
 @DeleteMapping("/{isbn}")
 public ResponseEntity<String> deleteReadingData(@PathVariable String isbn) {
  boolean deleted = readingDataService.deleteReadingData(isbn);
  return deleted ? ResponseEntity.ok("Data deleted.") :
          ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Reading data for ISBN " + isbn + " not found.");
 }
}

