package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import org.springframework.stereotype.Component;

@Component
public class HabitsMapper {

 public HabitsDTO entityToDto(HabitsEntity entity) {
  HabitsDTO dto = new HabitsDTO();
  dto.setHabitDate(entity.getHabitDate());
  dto.setHabitRead(entity.isHabitRead());
  return dto;
 }

 public HabitsEntity dtoToEntity(HabitsDTO dto) {
  HabitsEntity entity = new HabitsEntity();
  entity.setHabitDate(dto.getHabitDate());
  entity.setHabitRead(dto.isHabitRead());
  return entity;
 }
}
