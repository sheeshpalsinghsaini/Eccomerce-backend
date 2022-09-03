package com.ecom.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.models.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * for fetch data by Id it work but for another property we need
	 * to define custom query methods.
	 */
	
	public Optional<User> findByEmail(String email);	//we can declare for all properties.
	public List<User> findByName(String name); //parameter are from Users.
	public User findByEmailAndPassword(String email, String password);//find with both email,password
	/**
	 * public Optional<User> findByEmail(String email);
	 * -> optional is recommend it show data may be present or not
	 */
	
	//find all active user
//	public List<User> findByActiveTrue();//findByActiveFalse -> fetch all non active.
	public List<User> findByAboutIsNotNull();//findByAboutIsNull();
	public List<User> findByNameStartingWith(String prefix);
	public List<User> findByNameContaining(String infix);
	public List<User> findByNameLike(String likePattern);//pattern="a%"
	public List<User> findByNameOrderByNameDesc(String name);
	//find top 3 user by id
	public List<User> findTop4ByUserId(int UserId);
	//these are the custom finder methods
	
	//we can write our own query
	//in query User is the class name.
	@Query("select u from User u")
	public List<User> getAllUser();
	
	//how to pass data where query.
	@Query("select u from User u where u.userId=:userId and u.email=:email") //:userId -> indicate parameter.
	public User getUserByEmail(String userId,String email);
	//public User getUserByEmail(@Param("userId") int abcId);

	
	
	
	
}
