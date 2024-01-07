package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = {"${url.mapping.imreading.v1}/goals/"})
@Tag(name = "imReading Module:  reading goals ", description = "manage the reading goals for each year")
public class GoalsController {

 private final GoalsServiceImplementation goalsService;

 @PostMapping
 public ResponseEntity<GoalsDTO> addReadingGoal(@RequestBody GoalsDTO goalsDTO) {
  return ResponseEntity.ok(goalsService.saveReadingGoal(goalsDTO));
 }

 @GetMapping("{year}")
 public ResponseEntity<Optional<GoalsEntity>> getReadingGoalForYear(@PathVariable int year) {
  return ResponseEntity.ok(goalsService.getGoalForYear(year));
 }

 @DeleteMapping("{year}")
 public ResponseEntity<String> deleteReadingGoal(@PathVariable int year) {
  boolean deleted = goalsService.deleteReadingGoal(year);
  return deleted ? ResponseEntity.ok("Data deleted.") :
          ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Reading data for year " + year + " not found.");
 }

}


