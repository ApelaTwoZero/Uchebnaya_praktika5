package com.example.test2.Controllers;

import com.example.test2.Model.PostUser;
import com.example.test2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DeleteAndEditController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<PostUser> postUser = userRepository.findById(id);
        ArrayList<PostUser> res = new ArrayList<>();
        postUser.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!userRepository.existsById(id)) {return "redirect:/blog";}
        return "blog-details";}
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id")long id, PostUser postUser,
                           Model model)

    {if(!userRepository.existsById(id)){return "redirect:/";}
      PostUser posts = userRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid users Id" + id));
        model.addAttribute("posts", posts);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@ModelAttribute("postUser")@Valid  PostUser postUser, BindingResult bindingResult,
                                 @PathVariable("id") long id) {
        postUser.setId(id);
        if (bindingResult.hasErrors()) {
            return "blog-edit";
        }
        userRepository.save(postUser);
        return "redirect:/";}
    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        PostUser post = userRepository.findById(id).orElseThrow();
        userRepository.delete(post);
        return "redirect:/";}}
