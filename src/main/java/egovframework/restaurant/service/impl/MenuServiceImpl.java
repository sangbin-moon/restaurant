package egovframework.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import egovframework.restaurant.service.MenuService;
import egovframework.restaurant.vo.MenuVO;
import egovframework.restaurant.vo.MenuFileVO;

import java.io.File;
import java.util.UUID;



@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private MenuFileMapper menuFileMapper;

    @Override
    public List<MenuVO> selectMenuList(
            String searchKeyword,
            int offset,
            int pageSize) {

        return menuMapper.selectMenuList(
                searchKeyword,
                offset,
                pageSize);
    }
    
    @Override
    public int selectMenuCount(String searchKeyword) {
        return menuMapper.selectMenuCount(searchKeyword);
    }

    @Override
    public void insertMenu(MenuVO menuVO) {
        menuMapper.insertMenu(menuVO);
    }

    @Override
    @Transactional
    public void insertMenuWithImage(
            MenuVO menuVO,
            MultipartFile imageFile) {

        File savedFile = null;

        try {
            menuMapper.insertMenu(menuVO);
            
//            if ("rollbacktest".equals(menuVO.getMenuName())) {
//                throw new RuntimeException("트랜잭션 롤백 테스트용");
//            }
            
            String originalName = imageFile.getOriginalFilename();

            String extension = "";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(
                        originalName.lastIndexOf("."));
            }

            String savedName =
                    UUID.randomUUID().toString() + extension;

            String uploadPath = "C:/restaurant-upload";

            File uploadDirectory = new File(uploadPath);

            if (!uploadDirectory.exists()
                    && !uploadDirectory.mkdirs()) {

                throw new IllegalStateException(
                        "업로드 폴더를 생성할 수 없습니다.");
            }

            savedFile = new File(
                    uploadDirectory,
                    savedName);

            imageFile.transferTo(savedFile);

            MenuFileVO menuFileVO = new MenuFileVO();

            menuFileVO.setMenuId(menuVO.getMenuId());
            menuFileVO.setOriginalName(originalName);
            menuFileVO.setSavedName(savedName);
            menuFileVO.setFilePath(uploadPath);
            menuFileVO.setContentType(
                    imageFile.getContentType());
            menuFileVO.setFileSize(
                    imageFile.getSize());

            menuFileMapper.insertMenuFile(menuFileVO);

        } catch (Exception e) {

            if (savedFile != null && savedFile.exists()) {
                savedFile.delete();
            }

            throw new RuntimeException(
                    "메뉴와 이미지 등록 중 오류가 발생했습니다.",
                    e);
        }
    }
    
    @Override
    public void updateMenu(MenuVO menuVO) {
        menuMapper.updateMenu(menuVO);
    }

    @Override
    public void deleteMenu(int menuId) {
        menuMapper.deleteMenu(menuId);
    }

}