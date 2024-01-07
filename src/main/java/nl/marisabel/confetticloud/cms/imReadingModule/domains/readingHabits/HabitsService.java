package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import org.mapstruct.ap.shaded.freemarker.template.SimpleDate;

import java.util.List;
import java.util.Optional;

public interface HabitsService {

 HabitsDTO saveHabit(HabitsDTO habitsDTO) ;

 boolean deleteHabit(String date);

 List<HabitsEntity> getAllHabits();

 Optional<HabitsEntity> getHabit(String date);

}
