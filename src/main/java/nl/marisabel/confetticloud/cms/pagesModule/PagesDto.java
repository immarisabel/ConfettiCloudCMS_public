/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.pagesModule;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Tag(name = "Logs Model")
public class PagesDto {

 private Long id;

 private String title;

 private Instant createdDate;

 private Instant modifiedDate;

 private boolean isPublic;

 @Column(columnDefinition = "TEXT")
 private String content;


}
