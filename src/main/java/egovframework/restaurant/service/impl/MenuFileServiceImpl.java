package egovframework.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.restaurant.service.MenuFileService;
import egovframework.restaurant.vo.MenuFileVO;

@Service
public class MenuFileServiceImpl implements MenuFileService {

    @Autowired
    private MenuFileMapper menuFileMapper;

    @Override
    public void insertMenuFile(MenuFileVO menuFileVO) {
        menuFileMapper.insertMenuFile(menuFileVO);
    }

    @Override
    public MenuFileVO selectMenuFile(int menuId) {
        return menuFileMapper.selectMenuFile(menuId);
    }

    @Override
    public void deleteMenuFile(int menuId) {
        menuFileMapper.deleteMenuFile(menuId);
    }
}