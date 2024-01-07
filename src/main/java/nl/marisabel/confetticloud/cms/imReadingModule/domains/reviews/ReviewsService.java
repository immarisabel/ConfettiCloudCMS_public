/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewsService {


 ReviewsDTO addReviewToBook(ReviewsDTO reviewsDTO);

 ReviewsDTO getReviewForABook(String isbn);

 List<ReviewsDTO> getAllReviews();

 ReviewsDTO updateReview(String isbn, ReviewsDTO updatedReviewsDTO);

 boolean deleteReview(String isbn);


}





