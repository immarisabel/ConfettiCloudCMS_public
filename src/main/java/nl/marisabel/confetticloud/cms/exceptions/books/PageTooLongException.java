/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions.books;

public class PageTooLongException extends RuntimeException {
 public PageTooLongException() {
  super("Your current page is greater than the book's pages.");
 }

}
