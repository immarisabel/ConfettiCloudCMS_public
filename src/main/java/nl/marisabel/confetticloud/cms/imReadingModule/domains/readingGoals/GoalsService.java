package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import java.util.Optional;

public interface GoalsService {

 GoalsDTO saveReadingGoal(GoalsDTO goalsDTO) ;

 boolean deleteReadingGoal(int year);


 Optional<GoalsEntity> getGoalForYear(int year);

}
