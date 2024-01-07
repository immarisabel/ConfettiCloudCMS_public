/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData;

import jakarta.persistence.*;
import lombok.*;

import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cms-imreading-reading_data")
public class ReadingDataEntity {

 @Id
 @Column(name = "isbn", nullable = false, unique = true)
 private String bookIsbn;

 private Date startedDate;

 private Date finishedDate;

 private String status;

 private int currentPage;

 private int rating;

 private boolean favorite;

 @OneToOne
 @JoinColumn(name = "isbn", referencedColumnName = "isbn")
 private BooksEntity book;

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();

 }
}
