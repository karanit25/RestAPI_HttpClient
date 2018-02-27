package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetAPITest extends TestBase {
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

	@Test(priority=2)
	public void getAPItestWithoutHeaders() throws ClientProtocolException, IOException {
		// create an object of RestClient

		restClient = new RestClient();
		closablehttpresponse = restClient.get(url);
		
		// a. get Status code and print it
				int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("Status Code --->" + statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");

				// b. JSON string
				String responseString = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
				// we need to convert the response string into JSON object
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON GET API ---->" + responseJson);
				
				//Assertion for Single Value
				
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Per Page Value ---> "+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);

				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of Total is ---> "+ totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue) ,12);
				
				//Assertion for JSON Array values

				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				
				System.out.println(firstName);
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				
				// c. all Headers
				Header[] headerArray = closablehttpresponse.getAllHeaders();
				// we need to convert Header array to hashmap because its easy to
				// traverse as it uses key value pairs

				HashMap<String, String> allHeaders = new HashMap<String, String>();

				// with the help of for loop get name and value of header objects and
				// store it in hasmap
				for (Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}

				System.out.println("Header Array---->" + allHeaders);



				
	}

	@Test(priority=1)
	public void getAPItestWithHeaders() throws ClientProtocolException, IOException {
		// create an object of RestClient

		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		
		closablehttpresponse = restClient.get(url,headerMap);
		
		// a. get Status code and print it
				int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("Status Code --->" + statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");

				// b. JSON string
				String responseString = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
				// we need to convert the response string into JSON object
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON GET API ---->" + responseJson);
				
				//Assertion for Single Value
				
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Per Page Value ---> "+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);

				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of Total is ---> "+ totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue) ,12);
				
				//Assertion for JSON Array values

				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				
				System.out.println(firstName);
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				
				// c. all Headers
				Header[] headerArray = closablehttpresponse.getAllHeaders();
				// we need to convert Header array to hashmap because its easy to
				// traverse as it uses key value pairs

				HashMap<String, String> allHeaders = new HashMap<String, String>();

				// with the help of for loop get name and value of header objects and
				// store it in hasmap
				for (Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}

				System.out.println("Header Array---->" + allHeaders);



				
	}
	
}
