package com.example.wematch.controllers;

import com.example.wematch.models.FreeLanceTeam;
import com.example.wematch.models.Teams;
import com.example.wematch.repositories.*;
import com.example.wematch.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CrudForFreelanceTeam {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserServices services;
    @Autowired
    UserRepository applicationUserRepository;

    @Autowired
    TestRepo testRepo;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    FreelanceTeamRepository teamsRepository;
    public Long savedId;
    @GetMapping("/edit/team2/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        FreeLanceTeam teams = teamsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Team Id:" + id));
        model.addAttribute("teams", teams);
        System.out.println("this the team object Id "+teams.users.getId());
        savedId=teams.users.getId();
        return "updateFreeTeam";
    }

    @PostMapping("/update/team2/{id}")
    public String updateTeam(@PathVariable("id") long id, @Valid @ModelAttribute FreeLanceTeam teams, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            teams.setId(id);
            return "updateTeam";
        }

        teamsRepository.save(teams);

        return "redirect:/allTeams";
    }



    @RequestMapping("/delete/team2/{id}")
    public String deleteAlbum(@PathVariable long id){
        teamsRepository.deleteById(id);
        return "redirect:/allTeams";
    }

}
