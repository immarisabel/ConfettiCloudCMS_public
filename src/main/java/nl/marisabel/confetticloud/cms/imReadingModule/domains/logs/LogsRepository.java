/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends CrudRepository<LogsEntity, Long> {

 @Query("SELECT r FROM LogsEntity r WHERE r.isbn = :isbn")
 List<LogsEntity> findAllByIsbn(@Param("isbn") String isbn);

 //Spring Data JPA repository method.
 // Spring Data JPA repository methods are typically used for SELECT queries,
 // and for DELETE or UPDATE operations,
 // you should use @Modifying and @Query annotations.

 @Modifying
 @Query("DELETE FROM LogsEntity r WHERE r.isbn = :isbn")
 void deleteAllByIsbn(@Param("isbn") String isbn);
}


