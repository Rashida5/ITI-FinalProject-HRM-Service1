package org.example.finalgradservice1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/api/v1/home")
    public String displayHomePage(){
        return "home.html";
    }
}
