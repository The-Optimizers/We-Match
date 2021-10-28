package com.example.wematch.controllers;



import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("api/")

public class AboutUsController {

@Autowired
    UserRepository userRepository;
    @GetMapping("/aboutus")
    public String aboutUsPage(Model model, Principal principal){
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
            return "aboutUs";
        }
        else{
            model.addAttribute("username","guest");
        }
        return ("aboutUs");
    }


    public Boolean isUserLoggedIn(Principal p) {
        if (p != null)
            return true;
        else
            return false;
    }

}
