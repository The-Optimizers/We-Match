package com.example.wematch.controllers;


import com.example.wematch.models.Users;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("api/")
public class ApplicationUserController {
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository applicationUserRepository;
@GetMapping("/hello")
public String greet(Model model, Principal principal){

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
    model.addAttribute("user", applicationUser);

    return "greet";
}


    @PostMapping("/applicationuser")
    public String createUser(@RequestBody Users applicationUser) throws URISyntaxException {

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.save(applicationUser);
        //return ResponseEntity.created(new URI("http://localhost:3001/api/users" + savedUser.getId())).body(savedUser);
        return "redirect:http://localhost:4444/api/users";
    }
    @PostMapping("/signup")
    public String signUpNewUser(@ModelAttribute Users appUser) {
        appUser.setPassword(BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt())); // we have encrypted the user password
        applicationUserRepository.save(appUser);

        // we should then show the post creation page
        return ( "redirect:/api/login");
    }
@GetMapping("/signup")
public String showSignUpForm(){
        return ("signup");
}


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());

        if (applicationUser == null)
            return "redirect:http://localhost:8085/api/users";

        model.addAttribute("user", applicationUser);
        return "";
    }


}
