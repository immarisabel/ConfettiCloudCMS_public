package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Goals Model")
public class GoalsDTO {

 private int year;

 private int booksToRead;

 private int booksRead;
}
