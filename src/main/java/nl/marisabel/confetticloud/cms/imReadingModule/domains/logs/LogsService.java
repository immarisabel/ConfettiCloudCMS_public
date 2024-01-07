/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.imReadingModule.domains.logs;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogsService {

 LogsDTO addLogToBook(LogsDTO logsDTO);

 List<LogsDTO> getAllLogsForABook(String isbn);

 List<LogsDTO> getAllLogs();
 public LogsDTO getLogById(Long id);

 LogsDTO updateLog(Long id, LogsDTO updatedLogsDTO);

 boolean deleteLog(Long id);


}





