package com.example.sprestdatabase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product") //To specify on the method in the controller, 
                                    //to map a HTTP request to the URL to the method that is specified.
public class ProductController {

	// Spring will automatically resolve the instance and inject it into the class
	// that declared it
	// So, we don’t need to obtain the singleton instance ourselves.
	//Git branch update 1/29/2022 test1

	@Autowired
	ProductService productService;

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findAllProducts() {
		return productService.findAllProducts();
	}

	@GetMapping(value = "/productId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> findProductById(@PathVariable("id") Integer id) {
		return productService.findProductById(id);
	}

	// To find the products whose prices grater than the path variable //...@PathVariable..binds placeholder from the URI to the method parameter
	@GetMapping(value = "/prices/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findByPriceGT(@PathVariable("price") Float price) {
		return productService.findByPriceGT(price);

	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product create(@RequestBody Product body) {
		return productService.create(body);
	}

	@PutMapping(value="/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//- makes Spring bind method’s return value to the web response body
	public Product update(@PathVariable("id") String id, @RequestBody Product body) {
		return productService.update(id, body);

	}

	// @PatchMapping("product/")

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		productService.delete(id);
	}

}
