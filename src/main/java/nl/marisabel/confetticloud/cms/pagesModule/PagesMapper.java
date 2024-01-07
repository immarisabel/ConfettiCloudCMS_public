package nl.marisabel.confetticloud.cms.pagesModule;

import org.springframework.stereotype.Component;

@Component
public class PagesMapper {
 public PagesDto entityToDto(PagesEntity entity) {
  PagesDto dto = new PagesDto();
  dto.setId(entity.getId());
  dto.setTitle(entity.getTitle());
  dto.setContent(entity.getContent());
  dto.setPublic(entity.isPublic());
  dto.setModifiedDate(entity.getModifiedDate());
  dto.setCreatedDate(entity.getCreatedDate());
  return dto;
 }

 public PagesEntity dtoToEntity(PagesDto dto) {
  PagesEntity entity = new PagesEntity();
  entity.setTitle(dto.getTitle());
  entity.setContent(dto.getContent());
  entity.setPublic(dto.isPublic());
  entity.setModifiedDate(dto.getModifiedDate());
  entity.setCreatedDate(dto.getCreatedDate());
  return entity;
 }
}
