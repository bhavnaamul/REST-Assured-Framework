package com.testingapi.getrequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class getUsers {
	
	
//	@Test(priority = 1)
//	public void getAllUsersWithQueryParams() {
//		
//		given()  //pre-requisite
//		 .pathParam("path", "users")
//		  .queryParam("page", 3)
//		   .queryParam("id", 5)
//		  .when()
//		    .get("https://reqres.in/api/{path}?page=2")
//		    
//		   .then()
//		     .statusCode(200)
//		      .log().all();
//		       
//	}
//	
//	@Test(priority = 0)
//	public void getAllUsersWithBodyAssertion() {
//		
//	  given()
//	    .contentType("ContentType.JSON")
//	    
//	   .when()
//	      .get("https://reqres.in/api/users?page=2")
//	      
//	    .then() 
//	       .statusCode(200)
//	         .body("data[1].first_name",equalTo("Lindsay"))
//	          .log().all();
//	         	}
//		
//	@Test(priority = 2)
//	public void getAllUsersWithTestNGAssertion() {
//		Response resp=
//	  given()
//	    .contentType("ContentType.JSON")
//	    
//	   .when()
//	      .get("https://reqres.in/api/users?page=2");
//	      
//	    Assert.assertEquals(resp.getStatusCode(), 200);
//		Assert.assertEquals(resp.getHeader("Content-Type"), "application/json; charset=utf-8");
//		
//		String email = resp.jsonPath().get("data[1].email").toString();
//		 Assert.assertEquals(email,"lindsay.ferguson@reqres.in");
//		
//	         	}
	
	
	
	//Approach 4 - JSONObject class  -convert JSON object response to string format toString()
	
	@Test
	public void getAllUsersWithBodyData() {
		
		Response resp =
		  given()
		    .baseUri("https://reqres.in/api")
		    
		   .when()
		      .get("/users?page=2");
		  
		
	
		  JSONObject jo = new JSONObject(resp.toString());
		  boolean status=false;
		  for(int i=0;i<jo.getJSONArray("data").length();i++)
			  
		  {
			  String firstName=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			  System.out.println(firstName);
			  
			  if(firstName.equals("George")) {
				  status=true;
				  break;
			  }
		  }
		      
		Assert.assertEquals(status,true);
		
		
		
	}
	         	
	
	
	
	
	
	
	
	

}
