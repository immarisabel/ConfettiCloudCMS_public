/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions.books;

public class ShelfNotFoundException extends RuntimeException {
 public ShelfNotFoundException(Long id) {
  super("No shelf found with the provided id: " + id
  );
 }
}
