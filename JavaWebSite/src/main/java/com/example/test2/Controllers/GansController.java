package com.example.test2.Controllers;

import com.example.test2.Model.PostGan;
import com.example.test2.repo.GansRepository;
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
public class GansController {
    @Autowired
    private GansRepository gansRepository;
    @GetMapping("/blog/filtergan")
    public String blogFilterGan(Model model)
    {
        Iterable<PostGan> posts = gansRepository.findAll();
        model.addAttribute("result", posts);
        return "blog-filter-gans";
    }
    @PostMapping("/blog/filtergan/result")
    public String blogGansResult(@RequestParam String name, Model model) {
        List<PostGan> result = gansRepository.findByName(name);
        model.addAttribute("result", result);
        return "blog-filter-gans";}
    @PostMapping("/blog/filtergan/result2")
    public String blogGansResult2(@RequestParam String name, Model model) {
        List<PostGan> result = gansRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "blog-filter-gans";}
    @GetMapping("/blog/gans")
    public String blogGansAdd(PostGan postGan, Model model)
    {
        return "gans";
    }
    @PostMapping("/create-gan")
    public String createGan(@ModelAttribute("postGan")@Valid PostGan postGan, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "gans";
        }
        gansRepository.save(postGan);
        return "redirect:/blog/all-gans";}
    @GetMapping("/blog/all-gans")
    public String blogGans(Model model) {
        Iterable<PostGan> posts = gansRepository.findAll();
        model.addAttribute("posts", posts);
        return "all-gans";}
}
