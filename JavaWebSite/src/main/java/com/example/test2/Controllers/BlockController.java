package com.example.test2.Controllers;

import com.example.test2.Model.Post;
import com.example.test2.Model.PostUser;
import com.example.test2.repo.PostRepository;
import com.example.test2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class BlockController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return  "blog-main";
    }
    @PostMapping("/create-post")
    public String create(@RequestParam(value = "title") String title,
                         @RequestParam(value = "anons") String anons,
                         @RequestParam(value = "full_text") String full_text, Model model)
    {
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model)
    {
        return "blog-add";
    }
    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }
    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
        List<Post> result = postRepository.findByTitle(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }

}
