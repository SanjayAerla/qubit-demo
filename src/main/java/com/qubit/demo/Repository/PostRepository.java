package com.qubit.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubit.demo.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String>{

//	@Query("select post from Post post order by post.postCreatedTime desc limit 1")
//	public Post getLatestPost(Sort.by(Sort.Direction.DESC, "colName"));
}
