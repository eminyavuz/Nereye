package com.emin.nereye.domain.homePage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homepage {
    @GetMapping("/login")
    public String showMyLoginPage() {
        return "loginPage";
    }
    @GetMapping("/")
    public String showHome() {

        return "home";
    }

}
