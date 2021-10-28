package com.example.wematch.controllers;


import com.example.wematch.models.Teams;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.TeamsRepository;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Set;

@Controller
public class JoinedTeams {
    @Autowired
    UserRepository applicationUserRepository;
    @Autowired
    TeamsRepository teamsRepository;



}
