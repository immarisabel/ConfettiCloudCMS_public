package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;


import org.springframework.stereotype.Component;

@Component
public class LogsMapper {



 public LogsDTO entityToDto(LogsEntity entity) {
  LogsDTO dto = new LogsDTO();
  dto.setId(entity.getId());
  dto.setIsbn(entity.getIsbn());
  dto.setDate(entity.getDate());
  dto.setPage(entity.getPage());
  dto.setFavorite(entity.isFavorite());
  dto.setLog(entity.getLog());
  return dto;
 }

 public LogsEntity dtoToEntity(LogsDTO dto) {
  LogsEntity entity = new LogsEntity();
  entity.setId(dto.getId());
  entity.setIsbn(dto.getIsbn());
  entity.setDate(dto.getDate());
  entity.setPage(dto.getPage());
  entity.setFavorite(dto.isFavorite());
  entity.setLog(dto.getLog());
  return entity;
 }

}
