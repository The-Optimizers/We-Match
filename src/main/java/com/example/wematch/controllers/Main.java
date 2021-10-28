package com.example.wematch.controllers;

import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Main {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String header(Principal principal, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("isUserLoggedIn",isUserLoggedIn(principal));

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //
            if(isUserLoggedIn(principal)){
                model.addAttribute("userImage",userRepository.findAll());
            }
            model.addAttribute("username",principal.getName());
            String currentUserName = authentication.getName();
            return "header";
        }
        else{
                model.addAttribute("username","guest");
            }
        return "header";}


    public Boolean isUserLoggedIn(Principal p) {
        if (p != null)
            return true;
        else
            return false;
    }

}


