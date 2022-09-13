package com.example.test2.Controllers;

import com.example.test2.Model.PostUser;
import com.example.test2.Model.Role;
import com.example.test2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserWebController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("post_user", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model){
        Optional<PostUser> user = userRepository.findById(id);
        ArrayList<PostUser> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("post_user", res);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String username, @RequestParam(name="roles[]", required = false) String[] roles,
                           @RequestParam("userId") PostUser user){
        user.setUsername(username);
        user.getRoles().clear();
        if(roles!=null)
        {
            Arrays.stream(roles).forEach(r->user.getRoles().add(Role.valueOf(r)));
        }
        userRepository.save(user);

        return "redirect:/admin";
    }
}
