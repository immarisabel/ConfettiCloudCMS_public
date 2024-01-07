/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksServiceImplementation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LogsServiceImplementation implements LogsService {

 private final DataExistingCheck dataExistingCheck;
 private final LogsRepository logsRepository;
 private final LogsMapper mapper;
 private final BooksServiceImplementation booksService;

 @Override
 public LogsDTO addLogToBook(LogsDTO log) {
  if (!dataExistingCheck.doesIsbnExistsInBooks(log.getIsbn())) {
   throw new BookNotFoundException(log.getIsbn());
  }
  LogsEntity savedLog = logsRepository.save(mapper.dtoToEntity(log));
  return mapper.entityToDto(savedLog);
 }


 @Override
 public List<LogsDTO> getAllLogsForABook(String isbn) {
  List<LogsEntity> logsEntity = logsRepository.findAllByIsbn(isbn);
  return logsEntity.stream()
          .map(mapper::entityToDto)
          .collect(Collectors.toList());
 }


 @Override
 public List<LogsDTO> getAllLogs() {
  List<LogsEntity> logsEntity = (List<LogsEntity>) logsRepository.findAll();
  return logsEntity.stream()
          .map(mapper::entityToDto)
          .collect(Collectors.toList());
 }

 @Override
 public LogsDTO getLogById(Long id) {
  Optional<LogsEntity> optionalEntity = logsRepository.findById(id);
  if (optionalEntity.isPresent()) {
   log.info("Get log by id: " + id + " " + optionalEntity.get());
   return mapper.entityToDto(optionalEntity.get());
  } else {
   throw new IdNotFoundException(id);
  }
 }


 @Override
 public LogsDTO updateLog(Long id, LogsDTO updatedLogsDTO) {
  Optional<LogsEntity> optionalEntity = logsRepository.findById(id);
  if (optionalEntity.isPresent()) {
   LogsEntity entity = optionalEntity.get();
   entity.setDate(updatedLogsDTO.getDate());
   entity.setPage(updatedLogsDTO.getPage());
   entity.setFavorite(updatedLogsDTO.isFavorite());
   entity.setLog(updatedLogsDTO.getLog());
   LogsEntity updatedEntity = logsRepository.save(entity);
   return mapper.entityToDto(updatedEntity);
  } else {
   throw new IdNotFoundException(id);
  }
 }

 @Override
 public boolean deleteLog(Long id) {
  LogsEntity entity = logsRepository.findById(id)
          .orElseThrow(() -> new IdNotFoundException(id));
  logsRepository.delete(entity);
  return true;
 }

 public boolean deleteAllLogsForABook(String isbn) {
  List<LogsEntity> logsEntity = logsRepository.findAllByIsbn(isbn);
  if (logsEntity.isEmpty()) {
   throw new NotFoundException(isbn);
  }
  logsRepository.deleteAllByIsbn(isbn);
  return true;
 }

}
