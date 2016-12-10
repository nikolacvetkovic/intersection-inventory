package com.intersections.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cvele
 */

@Controller
public class SiteController {
    
    @RequestMapping("/")
    public String index(){
            
        return "index";
    }

    
    @RequestMapping("/login")
    public String login(){
            
        return "login";
    }
    
}
