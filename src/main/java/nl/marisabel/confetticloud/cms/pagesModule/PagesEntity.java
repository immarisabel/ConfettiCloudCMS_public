/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.pagesModule;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@Builder
@Table(name = "cms-pages")
@AllArgsConstructor
public class PagesEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String title;

 @CreationTimestamp
 private Instant createdDate;

 @UpdateTimestamp
 private Instant modifiedDate;

 private boolean isPublic;

 @Column(columnDefinition = "TEXT")
 private String content;


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
  PagesEntity that = (PagesEntity) o;
  return getId() != null && Objects.equals(getId(), that.getId());
 }

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }
}
