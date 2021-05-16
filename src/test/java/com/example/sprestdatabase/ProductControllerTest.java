package com.example.sprestdatabase;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

	/*
	 * We can @Autowire MockMvc because the WebApplicationContext provides an
	 * instance/bean for us
	 */
	@Autowired // mark a dependency which Spring is going to resolve and inject.
	private MockMvc mockmvc;
	/*
	 * We use @MockBean because the WebApplicationContext does not provide
	 * any @Component, @Service or @Repository beans instance/bean of this service
	 * in its context. It only loads the beans solely required for testing the
	 * controller.
	 */
	@MockBean
	private ProductService productservice;

	@Test
	public void testCreateProduct() throws Exception {
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("table");
		mockProduct.setPrice(10f);
		mockProduct.setDescription("furniture");
		mockProduct.setQuantity(10);

		String inputInJson = this.mapToJson(mockProduct);
		String URI = "/product/create";

		Mockito.when(productservice.create(Mockito.any(Product.class))).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();
		System.out.println("the input is" + inputInJson);
		System.out.println("the output is" + outputInJson);

		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testFindProductById() throws Exception {
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("table");
		mockProduct.setPrice(10f);
		mockProduct.setDescription("furniture");
		mockProduct.setQuantity(10);

		Optional<Product> mockProduct1= Optional.ofNullable(mockProduct);
		String URI = "/product/productId/1";

		Mockito.when(productservice.findProductById(Mockito.anyInt())).thenReturn(mockProduct1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				                                             .accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder)
				                  .andReturn();
		String expectedJson = this.mapToJson(mockProduct);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test

	public void testfindAllProducts() throws Exception {

		Product mockProduct1 = new Product();
		mockProduct1.setId(1);
		mockProduct1.setName("table");
		mockProduct1.setPrice(10f);
		mockProduct1.setDescription("furniture");
		mockProduct1.setQuantity(10);

		Product mockProduct2 = new Product();
		mockProduct2.setId(2);
		mockProduct2.setName("chair");
		mockProduct2.setPrice(10f);
		mockProduct2.setDescription("furniture");
		mockProduct2.setQuantity(10);

		List<Product> productList = new ArrayList<>();
		productList.add(mockProduct1);
		productList.add(mockProduct2);

		Mockito.when(productservice.findAllProducts()).thenReturn(productList);

		String URI = "/product/findAll";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(productList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	public void testFindByPriceGT() throws Exception {
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

		
		Mockito.when(productservice.findByPriceGT(Mockito.anyFloat())).thenReturn(productList);
		String URI = "/product/prices/3f";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				                                              .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(productList);
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println("The expectedJson is "+expectedJson);
		System.out.println("The outputJson is "+outputInJson);
		assertThat(outputInJson).isEqualTo(expectedJson);		
	}
	
	
	
	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 * 
	 * @throws JsonProcessingException
	 */

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
