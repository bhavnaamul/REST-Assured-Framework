package com.testingapi.getrequest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import static io.restassured.response.Response.*;
import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.ast.builder.AstStringCompiler;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.testingapi.utils.FileNameConstant;

import groovy.json.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PostAPIRequestUsingFile {

	
	@Test
	public void postAPIReq() throws Exception {
		
	  String postAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.POST_API_REQUEST_BODY),"UTF-8");
	 // System.out.println(postAPIRequestBody);
	  
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
		  
		 .extract()
		  .response();
	  
	  JSONArray jsonArray = JsonPath.read(resp.body().asString(), "$.booking..firstname");
	  String fname=(String)jsonArray.get(0);
	  Assert.assertEquals(fname,"Bhavna");
	  
	  JSONArray lastName = JsonPath.read(resp.body().asString(), "$.booking..lastname");
	  String lname=(String)lastName.get(0);
	  Assert.assertEquals(lname,"Dargade");
	  
	  int bookingID = JsonPath.read(resp.body().asString(),"$.bookingid");
	  System.out.println("-----------" + bookingID);
	  
	  given()
		  .contentType(ContentType.JSON)
		  .baseUri("https://restful-booker.herokuapp.com/booking")
		  
		  .when()
		    .get("/{bookingID}",bookingID)
		    
		    
		   .then() 
		   .log().all()
		   .assertThat()
		   .statusCode(200);

	  
	}
}
