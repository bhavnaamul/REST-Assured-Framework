package com.testingapi.getrequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.core.StringContains;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.testautomation.testingapi.listeners.RestAssuredListener;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.response.Response.*;


public class getRequest {
	final Logger logger = LogManager.getLogger(getRequest.class);
//	@Test
//	public void getAllBookings() {
//	
//		//RestAssured  /// appear if we dont put static io.restassured.RestAssured.*;
//		
//		given()
//		  .contentType(ContentType.JSON)
//		  .baseUri("https://restful-booker.herokuapp.com/booking")
//		  
//		  .when()
//		    .get()
//		    
//		   .then()
//		     .assertThat()
//		     .statusCode(200);
//	}
	
//	@Test
//	public void getAllBookingsWithVanillaSyntax() {
//	
//		given()
//		  .contentType(ContentType.JSON)
//		 
//		  .when()
//		    .get("https://restful-booker.herokuapp.com/booking")
//		    
//		   .then()
//		     .statusCode(200);
//	         
//	}
	
	
	@Test
	public void getAllBookingsWithResponse() {
		
		
		Response resp=
		given()
		  .filter(new RestAssuredListener())
		  .contentType(ContentType.JSON)
		  .baseUri("https://restful-booker.herokuapp.com/booking")
		  
		  .when()
		    .get()
		    
		   .then()
		     .assertThat()
		     .statusCode(200)
		     .header("Content-Type" ,"application/json; charset=utf-8")
           .extract()
            .response();
		
		
		Assert.assertTrue(resp.getBody().asString().contains("bookingid"));
	}
	
	
}
