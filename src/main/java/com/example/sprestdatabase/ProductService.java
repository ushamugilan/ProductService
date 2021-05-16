package com.example.sprestdatabase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductService {
	public List<Product> findAllProducts();
	public Optional<Product> findProductById(Integer id) ;
	public List<Product> findByPriceGT(Float price);
	public Product create(Product body);
	public Product update(String id,Product body);
	public void delete(String id);
	//List<Product> findByPriceGT(Float price);
	//public Product create(Map<String, String> body);
	
	
	

}
