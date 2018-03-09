package cn.lym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuymz
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title","标题----");
        model.addAttribute("content","内容------");
        return "index";
    }

    @GetMapping("/login")
    public String loginView(){

        return "login";
    }

    @PostMapping("/loginAction")
    public String loginAction(HttpServletRequest request){
        request.getSession().setAttribute("isLogin","yes");
        return "redirect:/";
    }
}
