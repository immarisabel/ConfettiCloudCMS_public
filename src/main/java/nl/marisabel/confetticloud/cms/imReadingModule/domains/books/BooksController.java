/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.books.NoBooksFoundException;
import nl.marisabel.confetticloud.cms.imReadingModule.customResponse.ResponseHandler;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsServiceImplementation;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsServiceImplementation;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;


@RestController
@Log4j2
@RequestMapping(value = {"${url.mapping.imreading.v1}/books"})
@Tag(name = "imReading Module: Books", description = "manage the books on the database")
@RequiredArgsConstructor
public class BooksController {

 private final BooksServiceImplementation booksService;
 private final ReviewsServiceImplementation reviewsService;
 private final LogsServiceImplementation logsService;
 private final ShelvesServiceImplementation shelvesService;


 @GetMapping
 public ResponseEntity<List<BooksDTO>> getAllBooks() {
  List<BooksDTO> books = booksService.getAllBooks();
  if (books.isEmpty()) {
   throw new NoBooksFoundException(books);
  }
  return ResponseEntity.ok(books);
 }


 @PostMapping
 public ResponseEntity<BooksDTO> createBook(
         @RequestBody BooksDTO bookRequestBody,
         @AuthenticationPrincipal UserPrincipal principal
 ) {
  BooksDTO createdBook = booksService.createBook(bookRequestBody, bookRequestBody.getShelves());
  return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<?> getBookById(@PathVariable String isbn) {
  return ResponseEntity.ok(booksService.getBookByIsbn(isbn));
 }

 @PutMapping("/{isbn}")
 public ResponseEntity<?> updateBook(@PathVariable String isbn,
                                     @RequestBody BooksDTO bookRequestBody) {
  BooksDTO updated = booksService.updateBook(
          isbn,
          bookRequestBody,
          bookRequestBody.getShelves());
  if (updated == null) {
   throw new BookNotFoundException(isbn);
  }
  return ResponseEntity.ok(updated);
 }

 @DeleteMapping("/{isbn}")
 public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
  try {
   log.info("deleting book...");
   BooksDTO book = booksService.getBookByIsbn(isbn);
   if (book != null) {
    booksService.deleteBook(String.valueOf(book.getIsbn()));
    log.info("deleted book...");
    return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, true);
   } else {
    throw new BookNotFoundException(isbn);
   }
  } catch (Exception e) {
   throw new BookNotFoundException(isbn);
  }
 }


}
