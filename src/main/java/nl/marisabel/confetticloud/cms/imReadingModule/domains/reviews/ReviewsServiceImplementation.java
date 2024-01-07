/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;


import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.books.ReviewAlreadyExistsException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.ReadingDataNotFoundByIsbnException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImplementation implements ReviewsService {

 private final ReviewsRepository reviewsRepository;
 private final DataExistingCheck dataExistingCheck;
 private final ReviewsMapper mapper;


 @Override
 public ReviewsDTO addReviewToBook(ReviewsDTO newReview) {
  String bookIsbn = newReview.getBookIsbn();
  boolean isbnExistsInReviews = dataExistingCheck.doesIsbnExistsInReviews(bookIsbn);
  boolean isbnExistsInBooks = dataExistingCheck.doesIsbnExistsInBooks(bookIsbn);

  if (!isbnExistsInReviews) {
   if (isbnExistsInBooks) {
    ReviewsEntity review = mapper.reviewData(newReview);
    ReviewsEntity savedReview = reviewsRepository.save(review);
    return mapper.entityToDto(savedReview);
   } else {
    throw new BookNotFoundException(bookIsbn);
   }
  } else {
   throw new ReviewAlreadyExistsException(bookIsbn);
  }
 }

 @Override
 public ReviewsDTO getReviewForABook(String isbn) {
  return Optional.ofNullable(reviewsRepository.findByIsbn(isbn))
          .map(mapper::entityToDto)
          .orElseThrow(() -> new ReadingDataNotFoundByIsbnException(isbn));
 }

 @Override
 public List<ReviewsDTO> getAllReviews() {
  List<ReviewsEntity> reviews = (List<ReviewsEntity>) reviewsRepository.findAll();
  return reviews.stream()
          .map(mapper::entityToDto)
          .collect(Collectors.toList());
 }

 @Override
 public ReviewsDTO updateReview(String isbn, ReviewsDTO updatedReview) {
  ReviewsEntity reviewData = reviewsRepository.findById(isbn)
          .orElseThrow(() -> new BookNotFoundException(isbn));

  reviewData.setDate(updatedReview.getDate());
  reviewData.setReview(updatedReview.getReview());

  return mapper.entityToDto(reviewsRepository.save(reviewData));
 }

 @Override
 public boolean deleteReview(String isbn) {
  ReviewsEntity entity = reviewsRepository.findById(isbn)
          .orElseThrow(() -> new ReadingDataNotFoundByIsbnException(isbn));
  reviewsRepository.delete(entity);
  return true;
 }


}
