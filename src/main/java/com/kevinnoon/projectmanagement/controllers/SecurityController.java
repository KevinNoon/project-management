package com.kevinnoon.projectmanagement.controllers;

import com.kevinnoon.projectmanagement.entities.UserAccount;
import com.kevinnoon.projectmanagement.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SecurityController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserAccountService accountService;

    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }
    @PostMapping("/register/save")
    public String saveUser(Model model,UserAccount user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        accountService.save(user);
        return "redirect:/";
    }
}
