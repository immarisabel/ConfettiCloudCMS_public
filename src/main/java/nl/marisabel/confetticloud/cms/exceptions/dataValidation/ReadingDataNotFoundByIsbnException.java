/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.exceptions.dataValidation;

public class ReadingDataNotFoundByIsbnException extends RuntimeException {
 public ReadingDataNotFoundByIsbnException(String isbn) {
  super("Reading Data with ISBN: " + isbn + " not found.");
 }
}

