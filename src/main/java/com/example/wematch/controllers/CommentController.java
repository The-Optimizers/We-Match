package com.example.wematch.controllers;

import com.example.wematch.models.Comment;
import com.example.wematch.models.Posts;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.CommentRepository;
import com.example.wematch.repositories.PostsRepository;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String createNewPost(@Validated Comment comment,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "commentForm";

        } else {
            commentRepository.save(comment);
            return "redirect:/v2/blog/" + comment.getPost().getId();
        }
    }

    @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
    public String commentPostWithId(@PathVariable Long id,
                                    Principal principal,
                                    Model model) {

        Optional<Posts> post = postsRepository.findById(id);

        if (post.isPresent()) {
            Optional<Users> user = Optional.ofNullable(userRepository.findByUsername(principal.getName()));

            if (user.isPresent()) {
                Comment comment = new Comment();
                comment.setUser(user.get());
                comment.setPost(post.get());

                model.addAttribute("comment", comment);

                return "commentForm";

            } else {
                return "error";
            }

        } else {
            return "error";
        }
    }
}
