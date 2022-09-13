package com.example.test2.Controllers;

import com.example.test2.Model.PostGan;
import com.example.test2.Model.PostUser;
import com.example.test2.repo.GansRepository;
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
public class DeleteAndEditGansController {

    @Autowired
    private GansRepository gansRepository;
    @GetMapping("/blog-gan/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<PostGan> post = gansRepository.findById(id);
        ArrayList<PostGan> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!gansRepository.existsById(id))
        {
            return "redirect:/blog";
        }
        return "blog-details-gan";
    }
    @GetMapping("/blog-gan/{id}/edit")
    public String blogEdit(@PathVariable("id")long id, PostGan postGan,
                           Model model)
    {
        if(!gansRepository.existsById(id)){
            return "redirect:/";
        }
        PostGan posts = gansRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid gans Id" + id));
        model.addAttribute("posts",posts);
        return "blog-edit-gan";
    }
    @PostMapping("/blog-gan/{id}/edit")
    public String blogPostUpdate(@ModelAttribute("postGan")@Valid PostGan postGan, BindingResult bindingResult,
                                 @PathVariable("id") long id
                                 ) {

        postGan.setId(id);
        if (bindingResult.hasErrors()) {
            return "blog-edit-gan";
        }
        gansRepository.save(postGan);
        return "redirect:/";
    }
    @PostMapping("/blog-gan/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        PostGan post = gansRepository.findById(id).orElseThrow();
        gansRepository.delete(post);
        return "redirect:/";
    }

}
