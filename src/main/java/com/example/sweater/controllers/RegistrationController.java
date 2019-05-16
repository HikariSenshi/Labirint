package com.example.sweater.controllers;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService users;

    @GetMapping("/signup")
    public String get() {

        return "signup";
    }

    @PostMapping("/signup")
    public String reg(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Map<String, Object> param) {

        User user = new User(username, password, email);
        System.out.println("here");

        if (!users.addUser(user)) {
            param.put("message", "User already exist!");
        }
        param.put("message", "Successful");

        return "redirect:/login";
    }


    @GetMapping("/activation/{code}")
    public String activate(Model model , @PathVariable String code) {

        boolean isActivated = users.activateUser(code);

        if(isActivated){
            model.addAttribute("message","User successfully activated");
        }   else {
            model.addAttribute("message","Activation code is not found!");

        }

        return "login";
    }

}
