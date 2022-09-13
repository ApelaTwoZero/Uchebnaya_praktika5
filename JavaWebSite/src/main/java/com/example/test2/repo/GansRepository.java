package com.example.test2.repo;

import com.example.test2.Model.PostGan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GansRepository extends CrudRepository<PostGan,Long> {

    List<PostGan> findByName(String name);
    List<PostGan> findByNameContains(String name);
}
