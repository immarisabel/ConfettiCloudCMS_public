package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Habits Model")
public class HabitsDTO {


    // Regular expression for the "YYYY-MM-DD" date format
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    @Pattern(regexp = DATE_PATTERN, message = "Invalid date format. Expected: YYYY-MM-DD")
    private String habitDate;

 private boolean habitRead;
}
