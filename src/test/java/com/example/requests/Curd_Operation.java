package com.example.requests;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Curd_Operation {
	
	int id;
	
	
	@Test(priority=0)
	
	public void get_list_of_users()
	{
		RestAssured.given()
		
		.when()
		
			.get("https://reqres.in/api/users?page=2")
		
		.then()
		
			.statusCode(200)
			.log().all();
			
	}
	@Test(priority=1)
	
	public void createUser()
	{
		
		HashMap data =new HashMap();
		data.put("Name" ,"Anil");
		data.put("job", "Software Engineer");
		
		id = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		//.then()
			//.statusCode(201)
			//.log().all();
		
	}
	
@Test(priority=2,dependsOnMethods="createUser")
	
	public void updateUser()
	{
		
		HashMap data =new HashMap();
		data.put("Name" ,"Anil");
		data.put("job", "Worker");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
		
	}
@Test(priority=3)

public void deleteUser()
{
	
	
	given()
		
	.when()
		.delete("https://reqres.in/api/users/"+id)
	.then()
		.statusCode(204)
		.log().all();
	
}
		
}
