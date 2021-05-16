package stepDefinition;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.net.URI;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Steps {
	private static final String CREATE_URL = "http://localhost:8080/product/create";
	private static final String UPDATE_URL = "http://localhost:8080/product/update/{id}";
	private static final String DELETE_URL = "http://localhost:8080/product/delete/{id}";
	private static final String FIND_ALL = "http://localhost:8080/product/findAll";
	private Response response;
	private ValidatableResponse jsonresponse;
	private RequestSpecification request;
	private String PRODUCT_BY_ID = "http://localhost:8080/product/productId/{id}";

	@Given("get all products")
	public void get_all_products() {
		request = given();
	}

	@When("I execute GET request url")
	public void i_execute_GET_request_url() {
		response = request.when().accept("application/json").get(FIND_ALL);
	}

	@Given("a product exists with an id of {int}")
	public void a_product_exists_with_an_id_of(Integer int1) {
		request = given().pathParam("id", int1);
	}

	@When("a user GET the product by id")
	public void a_user_GET_the_product_by_id() {
		response = request.when().accept("application/json").get(PRODUCT_BY_ID);
	}

	@Then("verify the status code is {int}")
	public void verify_the_status_code_is(Integer int1) {
		jsonresponse = response.then().statusCode(int1);
		System.out.println("RESPONSE is" + jsonresponse.toString());
	}

	@Given("create a product with input$")
	public void a_input_product_to_create(Map<String, String> product) {
		String inputjson = product.get("input");
		request = given().contentType("application/json").body(inputjson);// check that the content type return from the API is JSON
	}

	@When("a user post the product")
	public void a_user_post_the_product() {
		response = request.when().accept("application/json").post(CREATE_URL);
	}

	//‘contentType(ContentType.JSON)‘ to make sure that the response we get is in JSON format.
    //extract the response into the variable by calling the ‘extract().response()‘ methods.
	@And("verify response includes following in the response$")
	public void create_response_verify(DataTable data) {
		data.asMap(String.class, String.class).forEach((k, v) -> {
			System.out.println(k + " : " + jsonresponse.extract().body().jsonPath().getString((String) k));
			Assert.assertEquals(v, jsonresponse.extract().body().jsonPath().getString((String) k));
		});
		
	}

	@Given("update a product with given a product id {int} with input")
	public void update_a_product(Integer int1, Map<String, String> product) {
		String inputjson = product.get("input");
		request = given().contentType("application/json").pathParam("id", int1).body(inputjson);
	}

	@When("a user PUT the productId with id")
	public void update_product() {
		response = request.when().accept("application/json").put(UPDATE_URL);
	}

	@When("a user delete the product by id")
	public void a_user_delete_the_product_by_id() {
		response = request.when().accept("application/json").delete(DELETE_URL);
	}

}