package com.example.test2.repo;

import com.example.test2.Model.PostUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<PostUser,Long> {
   List<PostUser> findBySurname(String surname);
   PostUser findByUsername(String username);

}
