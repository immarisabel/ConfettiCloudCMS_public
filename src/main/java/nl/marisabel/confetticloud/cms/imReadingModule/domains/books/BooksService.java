/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.books;


import nl.marisabel.confetticloud.cms.imReadingModule.domains.shelves.ShelvesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {

 BooksDTO createBook(BooksDTO booksDTO,  List<Long> shelfIds);

 BooksDTO getBookByIsbn(String isbn);

 List<BooksDTO> getAllBooks();

 public BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO, List<Long> shelfIds);

 boolean deleteBook(String isbn);


}





