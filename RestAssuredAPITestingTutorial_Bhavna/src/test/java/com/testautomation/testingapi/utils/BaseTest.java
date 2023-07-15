package com.testautomation.testingapi.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.testingapi.getrequest.POSTRequest;

import io.restassured.RestAssured;

public class BaseTest  {
	
	final Logger logger = LogManager.getLogger(BaseTest.class);
	
	@BeforeTest
	public void BeforeMethod() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE)
				{
			Throwable t = result.getThrowable();
			StringWriter error = new StringWriter();
			t.printStackTrace(new PrintWriter(error));
			logger.info(error.toString());
				}
	}

}
