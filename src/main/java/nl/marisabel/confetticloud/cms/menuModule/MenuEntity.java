package nl.marisabel.confetticloud.cms.menuModule;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.confetticloud.cms.pagesModule.PagesEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "cms-menu")
public class MenuEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;

 private String url;

 private boolean showInMenu;

 private int displayOrder;

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
