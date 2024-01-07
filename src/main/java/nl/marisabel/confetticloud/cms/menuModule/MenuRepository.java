package nl.marisabel.confetticloud.cms.menuModule;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, Long> {

 @Query("SELECT m FROM MenuEntity m WHERE m.showInMenu = :showInMenu ORDER BY m.displayOrder")
 List<MenuDto> findAllByShowInMenuTrue(@Param("showInMenu") boolean showInMenu);

}
