package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData;

import org.springframework.stereotype.Component;

@Component
public class ReadingDataMapper {


 public ReadingDataDTO entityToDto(ReadingDataEntity entity) {
  ReadingDataDTO dto = new ReadingDataDTO();
  dto.setBookIsbn(entity.getBookIsbn());
  dto.setStartedDate(entity.getStartedDate());
  dto.setFinishedDate(entity.getFinishedDate());
  dto.setStatus(entity.getStatus());
  dto.setCurrentPage(entity.getCurrentPage());
  dto.setRating(entity.getRating());
  dto.setFavorite(entity.isFavorite());
  return dto;
 }

 public ReadingDataEntity dtoToEntity(ReadingDataDTO dto) {
  ReadingDataEntity entity = new ReadingDataEntity();
  entity.setBookIsbn(dto.getBookIsbn());
  entity.setStartedDate(dto.getStartedDate());
  entity.setFinishedDate(dto.getFinishedDate());
  entity.setStatus(dto.getStatus());
  entity.setCurrentPage(dto.getCurrentPage());
  entity.setRating(dto.getRating());
  entity.setFavorite(dto.isFavorite());
  return entity;
 }
}
