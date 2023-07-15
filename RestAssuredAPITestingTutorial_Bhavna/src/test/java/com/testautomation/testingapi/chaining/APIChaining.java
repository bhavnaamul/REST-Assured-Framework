package com.testautomation.testingapi.chaining;


import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testautomation.testingapi.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;


public class APIChaining extends BaseTest{
	
	@Test
	public void createBookings() {
		
		JSONObject bookings =new JSONObject();
		JSONObject bookingDates= new JSONObject();
		
		bookings.put("firstname","Bhavna's");
		bookings.put("lastname","Tutorial");
		bookings.put("totalprice",1000);
		bookings.put("depositpaid",true); 
		bookings.put("additionalneeds","Breakfast");
		bookings.put("bookingdates",bookingDates);
		
		bookingDates.put("checkin","2023-07-04");
		bookingDates.put("checkout","2023-07-07");
		
		Response res=
		given()
		  .contentType(ContentType.JSON)
		  .body(bookings.toString())
		  .baseUri("https://restful-booker.herokuapp.com/booking")
	
		 .when() 
		   .post()
		 
		 .then() 
		   .assertThat()
		   .statusCode(200)
		 
		  .extract()
		    .response();
		
		int bookID = res.path("bookingid");
		System.out.println("----Unique key for Employee when booking--------" + bookID);
		
		given()
		 // .contentType(ContentType.JSON)
		  .pathParam("bookingID","bookID")
		  .baseUri("https://restful-booker.herokuapp.com/booking")
		  
		  .when()
		    .get("/{bookingID}")
		    
		    
		   .then() 
		   .log().all()
		      .assertThat() 
		      .statusCode(200);
	
	}
			
				

}
