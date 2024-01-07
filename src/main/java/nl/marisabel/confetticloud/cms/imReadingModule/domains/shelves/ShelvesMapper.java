package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShelvesMapper {


 public ShelvesEntity convertDtoToEntity(ShelvesDTO dto) {
  return ShelvesEntity.builder()
          .id(dto.getId())
          .shelfName(dto.getShelfName())
          .build();
 }


 public ShelvesDTO convertEntityToDto(ShelvesEntity entity) {
  return ShelvesDTO.builder()
          .id(entity.getId())
          .shelfName(entity.getShelfName())
          .build();
 }

 public List<ShelvesDTO> convertEntityListToDtoList(List<ShelvesEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }

}
