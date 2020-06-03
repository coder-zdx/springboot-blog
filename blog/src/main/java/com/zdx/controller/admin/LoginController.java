package com.zdx.controller.admin;

import com.zdx.domain.User;
import com.zdx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zdx
 * @function 登陆控制器
 * @date 2020/5/18 14:48
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private IUserService userService;

    //此方法注解中不配置路径，访问localhost:8080/admin时，会跳转此方法页面
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(){
        return "admin/login";
    }

    //验证用户名和密码是否正确的控制器，视频说只能使用,使用其他则拿不到，因为使用的是重定向
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) throws Exception {
        User user = userService.checkUser(username, password);
        if(user != null){
            user.setPassword("想看密码？");
            session.setAttribute("user", user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }


    //注销控制器方法，调用该方法后，会将session域中user对象清除,并跳转/admin
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
