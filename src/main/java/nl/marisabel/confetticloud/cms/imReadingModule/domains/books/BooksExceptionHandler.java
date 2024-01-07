/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;


import nl.marisabel.confetticloud.cms.exceptions.CustomResponses;
import nl.marisabel.confetticloud.cms.exceptions.books.DuplicateBookException;
import nl.marisabel.confetticloud.cms.exceptions.books.NoBooksFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BooksExceptionHandler {


 @ExceptionHandler(DuplicateBookException.class)
 public ResponseEntity<CustomResponses> handleDuplicateBookException(DuplicateBookException exception) {
  CustomResponses errorResponse = new CustomResponses(703, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(NoBooksFoundException.class)
 public ResponseEntity<CustomResponses> handleEmptyListException(NoBooksFoundException exception) {
  CustomResponses errorResponse = new CustomResponses(404, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }
}
