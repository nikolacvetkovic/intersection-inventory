package com.intersections.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {
    
    @RequestMapping("/")
    public String index(){
            
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(required = false) String logout,
            @RequestParam(required = false) String error,
            ModelMap model){
        
            if(logout!=null){
                model.addAttribute("LogoutMessage", "Uspešno ste se odjavili.");
            }
            if(error!=null){
                model.addAttribute("ErrorMessage", "Neuspešna prijava!");
            }
        return "login";
    }
    
    @RequestMapping("/403")
    public String accessDenied(){
            
        return "403";
    }
    
}
