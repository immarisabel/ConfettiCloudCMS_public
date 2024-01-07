package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import org.springframework.stereotype.Component;

@Component
public class GoalsMapper {

 public GoalsDTO entityToDto(GoalsEntity entity) {
  GoalsDTO dto = new GoalsDTO();
  dto.setYear(entity.getYear());
  dto.setBooksToRead(entity.getBooksToRead());
  dto.setBooksRead(entity.getBooksRead());
  return dto;
 }

 public GoalsEntity dtoToEntity(GoalsDTO dto) {
  GoalsEntity entity = new GoalsEntity();
  entity.setYear(dto.getYear());
  entity.setBooksToRead(dto.getBooksToRead());
  entity.setBooksRead(dto.getBooksRead());
  return entity;
 }
}
