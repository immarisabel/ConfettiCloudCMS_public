/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cms-imreading-reviews")
public class ReviewsEntity {

 @Id
 @Column(name = "isbn", nullable = false, unique = true)
 private String bookIsbn;

 private Date date;

 @Column(columnDefinition = "TEXT")
 private String review;

 @OneToOne
 @JoinColumn(name = "isbn", referencedColumnName = "isbn")
 private BooksEntity book;

 @Override
 public final boolean equals(Object o) {
  if (this == o) return true;
  if (o == null) return false;
  Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
  Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
  if (thisEffectiveClass != oEffectiveClass) return false;
  ReviewsEntity that = (ReviewsEntity) o;
  return getBookIsbn() != null && Objects.equals(getBookIsbn(), that.getBookIsbn());
 }

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }
}

