package com.example.wematch.controllers;

import com.example.wematch.models.FreeLanceTeam;
import com.example.wematch.models.Teams;
import com.example.wematch.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class JoinChatController {

    @Autowired
    TeamsRepository teamsRepository;
    @GetMapping("/team/chat/{id}")
    public String joinChat(Principal principal, Model model, @PathVariable("id") long id){
        Teams teams = teamsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Team Id:" + id));
model.addAttribute("teams",teams);
        model.addAttribute("username",principal.getName());
        return "chat";
    }
}
