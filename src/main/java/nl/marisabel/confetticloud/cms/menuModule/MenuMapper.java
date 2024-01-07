package nl.marisabel.confetticloud.cms.menuModule;

import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

 public MenuDto entityToDto(MenuEntity entity) {
  MenuDto dto = new MenuDto();
  dto.setId(entity.getId());
  dto.setName(entity.getName());
  dto.setUrl(entity.getUrl());
  dto.setShowInMenu(entity.isShowInMenu());
  dto.setDisplayOrder(entity.getDisplayOrder());
  return dto;
 }

 public MenuEntity dtoToEntity(MenuDto dto) {
  MenuEntity entity = new MenuEntity();
  entity.setId(dto.getId());
  entity.setName(dto.getName());
  entity.setUrl(dto.getUrl());
  entity.setShowInMenu(dto.isShowInMenu());
  entity.setDisplayOrder(dto.getDisplayOrder());
  return entity;
 }
}
