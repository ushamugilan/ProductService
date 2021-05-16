package com.example.sprestdatabase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	// custom query to search to blog post by title or content
	//custom query to find the products greater than 100 (price>100)
	//return type findBy_column name_constrain(parameter)
	List<Product> findByPriceGreaterThan(Float price);
	//Native query join two table and get????
	//JPA with multiple tables

	

	

	

	

}
