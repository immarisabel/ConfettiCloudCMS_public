package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingHabits;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cms-imreading-habits")
public class HabitsEntity {


    // Regular expression for the "YYYY-MM-DD" date format
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    @Id
    @Column(name = "habit_date", nullable = false, unique = true)
    @Pattern(regexp = DATE_PATTERN, message = "Invalid date format. Expected: YYYY-MM-DD")
    private String habitDate;


 private boolean habitRead;

}
