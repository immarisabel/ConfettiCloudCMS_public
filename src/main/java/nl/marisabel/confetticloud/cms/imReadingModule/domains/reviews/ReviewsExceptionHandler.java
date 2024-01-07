/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;


import nl.marisabel.confetticloud.cms.exceptions.CustomResponses;
import nl.marisabel.confetticloud.cms.exceptions.books.ReviewAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewsExceptionHandler {


 @ExceptionHandler(ReviewAlreadyExistsException.class)
 public ResponseEntity<CustomResponses> handleEmptyListException(ReviewAlreadyExistsException ex) {
  CustomResponses errorResponse = new CustomResponses(409, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
 }

}
