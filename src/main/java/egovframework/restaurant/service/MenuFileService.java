package egovframework.restaurant.service;

import egovframework.restaurant.vo.MenuFileVO;

public interface MenuFileService {

    void insertMenuFile(MenuFileVO menuFileVO);

    MenuFileVO selectMenuFile(int menuId);

    void deleteMenuFile(int menuId);

}