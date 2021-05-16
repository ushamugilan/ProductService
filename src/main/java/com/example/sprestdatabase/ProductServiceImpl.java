package com.example.sprestdatabase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productrepos;
	
	@Override
	public List<Product> findAllProducts() {
		return productrepos.findAll();		
	}
	
	@Override
	public Optional<Product> findProductById(Integer id) {
		//int id1 = Integer.parseInt(id);
		return productrepos.findById(id);
		
	}

	public List<Product> findByPriceGT(Float price) {
		//float price1 = Float.parseFloat(price);
		return productrepos.findByPriceGreaterThan(price);
	}

	@Override
	public Product create(Product body) {
		/*int id = Integer.parseInt(body.get("id"));
		String name = body.get("name");
		Float price = Float.parseFloat(body.get("price"));
        String description = body.get("description");
		int quantity =Integer.parseInt(body.get("quantity"));
		
       return productrepos.save(new Product(id,name,price,description,quantity));*/
		 return productrepos.save(body);
	}

	@Override
	public Product update(String id,Product body) {
	/*	int id1 = Integer.parseInt(body.get("id"));
		String name = body.get("name");
		Float price = Float.parseFloat(body.get("price"));
        String description = body.get("description");
		int quantity =Integer.parseInt(body.get("quantity"));
		return productrepos.save(new Product(id1,name,price,description,quantity));*/
		int id1 = Integer.parseInt(id);
		Product product = productrepos.getOne(id1);
		product.setDescription(body.getDescription());
		product.setName(body.getName());
		product.setPrice(body.getPrice());
		product.setQuantity(body.getQuantity());
		return productrepos.save(product);
	}

	@Override
	public void delete(String id) {
		productrepos.deleteById(Integer.parseInt(id));
		
	}
	
	

}
