package egovframework.restaurant.web;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.restaurant.service.MenuService;
import egovframework.restaurant.vo.CartItemVO;
import egovframework.restaurant.vo.MenuVO;

@Controller
public class CartController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/cart.do")
    public String cart(
            HttpSession session,
            Model model) {

        @SuppressWarnings("unchecked")
        List<CartItemVO> cartList =
                (List<CartItemVO>) session.getAttribute("cartList");

        model.addAttribute("cartList", cartList);

        return "cart";
    }

    @RequestMapping(value = "/cart/add.do", method = RequestMethod.POST)
    public String addCart(
            @RequestParam("menuId") int menuId,
            @RequestParam("quantity") int quantity,
            HttpSession session) {

        @SuppressWarnings("unchecked")
        List<CartItemVO> cartList =
                (List<CartItemVO>) session.getAttribute("cartList");

        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        boolean alreadyExists = false;

        for (CartItemVO item : cartList) {
            if (item.getMenuId() == menuId) {
                item.setQuantity(item.getQuantity() + quantity);
                alreadyExists = true;
                break;
            }
        }

        if (!alreadyExists) {
        	for (MenuVO menu : menuService.selectMenuList(null, 0, Integer.MAX_VALUE)) {
                if (menu.getMenuId() == menuId) {

                    CartItemVO cartItem = new CartItemVO();

                    cartItem.setMenuId(menu.getMenuId());
                    cartItem.setMenuName(menu.getMenuName());
                    cartItem.setPrice(menu.getPrice());
                    cartItem.setQuantity(quantity);

                    cartList.add(cartItem);

                    break;
                }
            }
        }

        session.setAttribute("cartList", cartList);

        return "redirect:/cart.do";
    }
    
    @RequestMapping(value = "/cart/update.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateCart(
            @RequestParam("menuId") int menuId,
            @RequestParam("quantity") int quantity,
            HttpSession session) {

        @SuppressWarnings("unchecked")
        List<CartItemVO> cartList =
                (List<CartItemVO>) session.getAttribute("cartList");

        if (cartList == null) {
            return "fail";
        }

        for (CartItemVO item : cartList) {
            if (item.getMenuId() == menuId) {
                item.setQuantity(quantity);
                break;
            }
        }

        session.setAttribute("cartList", cartList);

        return "success";
    }
}