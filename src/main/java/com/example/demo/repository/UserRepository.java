package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	<Optional>User findByEmail(String email);
	
@Query(value="SELECT * FROM User u Where u.email != ?1 ",
		nativeQuery = true)
	List<User> findallusers(String username);

}
