/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions;


import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.ReadingDataNotFoundByIsbnException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.NothingFoundWithIsbnException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


 @ExceptionHandler(BookNotFoundException.class)
 public ResponseEntity<CustomResponses> handleBookNotFoundException(BookNotFoundException exception) {
  CustomResponses errorResponse = new CustomResponses(404, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(IdNotFoundException.class)
 public ResponseEntity<CustomResponses> handleIDNotFoundException(IdNotFoundException exception) {
  CustomResponses errorResponse = new CustomResponses(404, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(NothingFoundWithIsbnException.class)
 public ResponseEntity<CustomResponses> handleNoReadingDataFoundForIsbn(NothingFoundWithIsbnException exception) {
  CustomResponses errorResponse = new CustomResponses(404, exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<CustomResponses> handleGenericException(Exception exception) {
  CustomResponses errorResponse = new CustomResponses(500, "Internal Server Error" + exception.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
