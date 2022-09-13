package com.example.test2.Controllers;

import com.example.test2.Model.PostUser;
import com.example.test2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/blog/filterus")
    public String blogFilterUser(Model model)
    {
        Iterable<PostUser> posts = userRepository.findAll();
        model.addAttribute("result", posts);
        return "blog-filter-user";
    }
    @PostMapping("/blog/filterus/result")
    public String blogUserResult(@RequestParam String surname, Model model) {
        List<PostUser> result = userRepository.findBySurname(surname);
        model.addAttribute("result", result);
        return "blog-filter-user";}
    @GetMapping("/blog/all-users")
    public String blogUsers(Model model) {
        Iterable<PostUser> posts = userRepository.findAll();
        model.addAttribute("posts", posts);
        return "all-users";}
    @GetMapping("/blog/users")
    public String blogUsersAdd(PostUser postUser, Model model)
    {
        return "users";
    }
    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("postUser")@Valid PostUser postUser, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "users";
        }
        userRepository.save(postUser);
        return "redirect:/blog/all-users";}
}
