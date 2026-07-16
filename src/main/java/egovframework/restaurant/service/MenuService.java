package egovframework.restaurant.service;

import java.util.List;

import egovframework.restaurant.vo.MenuVO;

public interface MenuService {

	List<MenuVO> selectMenuList(
	        String searchKeyword,
	        int offset,
	        int pageSize);
	
	int selectMenuCount(String searchKeyword);

    void insertMenu(MenuVO menuVO);
    
    void insertMenuWithImage(
            MenuVO menuVO,
            org.springframework.web.multipart.MultipartFile imageFile);

    void updateMenu(MenuVO menuVO);

    void deleteMenu(int menuId);

}