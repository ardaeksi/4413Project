package com.damazon.repository;

import com.damazon.model.Item;
import com.damazon.model.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
		//Normally implementing here I learned that JPL already offers extensive functionality to incorporate SQL queries but I wanted to make sure for easier route 
		//Uses JPA language rather than SQL query since it is a service offered
	 	@Query("SELECT i FROM Item i")
	    List<Item> findAllItems();
	 	
	 	@Query("SELECT i FROM Item i WHERE i.itemID = :itemID")
	 	Optional<Item> findById(@Param("itemID")Integer itemID);
	 	
	 	@Query("SELECT i FROM Item i where i.productName = :productName")
	 	List<Item> findByProductName(@Param("productName")String productName);
	 	
	 	
}
