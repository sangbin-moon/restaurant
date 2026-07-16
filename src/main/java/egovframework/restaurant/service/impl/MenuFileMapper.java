package egovframework.restaurant.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.restaurant.vo.MenuFileVO;

@EgovMapper("menuFileMapper")
public interface MenuFileMapper {

    void insertMenuFile(MenuFileVO menuFileVO);

    MenuFileVO selectMenuFile(int menuId);

    void deleteMenuFile(int menuId);

}