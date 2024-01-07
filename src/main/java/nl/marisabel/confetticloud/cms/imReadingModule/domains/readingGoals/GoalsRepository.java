package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository  extends CrudRepository<GoalsEntity, Integer> {

}
