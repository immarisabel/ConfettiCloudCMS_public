/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Reviews Model")

public class ReviewsDTO {

 private String bookIsbn;

 private Date date;

 private String review;


}