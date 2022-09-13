package com.example.test2.Controllers;
import com.example.test2.Model.PostUser;
import com.example.test2.Model.Role;
import com.example.test2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(PostUser user, @RequestParam(value = "name") String name,
                          @RequestParam(value = "surname") String surname,
                          @RequestParam(value = "midlname") String midlname,
                          @RequestParam(value = "age")
                          int age, @RequestParam(value = "growth")
                              int growth, @RequestParam(value = "weight")
                              double weight, Model model){
        PostUser userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже зарегистрирован");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setName(name);
        user.setSurname(surname);
        user.setMidlname(midlname);
        user.setAge(age);
        user.setGrowth(growth);
        user.setWeight(weight);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
