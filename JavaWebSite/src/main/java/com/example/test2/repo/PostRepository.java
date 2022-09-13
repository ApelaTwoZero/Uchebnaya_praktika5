package com.example.test2.repo;

import com.example.test2.Model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post,Long> {


      List<Post> findByTitle(String title);


}
