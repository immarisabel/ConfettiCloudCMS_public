/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions.dataValidation;


import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataExistingCheck {

 private final ReadingDataRepository readingDataRepository;
 private final BooksRepository booksRepository;
 private final ShelvesRepository shelvesRepository;
 private final LogsRepository logsRepository;
 private final ReviewsRepository reviewsRepository;



 public boolean doesIsbnExistsInBooks(String isbn) {
  Optional<BooksEntity> bookIsbn = booksRepository.findById(isbn);
  return bookIsbn.isPresent();
 }


 public boolean doesIsbnExistsInReviews(String isbn) {
  ReviewsEntity bookReview = reviewsRepository.findByIsbn(isbn);
  return bookReview != null;
}


 public boolean doesReadingDataExists(String isbn) {
  Optional<ReadingDataEntity> readingData = readingDataRepository.findById(isbn);
  return readingData.isPresent();
 }


 public boolean doesShelfExist(String shelfName) {
  Optional<ShelvesEntity> shelf = Optional.ofNullable(shelvesRepository.findByName(shelfName));
  return shelf.isPresent();
 }


}
