package com.testingapi.getrequest;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.testingapi.utils.FileNameConstant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutAPIRequest {

	//Bhavna's script
	@Test
	public void PutAPIReq() throws Exception {

		String TokenAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.TOKEN_API_REQUEST_BODY),"UTF-8");
		
		String postAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.POST_API_REQUEST_BODY),"UTF-8");
		// System.out.println(postAPIRequestBody);

		String putAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstant.PUT_API_REQUEST_BODY),"UTF-8");
		//System.out.println(patchAPIRequestBody);

		//post script   
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


		int bookingID = JsonPath.read(resp.body().asString(),"$.bookingid");
		System.out.println("-----------" + bookingID);



		//get
		given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.get("/{bookingId}",bookingID)
		.then()
		.assertThat()
		.statusCode(200);

		
		//Authentication token script
		Response tokenAPIResponse =
				given()
				.contentType(ContentType.JSON)
				.body(TokenAPIRequestBody)
				.baseUri("https://restful-booker.herokuapp.com/auth")


				.when() 
				.post()

				.then() 
				.assertThat()
				//.statusCode(201)

				.extract()
				.response();



		String tokenID = JsonPath.read(tokenAPIResponse.body().asString(),"$.token");
		//int tokID = Integer.parseInt(tokenID);
		System.out.println("-----------" + tokenID);

//
		//Put detail for that ID
		
		given()
		.contentType("application/json")
		.body(putAPIRequestBody)
		.header("Cookie", "token="+tokenID)
		.baseUri("https://restful-booker.herokuapp.com/booking")

		.when()
		.put("/{bookingId}",bookingID)

		.then()
		.assertThat()
		//.statusCode(201)
		.log().all();


	}

}
