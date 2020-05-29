package com.tas.controller;

import com.tas.common.Loggable;
import com.tas.common.PageConfig;
import com.tas.dto.UserDto;
import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import com.tas.repository.RoleRepository;
import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.security.Principal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController implements Loggable {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
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
            if(message.equals("sigin")){
                model.addAttribute("message", "Đăng kí thành công vui lòng chờ admin chấp thuận");
            }

        }
        getLogger().info(message);
        return "login";
    }
    @RequestMapping(value = { "/resisger"},method = RequestMethod.GET)
    public String resisger(final Model model) {

        return "resisger";
    }
    @Autowired
    RoleRepository roleRepository;
    @RequestMapping(value = { "/resisger/data"},method = RequestMethod.POST)
    public RedirectView resisger(final Model model,HttpServletRequest request) {
        String username=request.getParameter("username");
        String pass=request.getParameter("password");
        String email=request.getParameter("email");
        getLogger().info(request.getLocalAddr()+" request sigin :  " +username +" "+pass+" " +email);
        UserEntity entity=new UserEntity();
        entity.setUsername(username);
        entity.setPassword(bCryptPasswordEncoder.encode(pass));
        entity.setEmail(email);
        entity.setActive(false);
        entity.setGender(false);
        Set<RoleEntity> roleEntities=new HashSet<>();
        RoleEntity roleEntity=roleRepository.findByName("USER");
        getLogger().info(roleEntity.getName());
        roleEntities.add(roleEntity);
        entity.setRoleEntities(roleEntities);
        try {
            userRepository.save(entity);
        }catch (Exception e){
            getLogger().warn(e.getMessage());
            return new RedirectView("/trainning_01_war_exploded/resisger");
        }
        model.addAttribute("message", "sigin");
        return new RedirectView("/trainning_01_war_exploded/login");

    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }


}
