package com.example.sprestdatabase;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class TestProduct_API {	
	@Test
	void test_GETproduct() {
		given().get("http://localhost:8080/product/findAll")
		.then().statusCode(200)
		.body("id[1]", equalTo(4))
		.body("name", hasItems("iPhoneSE","iPhoneX","tablechairs"))
		.log().all();
	}
	@Test
	void test_GET1() {
		given().get("http://localhost:8080/product/productId/4")
		.then().statusCode(200).		
		log().all();
	}
	
	@Test
	void test_POST() {
		JSONObject req = new JSONObject();
		req.put("name","table");
		req.put("price",34.0);
		req.put("description","furniture");
		req.put("quantity",67);	
		System.out.println(req.toJSONString());
		
		given()
		.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().post("http://localhost:8080/product/create")
		.then().statusCode(201);  //getting status code 200
	}	
	@Test
	void test_PUT() {
		JSONObject req = new JSONObject();
		req.put("name","table");
		req.put("price",124.0);
		req.put("description","furniture");
		req.put("quantity",67);	
		System.out.println(req.toJSONString());
		
		given()
		.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().put("http://localhost:8080/product/update/124")
		.then().statusCode(200);  
	}	
	
	@Test
	void test_DELETE() {
		JSONObject req = new JSONObject();		
		given()
		.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().delete("http://localhost:8080/product/delete/124")
		.then().statusCode(200);  
	}	
	
	
	@Test(description="Verify status code for GET method-users/2 as 200",groups="SmokeSuite")

	 public static void verifyResponsejson() throws IOException, org.json.simple.parser.ParseException {		

		 Response resp=given().when().get("https://reqres.in/api/users?page=2");

		 System.out.println(resp.path("total").toString());

		 assertEquals(resp.getStatusCode(),200);	 

		 assertEquals(resp.path("total").toString(),"12");	 

		 List<String> expected = new ArrayList<String>();		 

		 List<String> jsonResponse = resp.jsonPath().getList("data");

	     System.out.println(jsonResponse.size());

	     System.out.println("The number of data in the list is : " + jsonResponse.size());

	     assertEquals(jsonResponse.size(),6);

	     String usernames = resp.jsonPath().getString("data[1].email");

	     System.out.println("Email is : " + usernames);

	//   //  List<String> jsonResponses = resp.jsonPath().getList("data");

	//   //  System.out.println(jsonResponses.get(0));

	     Map<String,String> company = resp.jsonPath().getMap("data[2]");

	     System.out.println(company);

	     List<String> tset = new ArrayList<>(company.keySet());

	     for (int j=0;j<tset.size();j++) {

	    	 System.out.println("The keys for map in data is : "+ tset.get(j));

	     }

	     System.out.println("Fetch firtsname using map and get: " + company.get("first_name"));

	   //  List<Map<String, String>> companies = resp.jsonPath().getList("data");

	   //  System.out.println(companies.get(0).get("first_name"));

	    List<String> ids = resp.jsonPath().getList("data.email");

//	    Collections.sort(jsonResponse);

//	    Collections.sort(expected);

//	    assertEquals(jsonResponse,expected);

	     for(String i:ids)

	     {

	    	 System.out.println(i);

	        if (i.contentEquals("lindsay.ferguson@reqres.in")) {

	        	System.out.println("lindsay.ferguson is present in the response body");

	        }

	     }

	}


	
	/*
	@Test
	public void test2() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BA");

		System.out.println(request);
		System.out.println(request.toString());

		given().
		body(request.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().statusCode(201 );

	}*/

	
	
}