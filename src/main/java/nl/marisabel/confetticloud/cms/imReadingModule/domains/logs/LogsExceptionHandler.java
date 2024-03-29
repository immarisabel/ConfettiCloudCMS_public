/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import nl.marisabel.confetticloud.cms.exceptions.CustomResponses;
import nl.marisabel.confetticloud.cms.exceptions.books.PageTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LogsExceptionHandler {

 // when page number in log is bigger than amounts of pages a book has

 @ExceptionHandler(PageTooLongException.class)
 public ResponseEntity<CustomResponses> handleEmptyListException(PageTooLongException ex) {
  CustomResponses errorResponse = new CustomResponses(711, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
 }


}
