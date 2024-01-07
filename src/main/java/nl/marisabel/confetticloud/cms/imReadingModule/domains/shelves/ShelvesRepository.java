/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelvesRepository extends CrudRepository<ShelvesEntity, Long> {

 @Query("SELECT s FROM ShelvesEntity s WHERE s.shelfName = :shelfName")
 public ShelvesEntity findByName(String shelfName);

 @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM ShelvesEntity s WHERE s.shelfName = :shelfName")
 boolean existsByName(String shelfName);

}
