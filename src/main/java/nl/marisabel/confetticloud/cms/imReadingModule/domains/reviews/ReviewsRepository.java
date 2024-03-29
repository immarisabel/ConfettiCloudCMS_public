/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends CrudRepository<ReviewsEntity, String> {

 @Query("SELECT r FROM ReviewsEntity r WHERE r.bookIsbn = :isbn")
 ReviewsEntity findByIsbn(@Param("isbn") String isbn);

@Modifying
@Query("DELETE FROM ReviewsEntity r WHERE r.bookIsbn = :isbn")
 void deleteByIsbn(String isbn);
}


