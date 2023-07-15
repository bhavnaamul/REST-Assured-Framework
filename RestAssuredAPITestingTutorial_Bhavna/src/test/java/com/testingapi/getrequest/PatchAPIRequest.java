package com.testingapi.getrequest;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.testingapi.utils.FileNameConstant;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PatchAPIRequest {
	
	@Test
	public void patchAPIReq() throws Exception {
		
	  String postAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.POST_API_REQUEST_BODY),"UTF-8");
	 // System.out.println(postAPIRequestBody);
	  
	  String patchAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.PATCH_API_REQUEST_BODY),"UTF-8");
	  //System.out.println(patchAPIRequestBody);
	  
	  Response resp=
	  given()
		.contentType(ContentType.JSON)
		.body(postAPIRequestBody)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		 
	 .when() 
		  .post()
		 
	 .then() 
		  .assertThat()
		  .statusCode(200)
		  .log().all()
		  
	 .extract()
		  .response();
		 
	  
	  	  
	  int bookingID = JsonPath.read(resp.body().asString(),"$.bookingid");
	  System.out.println("-----------" + bookingID);
	  
	  
		   
		   

	  given()
	  .contentType(ContentType.JSON)
	  .baseUri("https://restful-booker.herokuapp.com/booking")
	  
	  .when()
	    .get("/{bookingID}",bookingID)  
	    
	    .then() 
		   .assertThat()
		   .statusCode(200)
		   .log().all();
	  
	  
	  
	
	  
	  //Patch detail for that ID
	  given()
		  .contentType("application/json")
		  .body(patchAPIRequestBody)
		  .baseUri("https://restful-booker.herokuapp.com/booking")
		  
		  .when()
		    .patch("{bookingID}",bookingID)
		    
		    
		   .then() 
		   .assertThat()
		   .log().all();
	}

}
