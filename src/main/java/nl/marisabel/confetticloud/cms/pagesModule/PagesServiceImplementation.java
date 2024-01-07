/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.confetticloud.cms.pagesModule;

import lombok.RequiredArgsConstructor;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagesServiceImplementation implements PagesService {

 private final DataExistingCheck dataExistingCheck;
 private final PagesRepository pagesRepository;
 private final PagesMapper mapper;


 @Override
 public PagesDto addPage(PagesDto newPage) {
   return mapper.entityToDto(pagesRepository.save(mapper.dtoToEntity(newPage)));
 }

 @Override
 public List<PagesDto> getAllPages() {
  List<PagesEntity> pagesList = (List<PagesEntity>) pagesRepository.findAll();
  return pagesList.stream()
          .map(mapper::entityToDto)
          .collect(Collectors.toList());
 }

 @Override
 public PagesDto getPageById(Long id) {
  return mapper.entityToDto(Objects.requireNonNull(pagesRepository.findById(id).orElse(null)));
 }


 @Override
 public PagesDto updatePage(Long id, PagesDto updatedPage) {
  PagesEntity page = mapper.dtoToEntity(updatedPage);
  page.setId(id);
  return mapper.entityToDto(pagesRepository.save(page));
 }

 @Override
 public boolean deletePage(Long id) {
  PagesEntity page = pagesRepository.findById(id)
          .orElseThrow(() -> new IdNotFoundException(id));
  pagesRepository.delete(page);
  return true;
 }



}
