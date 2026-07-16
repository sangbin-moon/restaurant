package egovframework.restaurant.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.restaurant.service.AdminUserService;
import egovframework.restaurant.vo.AdminUserVO;

@Controller
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
    public String loginForm() {

        return "adminLogin";
    }

    @RequestMapping(value = "/admin/login.do", method = RequestMethod.POST)
    public String login(
            @RequestParam("loginId") String loginId,
            @RequestParam("loginPassword") String loginPassword,
            HttpSession session,
            Model model) {

        AdminUserVO loginRequest = new AdminUserVO();

        loginRequest.setLoginId(loginId);
        loginRequest.setLoginPassword(loginPassword);

        AdminUserVO loginAdmin =
                adminUserService.login(loginRequest);

        if (loginAdmin == null) {

            model.addAttribute(
                    "loginError",
                    "아이디 or 비밀번호 확인");

            return "adminLogin";
        }

        session.setAttribute("loginAdmin", loginAdmin);

        return "redirect:/admin/menu.do";
    }
    
    @RequestMapping("/admin/logout.do")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/admin/login.do";
    }
}