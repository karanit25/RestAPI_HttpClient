package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method
	// CreateDefault method will create a client connection and will return closabale HttpClient object
	// HttpGet will create connection with provided URL

	// GET Method Without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);
		// hit the GET url and store response in an object

		return closableHttpResponse;
	}

	// GET Method With Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);
		// hit the GET url and store response in an object

		return closableHttpResponse;
	}
	
	//POST method
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		//call constructor to create the httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(url); // http POST request by passing URL (our first parameter)
		httppost.setEntity(new StringEntity(entityString)); //for Playload (our second parameter)
		
		//for Headers (our third parameter)
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httppost);
		// hit the POST method with all the prepared arguments by httppost variable and store response in an object

		return closableHttpResponse;
		
	}

}
