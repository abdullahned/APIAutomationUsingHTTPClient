package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class User_Registration extends TestBase {
	
	TestBase testBase = new TestBase();
	
	String user_register_url;
	
    RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	GET_Session getsession_obj = new GET_Session();
	
	Random r = new Random();
	String usernamee = "QA"+r.nextInt(100);
	String emaill = "Qtest"+r.nextInt(1000)+"@selfiestyler.com";
	
	@Test(priority=1, enabled=true)
	
	public void new_account_creation() throws ClientProtocolException, IOException
	{
		String token = getsession_obj.Create_Get_Session();
		
		System.out.println(token);
		
		user_register_url = prop.getProperty("user_register_url");
		
        HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer "+token);
		
		headerMap.put("Accept", "application/json");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev"); 
		
		JSONObject userJson = new JSONObject()
	             .put("username", usernamee)
	             .put("email", emaill)
	             .put("first_name", "test")
	             .put("last_name", "user")
	             .put("gender", "f")
	        	 .put("phone", "+15142546014");
		
		JSONObject val = new JSONObject()
				.put("value", "Germany0!");
		
		JSONObject passwordJson = new JSONObject().put("password", val);
		
	String testJson = new JSONObject()
        .put("device_id", "1234567890123456789012345678901234567890")
        .put("device_type", "iphone6")
        .put("is_subscribed", false)
        .put("api_version", "123")
        .put("user", userJson)
        .put("credentials", passwordJson).toString();  
	

//String usersJsonString2 ="{\"user\":{\"username\":\"test1019@selfiestyler.com\",\"email\":\"test20@selfiestyler.com\",\"first_name\":\"test\",\"last_name\":\"user\",\"gender\":\"f\",\"phone\":\"+15142546014\"},\"credentials\":{\"password\":{\"value\":\"Germany0!\"}},\"device_id\":\"1234567890123456789012345678901234567890\",\"device_type\":\"iphone6\",\"is_subscribed\":false,\"api_version\":\"123\"}";


//String qwerty = "{\r\n    \"user\": {\r\n        \"username\":\"pak\",\r\n        \"email\":\"test900@selfiestyler.com\",\r\n        \"first_name\": \"test\",\r\n        \"last_name\": \"user\",\r\n        \"gender\": \"f\",\r\n        \"phone\": \"+15142546014\"\r\n    },\r\n    \r\n    \"credentials\": {\r\n        \"password\" : { \"value\": \"Germany0!\" }\r\n    },\r\n    \r\n    \r\n    \r\n    \"device_id\": \"1234567890123456789012345678901234567890\",\r\n    \"device_type\":  \"iphone6\",\r\n    \"is_subscribed\": false,\r\n    \"api_version\":\"123\"\r\n}";
	
	   		
	  System.out.println("\n  Request "+testJson+"\n");
	   
	   closebaleHttpResponse = restClient.post(user_register_url, testJson, headerMap); //call the API
	   
	 //validate response from API:
	//1. status code:
	  int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
	 	    
	  System.out.println("Value of status code is: "+ statusCode +"\n");
	 	    
	  Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
	   
	 //2. JsonString:
	   String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
	 				
	   JSONObject responseJson = new JSONObject(responseString);
	 		
	    System.out.println("The response from API is:"+ responseJson +"\n");

        String first_name = TestUtil.getValueByJPath(responseJson, "/user/first_name");
		
		System.out.println("First Name is-->"+ first_name +"\n");
		
		Assert.assertEquals(first_name, "test");
		
        String last_name = TestUtil.getValueByJPath(responseJson, "/user/last_name");
		
		System.out.println("Last Name is-->"+ last_name +"\n");
		
		Assert.assertEquals(last_name, "user");	      
		
	}
	
	
	@Test(priority=2, enabled=false)
	
	public void Account_Already_Exist() throws ClientProtocolException, IOException
	{
		String token = getsession_obj.Create_Get_Session();
		
		System.out.println(token);
		
		user_register_url = prop.getProperty("user_register_url");
		
        HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer "+token);
		
		headerMap.put("Accept", "application/json");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev"); 
		
		JSONObject userJson = new JSONObject()
	             .put("username","test00020@selfiestyler.com")
	             .put("email","test00020@selfiestyler.com")
	             .put("first_name", "test")
	             .put("last_name", "user")
	             .put("gender", "f")
	        	 .put("phone", "+15142546014");
		
		JSONObject val = new JSONObject()
				.put("value", "Germany0!");
		
		JSONObject passwordJson = new JSONObject().put("password", val);
		
	String testJson = new JSONObject()
        .put("device_id", "1234567890123456789012345678901234567890")
        .put("device_type", "iphone6")
        .put("is_subscribed", false)
        .put("api_version", "123")
        .put("user", userJson)
        .put("credentials", passwordJson).toString();  
	   		
	  System.out.println("\n  Request "+testJson+"\n");
	   
	   closebaleHttpResponse = restClient.post(user_register_url, testJson, headerMap); //call the API
	   
	  int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode(); //validate response from API:
	 	    
	  System.out.println("Value of status code is: "+ statusCode +"\n"); 	//1. status code:
	 	    
	  Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_422);
	   
	 //2. JsonString:
	   String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
	 				
	   JSONObject responseJson = new JSONObject(responseString);
	 		
	    System.out.println("The response from API is:"+ responseJson +"\n");

        String error_message = TestUtil.getValueByJPath(responseJson, "/error_message");
		
		System.out.println("Error message is-->-->"+ error_message +"\n");
		
		Assert.assertEquals(error_message, "An account already exists for this username. Please login or register with a different username.");
		
        String error_summary = TestUtil.getValueByJPath(responseJson, "/error_summary");
		
		System.out.println("Error summary is-->"+ error_summary +"\n");
		
		Assert.assertEquals(error_summary, "Account Already Exists");	      
		
	}

}
