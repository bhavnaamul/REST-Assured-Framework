package com.testingapi.getrequest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAPIrequests {
	
		@Test
		public void getAllBookings() {
		
			//RestAssured  /// appear if we dont put static io.restassured.RestAssured.*;
			Response response=
			given()
			  .contentType(ContentType.JSON)
			  .baseUri("https://restful-booker.herokuapp.com/booking")
			  
			  .when()
			    .get()
			    
			    .then()
			     .assertThat()
			     .statusCode(200)
			      .statusLine("HTTP/1.1 200 OK")
			      .extract()
			       .response();
			
			Assert.assertTrue(response.getBody().asString().contains("bookingid"));
		  		}

}
