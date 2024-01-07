/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DataExistingCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = {"${url.mapping.imreading.v1}/reviews/"})
@Tag(name = "imReading Module: reviews", description = "manage the review for each book")
public class ReviewsController {

 private final ReviewsServiceImplementation reviewsService;
 private final DataExistingCheck dataExistingCheck;


 @PostMapping("/{isbn}")
 public ResponseEntity<ReviewsDTO> addReview(@RequestBody ReviewsDTO reviewRequestBody) {
  reviewsService.addReviewToBook(reviewRequestBody);
  return new ResponseEntity<>(reviewRequestBody, HttpStatus.CREATED);
 }

 @PutMapping("/{isbn}")
 public ResponseEntity<ReviewsDTO> updateReview(@PathVariable String isbn, @RequestBody ReviewsDTO updatedReviewRequestBody) {
  ReviewsDTO updatedReview = reviewsService.updateReview(isbn, updatedReviewRequestBody);
  if (Objects.nonNull(updatedReview)) {
   return ResponseEntity.ok(updatedReview);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<ReviewsDTO> getReviewForABook(@PathVariable String isbn) {
  ReviewsDTO review = reviewsService.getReviewForABook(isbn);
  return ResponseEntity.ok(review);
 }


@GetMapping("/all")
public ResponseEntity<List<ReviewsDTO>> getAllReviews() {
 List<ReviewsDTO> reviews = reviewsService.getAllReviews();
 return reviews.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reviews);
}

 @DeleteMapping("/{isbn}")
 public ResponseEntity<String> deleteReview(@PathVariable String isbn) {
  boolean deleted = reviewsService.deleteReview(isbn);
  String message = deleted ? "Review deleted." : "Deleting Failed: Review for book " + isbn + " not found.";
  return ResponseEntity.ok(message);
 }

}

