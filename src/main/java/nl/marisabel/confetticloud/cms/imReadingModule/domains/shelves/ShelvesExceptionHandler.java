/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;


import nl.marisabel.confetticloud.cms.exceptions.CustomResponses;
import nl.marisabel.confetticloud.cms.exceptions.books.ShelfNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DuplicateShelfException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShelvesExceptionHandler {


 @ExceptionHandler(ShelfNotFoundException.class)
 public ResponseEntity<CustomResponses> handleNoReadingDataFoundForIsbn(ShelfNotFoundException exception) {
  CustomResponses errorResponse = new CustomResponses(404, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(DuplicateShelfException.class)
 public ResponseEntity<CustomResponses> handleDuplicateShelfException(DuplicateShelfException exception) {
  CustomResponses errorResponse = new CustomResponses(409, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
 }



}
