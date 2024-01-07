package nl.marisabel.confetticloud.cms.menuModule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImplementation implements  MenuService {

 private final MenuRepository menuRepository;
 private final MenuMapper mapper;

 @Override
 public List<MenuDto> getAllLinks() {
  List<MenuEntity> menuEntity = (List<MenuEntity>) menuRepository.findAll();
  return menuEntity.stream()
          .map(mapper::entityToDto)
          .collect(Collectors.toList());
 }

 public List<MenuDto> showInMenuList(MenuDto menuDto) {
  List<MenuEntity> menuEntity = (List<MenuEntity>) menuRepository.findAll();

  List<MenuDto> sortedMenuList = menuEntity.stream()
          .map(mapper::entityToDto)
          .filter(MenuDto::isShowInMenu)
          .sorted(Comparator.comparingInt(MenuDto::getDisplayOrder))
          .collect(Collectors.toList());

  return sortedMenuList;
   }

 @Override
 public MenuDto getLinkById(Long id) {
  return mapper.entityToDto(Objects.requireNonNull(menuRepository.findById(id).orElse(null)));
 }

 @Override
 public MenuDto addLink(MenuDto menu) {
  MenuEntity entity = mapper.dtoToEntity(menu);
  MenuEntity savedEntity = menuRepository.save(entity);
  return mapper.entityToDto(savedEntity);
 }

 @Override
 public MenuDto updateLink(Long id, MenuDto updatedMenu) {
  MenuEntity entity = mapper.dtoToEntity(updatedMenu);
  entity.setId(id);
  MenuEntity savedEntity = menuRepository.save(entity);
  return mapper.entityToDto(savedEntity);
 }

 @Override
 public boolean deleteMenu(Long id) {
  Optional<MenuEntity> optionalEntity = menuRepository.findById(id);
  if (optionalEntity.isPresent()) {
   MenuEntity entity = optionalEntity.get();
   menuRepository.delete(entity);
   return true;
  }
  return false;
 }


}
