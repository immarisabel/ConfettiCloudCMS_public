/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData;


import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.ReadingDataNotFoundByIsbnException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.NothingFoundWithIsbnException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadingDataServiceImplementation implements ReadingDataService {

 private final ReadingDataRepository readingDataRepository;
 private final DataExistingCheck dataExistingCheck;
private final ReadingDataMapper mapper;

 @Override
 public ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingData) {
  if (!dataExistingCheck.doesIsbnExistsInBooks(readingData.getBookIsbn())) {
   throw new BookNotFoundException(readingData.getBookIsbn());
  }
  ReadingDataEntity newReadingData = mapper.dtoToEntity(readingData);
  ReadingDataEntity savedReadingData = readingDataRepository.save(newReadingData);
  return mapper.entityToDto(savedReadingData);
 }

 @Override
 public ReadingDataDTO getAllReadingDataForABook(String isbn) {
  return readingDataRepository.findById(isbn)
          .map(mapper::entityToDto)
          .orElseThrow(() -> new NothingFoundWithIsbnException(isbn));
 }

 @Override
 public ReadingDataDTO updateReadingData(String isbn, ReadingDataDTO readingDataToUpdate) {
  ReadingDataEntity readingData = readingDataRepository.findById(isbn)
          .orElseThrow(() -> new ReadingDataNotFoundByIsbnException(isbn));

  readingData.setStartedDate(readingDataToUpdate.getStartedDate());
  readingData.setFinishedDate(readingDataToUpdate.getFinishedDate());
  readingData.setStatus(readingDataToUpdate.getStatus());
  readingData.setCurrentPage(readingDataToUpdate.getCurrentPage());
  readingData.setRating(readingDataToUpdate.getRating());
  readingData.setFavorite(readingDataToUpdate.isFavorite());

  return mapper.entityToDto(readingDataRepository.save(readingData));
 }

 @Override
 public boolean deleteReadingData(String isbn) {
  return readingDataRepository.findById(isbn)
          .map(readingData -> {
           readingDataRepository.delete(readingData);
           return true;
          })
          .orElseThrow(() -> new ReadingDataNotFoundByIsbnException(isbn));
 }


}
