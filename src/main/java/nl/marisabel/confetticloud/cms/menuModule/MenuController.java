package nl.marisabel.confetticloud.cms.menuModule;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import nl.marisabel.confetticloud.cms.exceptions.dataValidation.IdNotFoundException;
import nl.marisabel.confetticloud.cms.pagesModule.PagesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.menu.v1}"})
@RequiredArgsConstructor
@Tag(name = "Menu Module", description = "manage links to display as menu")
public class MenuController {

 private final MenuServiceImplementation menuService;

 @PostMapping
 public ResponseEntity<MenuDto> addLink(@RequestBody MenuDto link) {
  MenuDto newLink = menuService.addLink(link);
  return new ResponseEntity<>(newLink, newLink != null ? HttpStatus.CREATED : HttpStatus.NOT_IMPLEMENTED);
 }


 @GetMapping("/all")
 public ResponseEntity<List<MenuDto>> getAllLinks() {
  List<MenuDto> linksList = menuService.getAllLinks();
  if (linksList.isEmpty()) {
   return ResponseEntity.noContent().build();
  }
  return ResponseEntity.ok(linksList);
 }


 @GetMapping("/active")
 public ResponseEntity<List<MenuDto>> getActiveLinks() {
  List<MenuDto> menuDtos =
          menuService.showInMenuList(MenuDto
                  .builder()
                  .showInMenu(true).build());
  if (menuDtos.isEmpty()) {
   return ResponseEntity.noContent().build();
  }
  return ResponseEntity.ok(menuDtos);
 }

 @GetMapping("/{id}")
 public ResponseEntity<MenuDto> getLinkById(@PathVariable Long id) {
  MenuDto link = menuService.getLinkById(id);
  if (link != null) {
   return new ResponseEntity<>(link, HttpStatus.OK);
  }
  throw new IdNotFoundException(id);
 }

 @PutMapping("/{id}")
 public ResponseEntity<MenuDto> updateALink(@PathVariable Long id, @RequestBody MenuDto updatedLink) {
  MenuDto link = menuService.updateLink(id, updatedLink);
  if (link != null) {
   return ResponseEntity.ok(link);
  }
  throw new IdNotFoundException(id);
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteALink(@PathVariable Long id) {
  boolean deleted = menuService.deleteMenu(id);
  if (deleted) {
   return ResponseEntity.ok("Link deleted.");
  }
  throw new IdNotFoundException(id);
 }

}
