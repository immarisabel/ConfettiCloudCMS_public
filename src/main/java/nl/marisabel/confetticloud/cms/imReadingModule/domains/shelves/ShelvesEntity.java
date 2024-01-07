/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "cms-imreading-shelves")
@AllArgsConstructor
@NoArgsConstructor
public class ShelvesEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String shelfName;

 @ManyToMany
 @JoinTable(
         name = "cms-imreading-shelved_books",
         joinColumns = @JoinColumn(name = "shelves_shelf_id"),
         inverseJoinColumns = @JoinColumn(name = "books_isbn"))
 @ToString.Exclude
 private List<BooksEntity> books;
 public void setId(Long id) {
  this.id = id;
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

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }
}

