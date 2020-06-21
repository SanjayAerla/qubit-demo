package com.qubit.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubit.demo.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String>{
}
