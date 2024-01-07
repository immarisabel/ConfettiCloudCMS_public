package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import org.mapstruct.ap.shaded.freemarker.template.SimpleDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitsRepository extends CrudRepository<HabitsEntity, String> {

}
