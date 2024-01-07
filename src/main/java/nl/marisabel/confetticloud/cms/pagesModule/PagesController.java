/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.pagesModule;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = {"${url.mapping.pages.v1}"})
@RequiredArgsConstructor
@Tag(name = "2.0 Pages Module", description = "manage content for full pages of text and html")
public class PagesController {

 private final PagesServiceImplementation pagesService;


 @PostMapping
 public ResponseEntity<PagesDto> addPage(@RequestBody PagesDto pageRequestBody) {
  PagesDto newPage = pagesService.addPage(pageRequestBody);
  return new ResponseEntity<>(newPage, newPage != null ? HttpStatus.CREATED : HttpStatus.NOT_IMPLEMENTED);
 }

 @GetMapping
 public ResponseEntity<List<PagesDto>> getAllPages() {
  List<PagesDto> pages = pagesService.getAllPages();
  return pages.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pages);
 }



 @GetMapping("/{id}")
 public ResponseEntity<PagesDto> getPageById(@PathVariable Long id) {
  PagesDto page = pagesService.getPageById(id);
  return page != null ? ResponseEntity.ok(page) : ResponseEntity.notFound().build();
 }


 @PutMapping("/{id}")
 public ResponseEntity<PagesDto> updatePage(@PathVariable Long id, @RequestBody PagesDto updatedPageRequestBody) {
  PagesDto updatedPage = pagesService.updatePage(id, updatedPageRequestBody);
  return updatedPage != null ? ResponseEntity.ok(updatedPage) : ResponseEntity.notFound().build();
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deletePage(@PathVariable Long id) {
  boolean deleted = pagesService.deletePage(id);
  if (deleted) {
   return ResponseEntity.ok("Page deleted.");
  } else {
   throw new IdNotFoundException(id);
  }
 }
}

