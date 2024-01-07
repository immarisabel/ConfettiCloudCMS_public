package nl.marisabel.confetticloud.cms.menuModule;


import java.util.List;

public interface MenuService {

public List<MenuDto> getAllLinks();
public List<MenuDto> showInMenuList(MenuDto menuDto);
public MenuDto getLinkById(Long id);
public MenuDto addLink(MenuDto menu);
public MenuDto updateLink(Long id, MenuDto updatedMenu);
public boolean deleteMenu(Long id);

}
