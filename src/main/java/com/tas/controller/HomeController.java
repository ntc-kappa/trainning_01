package com.tas.controller;

import com.tas.common.Loggable;
import com.tas.common.PageConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class HomeController implements Loggable {

    @RequestMapping(value = { "/","/home"}, method = RequestMethod.GET)
    public String homePage(Principal principal,Model model) {
        getLogger().info("Username "+ principal.getName()+" logined");
        model.addAttribute("userName", PageConfig.HELLO +principal.getName()+ " !");
        return "home";
    }
    @RequestMapping(value = { "/login"})
    public String login(HttpServletRequest request, final Model model) {
        String message=request.getParameter("message");
        if (message != null && !message.isEmpty()) {

            if (message.equals("error")) {
                model.addAttribute("message", "Sai tài khoản hoặc mật khẩu");
            }

        }
        getLogger().info(message);
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }


}
