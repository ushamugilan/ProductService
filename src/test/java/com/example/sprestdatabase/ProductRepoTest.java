package com.example.sprestdatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest
class ProductRepoTest {
	//for the testing purposes u should use this,automatically start the transaction
	@Autowired
	private TestEntityManager entityManager;
	
	//this class is what we are going to test
	@Autowired
	private ProductRepository productrepos;
	
	//always should start with test
	@Test
	public void testSaveProduct(){
		Product product = getProduct();
		Product savedInDb = entityManager.persist(product);//persist will save the product in the hsql db
		Product getFromDb = productrepos.getOne(savedInDb.getId());			
		assertThat(getFromDb).isEqualTo(savedInDb);			
	}
	
	@Test
	public void  findProductById() {
		
		Product productfp = new Product();			
		productfp.setName("Table");
		productfp.setPrice(3f);
		productfp.setDescription("chips");
		productfp.setQuantity(100);
		
		//save product in db
		Product productSaveInDb1 = entityManager.persist(productfp);
		Optional<Product> productSaveInDb =Optional.ofNullable(productSaveInDb1);
		
		//Get product from db
		Optional<Product> productFromInDb = productrepos.findById(productSaveInDb1.getId());
		assertThat(productFromInDb).isEqualTo(productSaveInDb);			
	}
	
	@Test
	public void testFindAllProducts()
	{
		Product productfa = new Product();			
		productfa.setName("Pringles");
		productfa.setPrice(3f);
		productfa.setDescription("chips");
		productfa.setQuantity(100);
		
		Product productfap = new Product();			
		productfap.setName("couch");
		productfap.setPrice(3f);
		productfap.setDescription("chips22");
		productfap.setQuantity(100);
		
		entityManager.persist(productfa);
		entityManager.persist(productfap);
		
		Iterable<Product> allProductsFromDb= productrepos.findAll();
		List<Product> productList = new ArrayList<>();
		
		for(Product product : allProductsFromDb) {
			productList.add(product);	
		}
		
		assertThat(productList.size()).isEqualTo(2);
	}
	
	
	@Test
	public void testFindByPriceGreaterThan() {
		Product productfg = new Product();			
		productfg.setName("vase");
		productfg.setPrice(3f);
		productfg.setDescription("chips");
		productfg.setQuantity(100);
		
		entityManager.persist(productfg);
		List<Product> getFromDb = productrepos.findByPriceGreaterThan(2f);
		assertThat(getFromDb.size()).isEqualTo(1);			
	}
	
	@Test
	public void testDelete() {
		Product productd = new Product();
	
		productd.setName("chair");
		productd.setPrice(3f);
		productd.setDescription("chips");
		productd.setQuantity(100);
		
		Product productd2 = new Product();			
		productd2.setName("rug");
		productd2.setPrice(3f);
		productd2.setDescription("chips22");
		productd2.setQuantity(100);
		
		Product persist1 = entityManager.persist(productd);
		//to delete one product
		entityManager.remove(persist1);
		
		entityManager.persist(productd2);
		
		Iterable<Product> allProductsFromDb= productrepos.findAll();
		List<Product> productList = new ArrayList<>();
		
		for(Product product : allProductsFromDb) {
			productList.add(product);	
		}
		
		assertThat(productList.size()).isEqualTo(1);		
		
	}	
	
	@Test
	
	
	private Product getProduct() {
		Product product = new Product();			
		product.setName("Boxofchocolates");
		product.setPrice(10f);
		product.setDescription("one dozen");
		product.setQuantity(10);
		return product;
	}
}



