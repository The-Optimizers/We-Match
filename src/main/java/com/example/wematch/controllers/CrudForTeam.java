package com.example.wematch.controllers;


import com.example.wematch.models.Teams;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.RoleRepository;
import com.example.wematch.repositories.TeamsRepository;
import com.example.wematch.repositories.TestRepo;
import com.example.wematch.repositories.UserRepository;
import com.example.wematch.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CrudForTeam {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserServices services;
    @Autowired
    UserRepository applicationUserRepository;
    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    TestRepo testRepo;
    @Autowired
    RoleRepository roleRepository;
public Long savedId;
    @GetMapping("/edit/team/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Teams teams = teamsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Team Id:" + id));
        model.addAttribute("teams", teams);
        System.out.println("this the team object Id "+teams.users.getId());
        savedId=teams.users.getId();
        return "updateTeam";
    }

    @PostMapping("/update/team/{id}")
    public String updateTeam(@PathVariable("id") long id, @Valid @ModelAttribute Teams teams,BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            teams.setId(id);
            return "updateTeam";
        }

        teamsRepository.save(teams);

        return "redirect:/allTeams";
    }



    @RequestMapping("/delete/team/{id}")
    public String deleteAlbum(@PathVariable long id){
        teamsRepository.deleteById(id);
        return "redirect:/allTeams";
    }



}
