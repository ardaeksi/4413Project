package com.damazon.repository;

import com.damazon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	//JPQL Language rather than raw SQL because it offers better integration with Entity model in the project
	@Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username")String username);

	@Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username")String username);
	
	//This is auto-generated as Id is set
	Optional<User> findById(Long id);
    
    
}

