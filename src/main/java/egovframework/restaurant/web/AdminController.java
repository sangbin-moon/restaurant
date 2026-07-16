package egovframework.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.restaurant.service.MenuService;
import egovframework.restaurant.service.MenuFileService;
import egovframework.restaurant.vo.MenuVO;
import egovframework.restaurant.vo.MenuFileVO;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.validation.Valid;


@Controller
public class AdminController {

    @Autowired
    private MenuService menuService;
    
    @Autowired
    private MenuFileService menuFileService;

    @RequestMapping("/admin/menu.do")
    public String adminMenuList(
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
    	
    	int pageSize = 5;
    	int offset = (page - 1) * pageSize;
    	int totalCount = menuService.selectMenuCount(searchKeyword);
    	int totalPage = (int) Math.ceil((double) totalCount / pageSize);
    	
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute(
                "menuList",
                menuService.selectMenuList(
                        searchKeyword,
                        offset,
                        pageSize));
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("menuVO", new MenuVO());

        return "adminMenu";
    }
    
    @RequestMapping(value = "/admin/menu/add.do", method = RequestMethod.POST)
    public String addMenu(
            @Valid MenuVO menuVO,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model) {

        if (bindingResult.hasErrors()) {

            int pageSize = 5;
            int page = 1;
            int offset = 0;

            int totalCount = menuService.selectMenuCount(null);
            int totalPage = (int) Math.ceil((double) totalCount / pageSize);

            model.addAttribute(
                    "menuList",
                    menuService.selectMenuList(
                            null,
                            offset,
                            pageSize));

            model.addAttribute("page", page);
            model.addAttribute("totalPage", totalPage);

            return "adminMenu";
        }

        menuService.insertMenuWithImage(
                menuVO,
                imageFile);

        return "redirect:/admin/menu.do";
    }
    
    @RequestMapping(value = "/admin/menu/update.do", method = RequestMethod.POST)
    public String updateMenu(
            @RequestParam("menuId") int menuId,
            @RequestParam("menuName") String menuName,
            @RequestParam("price") int price) {

        MenuVO menu = new MenuVO();

        menu.setMenuId(menuId);
        menu.setMenuName(menuName);
        menu.setPrice(price);

        menuService.updateMenu(menu);

        return "redirect:/admin/menu.do";
    }
    
    @RequestMapping(value = "/admin/menu/delete.do", method = RequestMethod.POST)
    public String deleteMenu(
            @RequestParam("menuId") int menuId) {

        menuService.deleteMenu(menuId);

        return "redirect:/admin/menu.do";
    }
    
    @RequestMapping(value = "/admin/menu/image/upload.do", method = RequestMethod.POST)
    public String uploadMenuImage(
            @RequestParam("menuId") int menuId,
            @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException {

        if (imageFile == null || imageFile.isEmpty()) {
            return "redirect:/admin/menu.do";
        }

        String originalName = imageFile.getOriginalFilename();

        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        String savedName = UUID.randomUUID().toString() + extension;

        String uploadPath = "C:/restaurant-upload";

        File uploadDirectory = new File(uploadPath);

        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        File savedFile = new File(uploadDirectory, savedName);

        imageFile.transferTo(savedFile);

        MenuFileVO menuFileVO = new MenuFileVO();

        menuFileVO.setMenuId(menuId);
        menuFileVO.setOriginalName(originalName);
        menuFileVO.setSavedName(savedName);
        menuFileVO.setFilePath(uploadPath);
        menuFileVO.setContentType(imageFile.getContentType());
        menuFileVO.setFileSize(imageFile.getSize());

        MenuFileVO existingFile = menuFileService.selectMenuFile(menuId);

        if (existingFile != null) {

            File oldFile = new File(
                    existingFile.getFilePath(),
                    existingFile.getSavedName());

            if (oldFile.exists()) {
                oldFile.delete();
            }

            menuFileService.deleteMenuFile(menuId);
        }

        menuFileService.insertMenuFile(menuFileVO);

        return "redirect:/admin/menu.do";
    }
}