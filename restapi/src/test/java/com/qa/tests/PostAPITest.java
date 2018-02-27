package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

import junit.framework.Assert;

public class PostAPITest extends TestBase {
	TestBase testBase;
	String baseUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closablehttpresponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		// call the constructor of base class so that peoperties means prop
		// object will be initialized
		// with the help of prop object get URL

		testBase = new TestBase();
		baseUrl = prop.getProperty("BaseURL");
		apiUrl = prop.getProperty("serviceURL");
		url = baseUrl + apiUrl;

	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		// create an object of RestClient
		restClient = new RestClient();
		//Prepare header - In case of multiple header list down all the headers like Username, password, Token etc
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		
		//Jackson API
		//Create an object of ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		//Create and object of Users class
		Users users = new Users("Karan","SW Engg"); //----->Expected User Object
		
		//Java Object to JSON file creation by passing dummy file path and users object
		//at this point dummy File will be written with JSON key value pair format
		mapper.writeValue(new File("/Users/karan/workspace/restapi/src/main/java/com/qa/data/Users.json"), users);
		//Object to JSON in String conversion and save it in a variable
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		//make a call to POST method
		
		closablehttpresponse = restClient.post(url, userJsonString, headerMap);

		//1.Perform validataion for status code
		int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
		//TestNG Assertion
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
		
		//2. JSON String
		String responseString = EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("This is JSON object - payload from API response"+responseJson);
		
		//Now we need to validate that created JSON contents are same as generate JSON file contents
		//for that we need to do Unmarshalling means 
		//JSON to java object comparison with the help of mapper
		
		Users userResObj = mapper.readValue(responseString, Users.class); //----->Actual User Object
		System.out.println(userResObj);
		
		//Now compare Expected User Object and Actual User Object values if both are same TC is passed otherwise fail
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
		
		System.out.println("Created Date---->"+userResObj.getCreatedAt());
		System.out.println("Generated Id---->"+userResObj.getId());
	}

}
