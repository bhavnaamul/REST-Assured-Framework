package com.testingapi.getrequest;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.testautomation.testingapi.listeners.RestAssuredListener;
import com.testautomation.testingapi.utils.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
public class POSTRequest extends BaseTest{
	
	@Test
	public void createBookings() {
		
		final Logger logger = LogManager.getLogger(POSTRequest.class);
		
		logger.info("Execution started......");
		
		JSONObject bookings =new JSONObject();
		JSONObject bookingDates= new JSONObject();
		
		bookings.put("firstname","API testing");
		bookings.put("lastname","Tutorial");
		bookings.put("totalprice",2000);
		bookings.put("depositpaid",true); 
		bookings.put("additionalneeds","Breakfast");
		bookings.put("bookingdates",bookingDates);
		
		bookingDates.put("checkin","2023-07-04");
		bookingDates.put("checkout","2023-07-07");
		
		logger.info("POST request started......");
		
		given()
		 .filter(new RestAssuredListener())
		.contentType(ContentType.JSON)
		 .body(bookings.toString())
		 .baseUri("https://restful-booker.herokuapp.com/booking")
//		 .log().body()
//		 .log().headers()
//		 .log().all()
		 
		 .when() 
		  .post()
		 
		 .then() 
		  .assertThat()
		//  .log().all()
		   .statusCode(200)
		  .body("booking.firstname", Matchers.equalTo("API testing"))
		  .body("booking.lastname", Matchers.equalTo("Tutorial"))
		  .body("booking.bookingdates.checkin", Matchers.equalTo("2023-07-04"));
		
		logger.info("Execution ended......");
		
		
	}
			
				

}
