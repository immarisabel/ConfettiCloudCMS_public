/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions.books;

public class BookNotFoundException extends RuntimeException {
 public BookNotFoundException(String isbn) {
  super("No book found with the provided ISBN: " + isbn);
 }
}

