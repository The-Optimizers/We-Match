package com.example.wematch.controllers;

import com.example.wematch.models.Users;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("api/")
public class ProfileController {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository applicationUserRepository;


//    @GetMapping("/profile")
//    public String getProfile(Principal prni, Mode) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
//
//        if (applicationUser == null)
//            return "redirect:http://localhost:8085/api/users";
//
//        model.addAttribute("user", applicationUser);
//        return "profile";
//    }


    @GetMapping("/profile")
    public String UserProfile(Principal principal , Model model) {
        if (applicationUserRepository != null) {
            model.addAttribute("userData", principal.getName());
            model.addAttribute("allUserData", applicationUserRepository.findByUsername(principal.getName()));
        } else {
            model.addAttribute("userData", "No user");
            model.addAttribute("allUserData", new Users());
        }
        return "profile";
    }


//    @PostMapping("/userprofile")
//    public RedirectView addNewPost(Principal principal , @RequestParam String body){
//        Post post = new Post(body , applicationUserRepository.findByUsername(principal.getName()));
//        postRepository.save(post);
//        return new RedirectView("/userprofile");
//    }
}
