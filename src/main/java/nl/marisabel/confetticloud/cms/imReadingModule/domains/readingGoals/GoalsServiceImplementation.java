package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalsServiceImplementation implements GoalsService {

 private final GoalsRepository goalsRepository;
 private final GoalsMapper mapper;

 @Override
 public GoalsDTO saveReadingGoal(GoalsDTO goalsDTO) {
 return mapper.entityToDto(goalsRepository.save(mapper.dtoToEntity(goalsDTO)));
 }

 @Override
 public boolean deleteReadingGoal(int year) {
  return goalsRepository.existsById(year);
 }

 @Override
 public Optional<GoalsEntity> getGoalForYear(int year) {
  return goalsRepository.findById(year);
 }
}
