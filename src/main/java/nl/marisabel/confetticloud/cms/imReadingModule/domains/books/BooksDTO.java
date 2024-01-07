/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.readingData.ReadingDataDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews.ReviewsDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Books Model")
public class BooksDTO {

 private String isbn;

 private String title;

 private String author;

 private int pages;

 private String thumbnailUrl;

 private String selfLink;

 private List<Long> shelves;

 private ReadingDataDTO readingData;

 private ReviewsDTO reviews;

}