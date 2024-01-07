/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;
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
@Table(name = "cms-imreading-logs")
public class LogsEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Date date;

 private int page;

 private boolean favorite;

 @Column(columnDefinition = "TEXT")
 private String log;

 private String isbn;

 @Override
 public final int hashCode() {
  if (this instanceof HibernateProxy hibernateProxy) {
   return hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode();
  } else {
   return getClass().hashCode();
  }
 }

 @Override
 public final boolean equals(Object o) {
  if (this == o) return true;
  if (o == null) return false;
  Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
  Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
  if (thisEffectiveClass != oEffectiveClass) return false;
  ShelvesEntity that = (ShelvesEntity) o;
  return getId() != null && Objects.equals(getId(), that.getId());
 }

 public void setId(Long id) {
  this.id = id;
 }


}
