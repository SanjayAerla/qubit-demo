//package com.qubit.demo.Repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.qubit.demo.model.User;
//
//@Repository
//public interface UserRepository extends CrudRepository<User, String> {
//
//	@Query("select user from User user where user.fbUserId = :facebookId")
//	public User getByIdAndStatus(@Param("facebookId") String faceboodId);
//	
////	@Query("select user.fbUserId from User where user.isNew = true")
////	public String getNewUserId();
//}
