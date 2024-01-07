/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cms-imreading-books")
public class BooksEntity {

 @Id
 @Column(name = "isbn", nullable = false, unique = true)
 private String isbn;

 private String title;

 private String author;

 private int pages;

 private String thumbnailUrl;

 private String selfLink;

 @ManyToMany
 @JoinTable(
         name = "cms-imreading-shelved_books",
         joinColumns = @JoinColumn(name = "books_isbn"),
         inverseJoinColumns = @JoinColumn(name = "shelves_shelf_id")
 )
 @ToString.Exclude
 private List<ShelvesEntity> shelves;

 @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
 private ReviewsEntity reviews;

 @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
 private ReadingDataEntity readingData;


 @Override
 public final boolean equals(Object o) {
  if (this == o) {
   return true;
  }

  if (o == null || getClass() != o.getClass()) {
   return false;
  }

  BooksEntity that = (BooksEntity) o;
  return Objects.equals(getIsbn(), that.getIsbn());
 }

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
          .getPersistentClass()
          .hashCode() : getClass()
          .hashCode();
 }

 public void addShelf(ShelvesEntity shelf) {
  shelves.add(shelf);
  shelf.getBooks().add(this);
 }

 public void removeShelf(ShelvesEntity shelf) {
  shelves.remove(shelf);
  shelf.getBooks().remove(this);
 }


}
