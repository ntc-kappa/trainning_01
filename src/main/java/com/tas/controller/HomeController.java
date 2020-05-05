package com.tas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = { "/" })
    public String login(HttpServletRequest request, final Model model) {
        String message=request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            }

        }
        return "login";
    }
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }


}
