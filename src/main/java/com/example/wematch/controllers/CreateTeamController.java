package com.example.wematch.controllers;

import com.example.wematch.models.DTO.FreeLanceDTO;
import com.example.wematch.models.DTO.TeamsDTO;
import com.example.wematch.models.FreeLanceTeam;
import com.example.wematch.models.Role;
import com.example.wematch.models.Teams;
import com.example.wematch.models.Users;
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


public String cardId="";
public String nameValue="";
    @PostMapping("/create/team")
    public String signUpNewTeam(@ModelAttribute TeamsDTO teams, Model model,Principal principal) {
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
        cardId=principal.getName();
        newTeam.setCardId(cardId);
        user.addRole(roleAdmin);

        applicationUserRepository.save(user);
        teamsRepository.save(newTeam);

        return ("redirect:/create/team");
    }
    @GetMapping("/create/team")
    public String showCreateTeamForm(Model model){

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
                       cardId=principal.getName();
                       newTeam.setCardId(cardId);
                       user.addRole(roleAdmin);

                       applicationUserRepository.save(user);
                       freelanceTeamRepository.save(newTeam);

                       return ("redirect:/create/team2");
                   }
    @GetMapping("/create/team2")
    public String showCreateFreeTeamForm(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users applicationUser = applicationUserRepository.findByUsername(userDetails.getUsername());
//services.registerDefaultUser(applicationUser);


        return ("create_team");
    }

    @RequestMapping("/join/team")
    public String addFollow(Principal principal, @RequestParam Long id2, @RequestParam Long id,Model model, @RequestParam("id") String name) {
        Users user = applicationUserRepository.findById(id2).get();
        Teams toFollow = teamsRepository.findById(id).get();
        System.out.println("this is from join team and this is  name ===> "+name);
        nameValue=name;
        model.addAttribute("ok",2);

        user.getTeam().add(toFollow);
        applicationUserRepository.save(user);

        return ("redirect:/allTeams");
    }

    @Transactional
    @RequestMapping("/leave/team")
    public String removeFollow(Principal principal, @RequestParam Long id2, @RequestParam Long id,Model model) {

        Users user = applicationUserRepository.findById(id2).get();
        Teams unFollow = teamsRepository.findById(id).get();
        System.out.println(unFollow.getName());
        user.getTeam().remove(unFollow);
        model.addAttribute("ok",2);
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











    /********************************************************************/



@GetMapping("/allTeams")

    public String showCards(Model model, Principal p){
//        services.rolesMethod();
//    Role roleAdmin = roleRepository.findByName("Admin");
    Users currentUser = applicationUserRepository.findByUsername(p.getName());

//    currentUser.addRole(roleAdmin);
//
//    applicationUserRepository.save(currentUser);
boolean session=isLoggedInUserHasAdminPower(currentUser);
    System.out.println("session value =========> "+session);
model.addAttribute("hi",session);
    model.addAttribute("teams",teamsRepository.findAll());
    model.addAttribute("teams2",freelanceTeamRepository.findAll());
    model.addAttribute("username",p.getName());
    model.addAttribute("principal",currentUser);
    model.addAttribute("user_id",currentUser.getId());
    model.addAttribute("isValid",isValid(currentUser,teamsRepository.findAll()));
model.addAttribute("testValid",currentUser.teams);
model.addAttribute("u",nameValue);
    System.out.println("from method ==>"+isValid(currentUser,teamsRepository.findAll()));
    return "team_cards";
}
public Long index=0L;
    public Boolean isUserLoggedIn(Principal p){
        if(p != null)
            return true;
        else
            return false;
    }





public boolean isValid(Users user,List<Teams>teams){
    System.out.println("this is from isValid method and this is  name ===> "+nameValue);
    System.out.println("split name with arrays"+ Arrays.toString(nameValue.split(",")));

    List<String> valid=new ArrayList<>();
    List<String> valid2=new ArrayList<>();
Teams testTeam=new Teams();
testTeam.getId();
    for(Teams object : user.getTeam()) {
        Teams element =  object;
        System.out.println("set data"+element);
        valid.add(element.toString());
    }
    for(Teams object : teams) {
        Teams element =  object;
        System.out.println("set data"+element.getId());
        valid2.add(element.toString());
    }
//    System.out.println(teams.get(0).getId());
   System.out.println(user.getTeam());

if(!(Collections.disjoint(valid2, valid))){
    return true;
}
else{
    return false;
}
}

    public boolean isLoggedInUserHasAdminPower(Users user) {
        boolean y=false;
        Set<String> valid=new HashSet<>();

        System.out.println("this is role data!"+roleRepository.findAll());
        System.out.println(user.getRoles());
        System.out.println(user.getId());
        Teams test= (teamsRepository.findAllById(user.getId()));
        List<Teams> findAll=  teamsRepository.findAll();
        for(int i=0;i<findAll.size();i++){
            System.out.println("all teams id ==>"+findAll.get(i).getId());
        }



        for(Role object : user.getRoles()) {
            Role element =  object;
            System.out.println("set data"+element);
            valid.add(element.toString());
        }

        System.out.println(valid);
        System.out.println("this valid set ==>"+valid.contains("Admin"));


        if(valid.contains("Admin") ) {
            //  System.out.println("teams id test ===> "+teamsRepository.findAllById(user.getId()).getId());
            System.out.println("OK OK");
            y = true;

        } else {
                System.out.println("not admin :(");

                y=false;
            }

return y;
    }
}
