/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.imReadingModule.customResponse.ResponseHandler;
import nl.marisabel.confetticloud.cms.exceptions.books.ShelfNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DuplicateShelfException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"${url.mapping.imreading.v1}/shelves"})
@Tag(name = "imReading Module: shelves ", description = "manage the shelves on the database")
@RequiredArgsConstructor
@Log4j2
public class ShelvesController {

 private final ShelvesServiceImplementation shelvesService;


 @PostMapping
 public ResponseEntity<ShelvesDTO> createShelf(@RequestBody ShelvesDTO shelf) {
  if (shelvesService.existByName(shelf.getShelfName())) {
   throw new DuplicateShelfException(shelf.getShelfName());
  }
log.info(shelf.getShelfName());
  ShelvesDTO createdShelf = shelvesService.createShelf(shelf);
  return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<ShelvesDTO> updateShelf(@PathVariable Long id, @RequestBody ShelvesDTO updatedShelf) {
  if (!shelvesService.existsById(updatedShelf.getId())) {
   throw new ShelfNotFoundException(updatedShelf.getId());
  }
  ShelvesDTO updatedShelfDTO = shelvesService.updateShelf(updatedShelf.getId(), updatedShelf);
  return ResponseEntity.ok(updatedShelfDTO);
 }

 @GetMapping("/{shelfName}")
 public ResponseEntity<?> getShelfByName(@PathVariable String shelfName) {
  ShelvesDTO shelf = shelvesService.getShelfByName(shelfName);
  if (shelf == null)    throw new ShelfNotFoundException(shelf.getId());
  return ResponseEntity.ok(shelf);
 }

  @GetMapping("/shelf/{id}")
 public ResponseEntity<?> getShelfById(@PathVariable Long id) {
  ShelvesDTO shelf = shelvesService.getShelfById(id);
  if (shelf == null)
   throw new ShelfNotFoundException(shelf.getId());
  return ResponseEntity.ok(shelf);
 }

 @GetMapping
 public List<ShelvesDTO> getAllShelves() {
  try {
   return shelvesService.getAllShelves();
  } catch (Exception e) {
   throw new RuntimeException("Error retrieving shelves: " + e.getMessage());
  }
 }


 @DeleteMapping("/{name}")
 public ResponseEntity<?> deleteShelf(@PathVariable String name) {
  ShelvesDTO shelf = shelvesService.getShelfByName(name);
  if (shelf != null) {
   shelvesService.deleteShelf(shelf.getId());
   return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, name);
  } else {
   throw new ShelfNotFoundException(shelf.getId());
  }
 }

}
