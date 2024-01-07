/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marisabel.confetticloud.cms.exceptions.books.BookNotFoundException;
import nl.marisabel.confetticloud.cms.exceptions.books.DuplicateBookException;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.logs.LogsEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesEntity;
import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class BooksServiceImplementation implements BooksService {

 private final BooksRepository booksRepository;
 private final ShelvesRepository shelvesRepository;
 private final BooksMapper mapper;


// @Override
// public BooksDTO createBook(BooksDTO book, List<Long> shelvesIds) {
//  String bookIsbn = book.getIsbn();
//  if (booksRepository.existsById(bookIsbn)) {
//   throw new DuplicateBookException(bookIsbn);
//  }
//  BooksEntity bookEntity = mapper.dtoToEntity(book);
//  bookEntity.setShelves((List<ShelvesEntity>) shelvesRepository.findAllById(shelvesIds));
//  BooksEntity newBook = booksRepository.save(bookEntity);
//  return mapper.entityToDto(newBook);
// }
//@Override
//public BooksDTO updateBook(String isbn, BooksDTO book, List<Long> shelvesIds) {
// if (booksRepository.existsById(isbn)) {
//
//  BooksEntity updatedBook = mapper.dtoToEntity(book);
//  updatedBook.setIsbn(isbn);
//  updatedBook.setShelves((List<ShelvesEntity>) shelvesRepository.findAllById(shelvesIds));
//
//  return mapper.entityToDto(booksRepository.save(updatedBook));
// }
// throw new BookNotFoundException(isbn);
//}


 @Override
 public BooksDTO createBook(BooksDTO book, List<Long> shelvesIds) {
  String bookIsbn = book.getIsbn();
  if (booksRepository.existsById(bookIsbn)) {
   throw new DuplicateBookException(bookIsbn);
  }

  // Create book entity and set shelves
  BooksEntity bookEntity = mapper.dtoToEntity(book);
  bookEntity.setShelves((List<ShelvesEntity>) shelvesRepository.findAllById(shelvesIds));

  // Save the book entity
  BooksEntity newBook = booksRepository.save(bookEntity);
  return mapper.entityToDto(newBook);
 }

 @Override
 public BooksDTO updateBook(String isbn, BooksDTO book, List<Long> shelvesIds) {
  if (!booksRepository.existsById(isbn)) {
   throw new BookNotFoundException(isbn);
  }

  // Retrieve existing book entity
  BooksEntity existingBook = booksRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

  // Update book entity fields
  existingBook.setTitle(book.getTitle());
  existingBook.setAuthor(book.getAuthor());
  existingBook.setPages(book.getPages());
  existingBook.setThumbnailUrl(book.getThumbnailUrl());
  existingBook.setSelfLink(book.getSelfLink());

  // Set shelves
  existingBook.setShelves((List<ShelvesEntity>) shelvesRepository.findAllById(shelvesIds));


  // Save the updated book entity
  BooksEntity updatedBook = booksRepository.save(existingBook);
  return mapper.entityToDto(updatedBook);
 }


 @Override
 public BooksDTO getBookByIsbn(String isbn) {
  BooksEntity booksEntity = booksRepository.findById(isbn).orElse(null);
  if (booksEntity != null) {
   return mapper.entityToDto(booksEntity);
  }
  throw new BookNotFoundException(isbn);
 }

 @Override
 public List<BooksDTO> getAllBooks() {
  List<BooksEntity> bookList = (List<BooksEntity>) booksRepository.findAll();
  return mapper.convertEntityListToDtoList(bookList);
 }




 @Override
 public boolean deleteBook(String isbn) {
  Optional<BooksEntity> bookToBeDeleted = booksRepository.findById(isbn);
  if (bookToBeDeleted.isPresent()) {
   booksRepository.findById(isbn).ifPresent(booksRepository::delete);
   return true;
  }
  throw new BookNotFoundException(isbn);
 }

}
