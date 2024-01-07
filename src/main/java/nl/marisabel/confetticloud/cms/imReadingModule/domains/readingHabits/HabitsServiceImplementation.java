package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.SimpleDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitsServiceImplementation implements HabitsService {

 private final HabitsRepository habitsRepository;
 private final HabitsMapper mapper;

 @Override
 public HabitsDTO saveHabit(HabitsDTO habitsDTO) {
 return mapper.entityToDto(habitsRepository.save(mapper.dtoToEntity(habitsDTO)));
 }

 @Override
 public boolean deleteHabit(String date) {
  return habitsRepository.existsById(date);
 }

 @Override
 public List<HabitsEntity> getAllHabits() {
  return (List<HabitsEntity>) habitsRepository.findAll();
 }

 @Override
 public Optional<HabitsEntity> getHabit(String date) {
  return habitsRepository.findById(date);
 }
}
