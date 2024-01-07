package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingGoals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cms-imreading-goals")
public class GoalsEntity {


 @Id
 @Column(name = "year", nullable = false, unique = true)
 private int year;

 private int booksToRead;

 private int booksRead;

}
