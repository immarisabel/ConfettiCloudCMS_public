/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = {"${url.mapping.imreading.v1}/logs/"})
@Tag(name = "imReading Module:  logs ", description = "manage the logs for each book")
public class LogsController {

 private final LogsServiceImplementation logsService;



 @PostMapping
 public ResponseEntity<LogsDTO> addLog(@RequestBody LogsDTO logRequestBody) {
  LogsDTO newLog = logsService.addLogToBook(logRequestBody);
  return newLog != null ? ResponseEntity.ok(newLog) : ResponseEntity.notFound().build();
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<List<LogsDTO>> getAllLogsForABookByIsbn(@PathVariable String isbn) {
  List<LogsDTO> logs = logsService.getAllLogsForABook(isbn);
  return logs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(logs);
 }

@GetMapping
 public ResponseEntity<List<LogsDTO>> getAllLogs() {
  List<LogsDTO> logs = logsService.getAllLogs();
  return logs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(logs);
 }

 @GetMapping("/log/{id}")
 public ResponseEntity<LogsDTO> getLogById(@PathVariable Long id) {
  LogsDTO log = logsService.getLogById(id);
  return log != null ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
 }

 @PutMapping("/{id}")
 public ResponseEntity<LogsDTO> updateLog(@PathVariable Long id, @RequestBody LogsDTO updatedLogRequestBody) {
  LogsDTO updateLog = logsService.updateLog(id, updatedLogRequestBody);
  return updateLog != null ? ResponseEntity.ok(updateLog) : ResponseEntity.notFound().build();
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteLog(@PathVariable Long id) {
  boolean deleted = logsService.deleteLog(id);
  return deleted ? ResponseEntity.ok("Log deleted.") : ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Deleting Failed: Log with ID " + id + " not found.");
 }

 @DeleteMapping("/delete/{isbn}")
 public ResponseEntity<String> deleteAllLogsForABook(@PathVariable String isbn) {
  if (logsService.deleteAllLogsForABook(isbn)) {
   return ResponseEntity.ok("Log deleted.");
  } else {
   return ResponseEntity.status(HttpStatus.NOT_FOUND)
           .body("Deleting Failed: Log with ISBN " + isbn + " not found.");
  }
 }
}

