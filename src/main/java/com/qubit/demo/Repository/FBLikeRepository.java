package com.qubit.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubit.demo.model.FBLike;

@Repository
public interface FBLikeRepository extends CrudRepository<FBLike, String> {

}
