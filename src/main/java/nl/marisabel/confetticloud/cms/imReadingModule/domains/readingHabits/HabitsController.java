package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = {"${url.mapping.imreading.v1}/habits/"})
@Tag(name = "Habits", description = "Habits API")
public class HabitsController {

 private final HabitsServiceImplementation habitsService;

 @PostMapping
 public ResponseEntity<HabitsDTO> addReadingGoal(@RequestBody HabitsDTO goalsDTO) {
  return ResponseEntity.ok(habitsService.saveHabit(goalsDTO));
 }

 @GetMapping("{date}")
 public ResponseEntity<Optional<HabitsEntity>> getReadingGoalForYear(@PathVariable String date) {
  return ResponseEntity.ok(habitsService.getHabit(date));
 }


 @GetMapping
 public ResponseEntity<List<HabitsEntity>> getAllHabits() {
  List<HabitsEntity> habits = habitsService.getAllHabits();
  return ResponseEntity.ok(habits);
 }

 @DeleteMapping("{date}")
 public ResponseEntity<String> deleteReadingGoal(@PathVariable String date) {
  boolean deleted = habitsService.deleteHabit(date);
  return deleted ? ResponseEntity.ok("Data deleted.") :
          ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found.");
 }

}


