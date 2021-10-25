package com.example.wematch.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("api/")

public class AboutUsController {


    @GetMapping("/aboutus")
    public String aboutUsPage(){
        return ("aboutUs");
    }



}
