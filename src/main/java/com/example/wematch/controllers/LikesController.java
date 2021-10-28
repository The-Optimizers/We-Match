package com.example.wematch.controllers;

import com.example.wematch.models.Comment;
import com.example.wematch.models.Likes;
import com.example.wematch.models.Posts;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.LikeRepository;
import com.example.wematch.repositories.PostsRepository;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class LikesController {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;


    @RequestMapping(value = "/createLike", method = RequestMethod.POST)
    public String createNewLike(@Validated Likes like,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "blogs";

        } else {
            likeRepository.save(like);
            return "redirect:/v2/blog/" + like.getPost().getId();
        }
    }

    @RequestMapping(value = "/likePost/{id}", method = RequestMethod.GET)
    public String likePostWithId(@PathVariable Long id,
                                    Principal principal,
                                    Model model) {

        Optional<Posts> post = postsRepository.findById(id);

        if (post.isPresent()) {
            Optional<Users> user = Optional.ofNullable(userRepository.findByUsername(principal.getName()));

            if (user.isPresent()) {
                Likes likes = new Likes();
                likes.setUser(user.get());
                likes.setPost(post.get());

                model.addAttribute("likes", likes);

                return "blogs";

            } else {
                return "error";
            }

        } else {
            return "error";
        }
    }

    @GetMapping("/v2/like/{id}")
    public String getPostWithId (@PathVariable Long id,
                                 Principal principal,
                                 Model model){

        Optional<Posts> optionalPost = postsRepository.findById(id);

        if (optionalPost.isPresent()) {
            Posts post = optionalPost.get();

            model.addAttribute("post", post);
                model.addAttribute("username", principal.getName());

            return "blogs";

        } else {
            return "error";
        }
    }
}
