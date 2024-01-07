package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.books.ShelfNotFoundException;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsRepository;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class BooksMapper {

 private final ShelvesRepository shelvesRepository;
 private final BooksRepository booksRepository;
 private final ReadingDataRepository readingDataRepository;
 private final ReviewsRepository reviewsRepository;

 public BooksEntity dtoToEntity(BooksDTO book) {
    List<ShelvesEntity> shelfEntities = book.getShelves().stream()
            .map(shelfId -> shelvesRepository.findById(shelfId)
                    .orElseThrow(() -> new ShelfNotFoundException(shelfId)))
            .collect(Collectors.toList());

  ReadingDataEntity readingDataEntity = null;
  if (book.getReadingData() != null) {
   readingDataEntity = ReadingDataEntity.builder()
           .bookIsbn(book.getIsbn())
           .startedDate(book.getReadingData().getStartedDate())
           .finishedDate(book.getReadingData().getFinishedDate())
           .status(book.getReadingData().getStatus())
           .currentPage(book.getReadingData().getCurrentPage())
           .rating(book.getReadingData().getRating())
           .favorite(book.getReadingData().isFavorite())
           .build();

   // Save the new ReadingDataEntity to get its ID
   readingDataEntity = readingDataRepository.save(readingDataEntity);
  }

  ReviewsEntity reviewsEntity = null;
  if (book.getReviews() != null) {
   reviewsEntity = ReviewsEntity.builder()
           .bookIsbn(book.getIsbn())
           .date(book.getReviews().getDate())
           .review(book.getReviews().getReview())
           .build();

   // Save the new ReviewsEntity to get its ID
   reviewsEntity = reviewsRepository.save(reviewsEntity);
  }


// Set the logs for the book
  BooksEntity bookEntity = BooksEntity.builder()
          .isbn(book.getIsbn())
          .title(book.getTitle())
          .author(book.getAuthor())
          .pages(book.getPages())
          .thumbnailUrl(book.getThumbnailUrl())
          .selfLink(book.getSelfLink())
          .shelves(shelfEntities)
          .reviews(reviewsEntity)
          .readingData(readingDataEntity)
          .build();

  return booksRepository.save(bookEntity);
 }

 public BooksDTO entityToDto(BooksEntity book) {
  List<Long> shelvesEntities = book.getShelves().stream()
          .map(ShelvesEntity::getId)
          .collect(Collectors.toList());

  ReviewsDTO reviewsDTO = book.getReviews() != null ? ReviewsDTO.builder()
          .bookIsbn(book.getReviews().getBookIsbn())
          .date(book.getReviews().getDate())
          .review(book.getReviews().getReview())
          .build() : null;

  ReadingDataDTO readingDataDTO = book.getReadingData() != null ? ReadingDataDTO.builder()
          .bookIsbn(book.getReadingData().getBookIsbn())
          .startedDate(book.getReadingData().getStartedDate())
          .finishedDate(book.getReadingData().getFinishedDate())
          .status(book.getReadingData().getStatus())
          .currentPage(book.getReadingData().getCurrentPage())
          .rating(book.getReadingData().getRating())
          .favorite(book.getReadingData().isFavorite())
          .build() : null;

  return BooksDTO.builder()
          .isbn(book.getIsbn())
          .title(book.getTitle())
          .author(book.getAuthor())
          .pages(book.getPages())
          .thumbnailUrl(book.getThumbnailUrl())
          .selfLink(book.getSelfLink())
          .shelves(shelvesEntities)
          .reviews(reviewsDTO)
          .readingData(readingDataDTO)
          .build();
 }

 public List<BooksDTO> convertEntityListToDtoList(List<BooksEntity> entityList) {
  return entityList.stream()
          .map(this::entityToDto)
          .collect(Collectors.toList());
 }
}
