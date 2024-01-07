/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.ShelfNotFoundException;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksDTO;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.books.BooksEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ShelvesServiceImplementation implements ShelvesService {

 private final ShelvesRepository shelvesRepository;
 private final ShelvesMapper mapper;


 @Override
 public ShelvesDTO createShelf(ShelvesDTO newShelf) {
  log.info(newShelf.getShelfName());
  return mapper.convertEntityToDto( shelvesRepository.save(mapper.convertDtoToEntity(newShelf)) );
 }

 @Override
 public ShelvesDTO updateShelf(Long id, ShelvesDTO updatedShelf) {
  if (shelvesRepository.findById(id).isPresent()) {
   ShelvesEntity shelfData = mapper.convertDtoToEntity(updatedShelf);
   ShelvesEntity savedShelf = shelvesRepository.save(shelfData);
   return mapper.convertEntityToDto(savedShelf);
  }
  throw new ShelfNotFoundException(id);
 }

 @Override
 public ShelvesDTO getShelfById(Long id) {
  ShelvesEntity shelf = shelvesRepository.findById(id).orElse(null);
  if (shelf != null) {
   return mapper.convertEntityToDto(shelf);
  }
  throw new ShelfNotFoundException(id);
 }

 public ShelvesDTO getShelfByName(String shelfName) {
  ShelvesEntity shelf = shelvesRepository.findByName(shelfName);
  if (shelf != null) {
   return mapper.convertEntityToDto(shelf);
  }
  throw new ShelfNotFoundException(shelf.getId());
 }


 @Override
 public List<ShelvesDTO> getAllShelves() {
  List<ShelvesEntity> shelfList = (List<ShelvesEntity>) shelvesRepository.findAll();
  return mapper.convertEntityListToDtoList(shelfList);
 }


 @Override
 public boolean deleteShelf(Long id) {
  shelvesRepository.deleteById(id);
  return false;
 }

 @Override
 public boolean existsById(Long id) {
  return shelvesRepository.existsById(id);
 }

 public boolean existByName(String name) {
  return shelvesRepository.existsByName(name);
 }





}
