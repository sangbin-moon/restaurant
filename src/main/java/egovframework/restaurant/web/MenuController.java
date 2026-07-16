package egovframework.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.restaurant.service.MenuService;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping({"/main.do", "/menu.do"})
    public String menuList(Model model) {

    	model.addAttribute(
    	        "menuList",
    	        menuService.selectMenuList("", 0, 1000)
    	);

        return "menu";
    }
}