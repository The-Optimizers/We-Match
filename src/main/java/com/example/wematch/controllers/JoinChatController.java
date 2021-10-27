package com.example.wematch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class JoinChatController {
    @GetMapping("/team/chat")
    public String joinChat(Principal principal, Model model){
        model.addAttribute("username",principal.getName());
        return "chat";
    }
}
