package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.user_registration;

public class User_Registration extends TestBase {
	
	TestBase testBase = new TestBase();
	
	String user_register_url;
	
    RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	GET_Session getsession_obj = new GET_Session();
	
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
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		
		user_registration user_registration_obj = new user_registration("abdullahned","mabdullah@selfiestyler.com",
				"muhammad", "Abdullah", "M", "+15142546014", "Germany0!", "124", "iphone6", "false", "123");
		
		//object to json file:
		mapper.writeValue(new File("C:\\Users\\m.abdullah\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\user_registration.json"), user_registration_obj);
		
		//java object to json in String:
	   String usersJsonString = mapper.writeValueAsString(user_registration_obj); //marshalling
				
	   System.out.println("\n"+usersJsonString+"\n");
	   
	   closebaleHttpResponse = restClient.post(user_register_url, usersJsonString, headerMap); //call the API
	   
	 //2. JsonString:
	   String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
	 				
	   JSONObject responseJson = new JSONObject(responseString);
	 		
	    System.out.println("The response from API is:"+ responseJson +"\n");
		
		
	}

}
