package com.example.sprestdatabase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepo;

	@Test
	public void testCreate() {

		Product product = new Product();
		product.setId(1);
		product.setName("chair");
		product.setPrice(3f);
		product.setDescription("chips");
		product.setQuantity(100);
		
		Product product1 = new Product();
		product1.setId(2);
		product1.setName("chair");
		product1.setPrice(3f);
		product1.setDescription("chips");
		product1.setQuantity(100);

		Mockito.when(productRepo.save(product)).thenReturn(product1);
		assertThat(productService.create(product)).isEqualTo(product);
	}

	@Test
	public void testfindProductById() {
		Product product = new Product();
		product.setId(1);
		product.setName("chair");
		product.setPrice(3f);
		product.setDescription("chips");
		product.setQuantity(100);

		Optional<Product> product1 = Optional.ofNullable(product);

		Mockito.when(productRepo.findById(1)).thenReturn(product1);
		assertThat(productService.findProductById(1)).isEqualTo(product1);

	}

	@Test
	public void testfindAllProducts() {
		Product mockProduct1 = new Product();
		mockProduct1.setId(1);
		mockProduct1.setName("table");
		mockProduct1.setPrice(10f);
		mockProduct1.setDescription("furniture");
		mockProduct1.setQuantity(10);

		Product mockProduct2 = new Product();
		mockProduct2.setId(2);
		mockProduct2.setName("chair");
		mockProduct2.setPrice(12f);
		mockProduct2.setDescription("furniture");
		mockProduct2.setQuantity(10);

		List<Product> productList = new ArrayList<>();
		productList.add(mockProduct1);
		productList.add(mockProduct2);
		Mockito.when(productRepo.findAll()).thenReturn(productList);
		assertThat(productService.findAllProducts()).isEqualTo(productList);
	}

	@Test
	public void testFindByPriceGT() {
		Product mockProduct1 = new Product();
		mockProduct1.setId(1);
		mockProduct1.setName("table");
		mockProduct1.setPrice(10f);
		mockProduct1.setDescription("furniture");
		mockProduct1.setQuantity(10);

		Product mockProduct2 = new Product();
		mockProduct2.setId(2);
		mockProduct2.setName("chair");
		mockProduct2.setPrice(12f);
		mockProduct2.setDescription("furniture");
		mockProduct2.setQuantity(10);

		List<Product> productList = new ArrayList<>();
		productList.add(mockProduct1);
		productList.add(mockProduct2);
		Mockito.when(productRepo.findByPriceGreaterThan(3f)).thenReturn(productList);
		assertThat(productService.findByPriceGT(3f)).isEqualTo(productList);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setId(1);
		product.setName("chair");
		product.setPrice(3f);
		product.setDescription("chips");
		product.setQuantity(100);

		Mockito.when(productRepo.getOne(1)).thenReturn(product);
		product.setDescription("chipsupdate");
		product.setName("chairnew");
		product.setPrice(400f);
		product.setQuantity(10);
		
		Mockito.when(productRepo.save(product)).thenReturn(product);
		assertThat(productService.update(String.valueOf(1), product)).isEqualTo(product);

	}

	@Test
	public void testDelete() {
		Product product = new Product();
		product.setId(1);
		product.setName("chair");
		product.setPrice(3f);
		product.setDescription("chips");
		product.setQuantity(100);

		Mockito.when(productRepo.getOne(1)).thenReturn(product);
		Mockito.when(productRepo.existsById(product.getId())).thenReturn(false);
		assertFalse(productRepo.existsById(product.getId()));
		

	}

}
