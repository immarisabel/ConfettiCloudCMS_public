package nl.marisabel.confetticloud.cms.imReadingModule.domains.reviews;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewsMapper {


 public ReviewsDTO entityToDto(ReviewsEntity entity) {
  ReviewsDTO dto = new ReviewsDTO();
  dto.setBookIsbn(entity.getBookIsbn());
  dto.setDate(entity.getDate());
  dto.setReview(entity.getReview());
  return dto;
 }

 public ReviewsEntity reviewData(ReviewsDTO dto) {
  ReviewsEntity entity = new ReviewsEntity();
  entity.setBookIsbn(dto.getBookIsbn());
  entity.setDate(dto.getDate());
  entity.setReview(dto.getReview());
  return entity;
 }

 public List<ReviewsDTO> convertEntityListToDtoList(Iterable<ReviewsEntity> all) {
  List<ReviewsDTO> dtoList = List.of();
  for (ReviewsEntity entity : all) {
   dtoList.add(entityToDto(entity));
  }
  return dtoList;
 }
}
