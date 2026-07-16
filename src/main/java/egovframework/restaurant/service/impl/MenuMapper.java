package egovframework.restaurant.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.restaurant.vo.MenuVO;

@EgovMapper("menuMapper")
public interface MenuMapper {

	List<MenuVO> selectMenuList(
	        @Param("searchKeyword") String searchKeyword,
	        @Param("offset") int offset,
	        @Param("pageSize") int pageSize);
	
    void insertMenu(MenuVO menuVO);

    void updateMenu(MenuVO menuVO);

    void deleteMenu(@Param("menuId") int menuId);
    
    int selectMenuCount(
            @Param("searchKeyword") String searchKeyword);

}