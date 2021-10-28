package com.example.wematch.controllers;

import com.example.wematch.models.*;
import com.example.wematch.models.DTO.FreeLanceDTO;
import com.example.wematch.models.DTO.TeamsDTO;
import com.example.wematch.repositories.*;
import com.example.wematch.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.PSource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CreateTeamController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserServices services;
    @Autowired
    UserRepository applicationUserRepository;
    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    FreelanceTeamRepository freelanceTeamRepository;
    @Autowired
    TestRepo testRepo;
    @Autowired
    RoleRepository roleRepository;


    public String cardId = "";
    public String nameValue = "";
public int counter=0;
    @PostMapping("/create/team")
    public String signUpNewTeam(@ModelAttribute TeamsDTO teams, Model model, Principal principal) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
        Users user = applicationUserRepository.findByUsername(principal.getName());
        Teams newTeam = new Teams(user,
                teams.getName(),
                teams.getNumber(),
                teams.getTime(),
                teams.getInfo(),
                teams.getTeamType(),
                teams.getAgeRange(),
                teams.getImageUrl(),
                teams.getCountry(),
                teams.getGender(),
                teams.getBio());
        services.rolesMethod();

        Role roleAdmin = roleRepository.findByName("Admin");
        cardId = principal.getName();
        newTeam.setCardId(cardId);
        user.addRole(roleAdmin);

        applicationUserRepository.save(user);
        teamsRepository.save(newTeam);

        return ("redirect:/allTeams");
    }

    @GetMapping("/create/team")
    public String showCreateTeamForm(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
//services.registerDefaultUser(applicationUser);


        return ("create_team");
    }


    /**********************for freelance team :)*************************/
    @PostMapping("/create/team2")
    public String signUpNewFreeTeam(@ModelAttribute FreeLanceDTO teams, Model model, Principal principal) {

        Users user = applicationUserRepository.findByUsername(principal.getName());
        FreeLanceTeam newTeam = new FreeLanceTeam(user,
                teams.getName(),
                teams.getNumber(),
                teams.getInfo(),
                teams.getTeamType(),
                teams.getAgeRange(),
                teams.getImageUrl(),
                teams.getGender(),
                teams.getBio(),
                teams.getSkills());
        services.rolesMethod();

        Role roleAdmin = roleRepository.findByName("Admin");
        cardId = principal.getName();
        newTeam.setCardId(cardId);
        user.addRole(roleAdmin);

        applicationUserRepository.save(user);
        freelanceTeamRepository.save(newTeam);

        return ("redirect:/create/team2");
    }

    @GetMapping("/create/team2")
    public String showCreateFreeTeamForm(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
//services.registerDefaultUser(applicationUser);


        return ("create_team");
    }
public boolean check=false;
    @Autowired
    ValidationRepo validationRepo;
    @RequestMapping("/join/team")
    public String addFollow(Principal principal, @RequestParam Long id2, @RequestParam Long id, Validation validation, Model model, @RequestParam("id") String name, @Valid TeamsDTO teams) {
        Users user = applicationUserRepository.findById(id2).get();
        Teams toFollow = teamsRepository.findById(id).get();
        System.out.println("this is from join team and this is  name ===> " + name);
        nameValue = name;
        user.getTeam().add(toFollow);
validation.setTeam_id(toFollow.getId());
validation.setUser_id(user.getId());
        System.out.println("this tje counter ==>"+counter);

        applicationUserRepository.save(user);
        validationRepo.save(validation);
        return ("redirect:/allTeams");
    }

    @Transactional
    @RequestMapping("/leave/team")
    public String removeFollow(Principal principal, @RequestParam Long id2, @RequestParam Long id, Model model) {

        Users user = applicationUserRepository.findById(id2).get();
        Teams unFollow = teamsRepository.findById(id).get();
        System.out.println(unFollow.getName());
        user.getTeam().remove(unFollow);
        check=isValid(user,teamsRepository.findAll());

        applicationUserRepository.save(user);
        return ("redirect:/allTeams");
    }

    @GetMapping("/myteams")
    public String getFollowingInfo(Principal principal, Model model) {
        try {
            model.addAttribute("userData", principal.getName());
            Users user = applicationUserRepository.findByUsername(principal.getName());
            Set<Teams> userFollow = user.getTeam();
            model.addAttribute("Allfollowing", userFollow);
        } catch (NullPointerException e) {
            model.addAttribute("userData", "");
        }
        return "myteams";
    }

    @GetMapping("/cards/team/{team}")
    public String getSongsByAlbum(@PathVariable String team, Model model){
//        List<Teams> teams = teamsRepository.findAllByTeam_Name(team);
//        model.addAttribute("team_type", teams);
        return "team_cards";
    }
    /********************************************************************/

public boolean y=true;
    @GetMapping("/allTeams")

    public String showCards(Model model, Principal p) {

        Users currentUser = applicationUserRepository.findByUsername(p.getName());
        boolean session = isLoggedInUserHasAdminPower(currentUser);
        System.out.println("session value =========> " + session);
        model.addAttribute("hi", session);
        model.addAttribute("teams", teamsRepository.findAll());
        model.addAttribute("teams2", freelanceTeamRepository.findAll());
        model.addAttribute("username", p.getName());
        model.addAttribute("uid", currentUser);
        model.addAttribute("user_id", currentUser.getId());
        model.addAttribute("isValid", check);
        model.addAttribute("testValid", currentUser.teams);

        model.addAttribute("y", y);
        System.out.println("from method ==>" + isValid(currentUser, teamsRepository.findAll()));
        return "team_cards";
    }

    public Long index = 0L;

    public Boolean isUserLoggedIn(Principal p) {
        if (p != null)
            return true;
        else
            return false;
    }


    public boolean isValid(Users user, List<Teams> teams) {
        System.out.println("this is from isValid method and this is  name ===> " + nameValue);
        String[] ary = nameValue.split(",");
        System.out.println("split name with arrays" + (Arrays.asList(nameValue.split(","))));

        List<String> valid = new ArrayList<>();
        List<String> valid2 = new ArrayList<>();


//        for (Teams object : user.getTeam()) {
//            Teams element = object;
//            System.out.println("set data team id user has joined ===>" + element.getId());
//            valid.add(element.toString());}

        for (Teams object : teams) {
            Teams element = object;
            System.out.println("set data all teams id ====> " + element.getId());

            valid2.add(element.toString());
        }

        for (Teams object : user.getTeam()) {
            Teams element = object;
            for (Users object2 : element.getUser()) {
                Users element2 = object2;
                System.out.println("set data team id user has joined inside ===>" + element2.getId());
                valid.add(element2.getId().toString());
            }

            System.out.println("set data team id user has joined outside ===>" + element.getId());
        }

        System.out.println("this is list users joined teams ===> "+valid);
        if (true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLoggedInUserHasAdminPower(Users user) {
        boolean y = false;
        Set<String> valid = new HashSet<>();

        System.out.println("this is role data!" + roleRepository.findAll());
        System.out.println(user.getRoles());
        System.out.println(user.getId());
        Teams test = (teamsRepository.findAllById(user.getId()));
        List<Teams> findAll = teamsRepository.findAll();
        for (int i = 0; i < findAll.size(); i++) {
            System.out.println("all teams id ==>" + findAll.get(i).getId());
        }


        for (Role object : user.getRoles()) {
            Role element = object;
            System.out.println("set data" + element);
            valid.add(element.toString());
        }

        System.out.println(valid);
        System.out.println("this valid set ==>" + valid.contains("Admin"));


        if (valid.contains("Admin")) {
            //  System.out.println("teams id test ===> "+teamsRepository.findAllById(user.getId()).getId());
            System.out.println("OK OK");
            y = true;

        } else {
            System.out.println("not admin :(");

            y = false;
        }

        return y;
    }
}
