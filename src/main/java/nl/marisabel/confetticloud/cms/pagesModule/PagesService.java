/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.confetticloud.cms.pagesModule;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PagesService {

 PagesDto addPage(PagesDto pagesDTO);

 List<PagesDto> getAllPages();

 PagesDto getPageById(Long id);

 PagesDto updatePage(Long id, PagesDto updatedPagesDto);

 boolean deletePage(Long id);


}





