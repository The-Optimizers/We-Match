package com.example.wematch.controllers;

import com.example.wematch.models.Posts;
import com.example.wematch.models.PostsDTO;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.PostsRepository;
import com.example.wematch.repositories.UserRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class PostController {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/v2/blogs")
    public String profile(@AuthenticationPrincipal Users user, Model model) {
        model.addAttribute("blogs", postsRepository.findAll());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("createdAt", new Date());
        return "blogs";
    }

    @GetMapping("/v2/blog/{id}")
    public String getPostWithId (@PathVariable Long id,
                                 Principal principal,
                                 Model model){

        Optional<Posts> optionalPost = postsRepository.findById(id);

        if (optionalPost.isPresent()) {
            Posts post = optionalPost.get();

            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }
            return "blog";

        } else {
            return "error";
        }
    }

    @PostMapping("/v2/blogs")
    public RedirectView createNewBlogPost(@AuthenticationPrincipal Users user, @ModelAttribute PostsDTO blogDTO) { // modelattribute when working with fomr data
//        Users users = userRepository.findByUsername(blogDTO.getUser());
//        Date date = blogDTO.setTimeStamp(new Date());
        Posts newBlog = new Posts(blogDTO.getBody(), user, blogDTO.getTimeStamp());
        postsRepository.save(newBlog);
        return new RedirectView("/v2/blogs");
    }

    @PostMapping("/edit/blog/{id}")
    public RedirectView updateBlog(@PathVariable(value = "id") Long blogId, @ModelAttribute Posts blogDetails, @AuthenticationPrincipal Users user) throws NotFoundException {
        Posts blog = postsRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found for this id :: " + blogId));
        blog.setUsers(user);
        postsRepository.save(blog);
        return new RedirectView("/v2/blogs");

    }

    @GetMapping("/edit/blog/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal Users user) {
        Posts blog = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Blog Id:" + id));
        blog.setUsers(user);
        model.addAttribute("blog", blog);
        System.out.println(blog);
        return "updateBlog";
    }

    @PostMapping("/update/blog/{id}")
    public String updateUser(@PathVariable("id") Long id, @Validated Posts blog, BindingResult result, @AuthenticationPrincipal Users user) {
        if (result.hasErrors()) {
            blog.setId(id);
            return "updateBlog";
        }
        blog.setUsers(user);
        postsRepository.save(blog);
        return "redirect:/v2/blogs";

    }




        @RequestMapping("/delete/blog/{id}")
        public RedirectView deleteAlbum ( @PathVariable long id){
            postsRepository.deleteById(id);
            return new RedirectView("/v2/blogs");

        }


        private boolean isPrincipalOwnerOfPost (Principal principal, Posts post){
            return principal != null && principal.getName().equals(post.getUsers().getUsername());
        }
    }
