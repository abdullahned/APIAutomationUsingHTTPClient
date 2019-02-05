package com.qa.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Login_Credential;
import com.qa.data.Users;
import com.qa.util.TestUtil;


public class Users_Login extends TestBase {

	TestBase testBase = new TestBase();
	
	String Users_login_Url;
	
	RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	@Test(priority=1, enabled=true)
	
	public void valid_credentials() throws JsonGenerationException, JsonMappingException, IOException
	{
		Users_login_Url = prop.getProperty("Users_login_Url");
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer 8f99243e-f3f1-cd9a-2c87-573ae3ebcf68");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev");
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		
		Login_Credential Login_Credential_obj = new Login_Credential("memtestwomen@ss.com","Bilal123"); //expected users obejct
				
		//object to json file:
		mapper.writeValue(new File("C:\\Users\\m.abdullah\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\Login_Credential.json"), Login_Credential_obj);
		
		//java object to json in String:
		String usersJsonString = mapper.writeValueAsString(Login_Credential_obj); //marshalling
		
		System.out.println("\n"+usersJsonString+"\n");
		
		closebaleHttpResponse = restClient.post(Users_login_Url, usersJsonString, headerMap); //call the API
		
		//validate response from API:
		//1. status code:
	    int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
	    
	    System.out.println("Value of status code is: "+ statusCode +"\n");
	    
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		
		//2. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("The response from API is:"+ responseJson +"\n");
		
		String email = TestUtil.getValueByJPath(responseJson, "/user/email");
		
		System.out.println("Email address is-->"+ email +"\n");
		
		Assert.assertEquals(email, "memtestwomen@ss.com");
		
        String is_calibrated = TestUtil.getValueByJPath(responseJson, "/user/is_calibrated");
		
		System.out.println("value of calibrated is-->"+ is_calibrated +"\n");
		
		Assert.assertEquals(is_calibrated, "false");
		
	}
	
	
	@Test(priority=2, enabled=true)
	
	public void Invalid_Password() throws JsonGenerationException, JsonMappingException, IOException
	{
		Users_login_Url = prop.getProperty("Users_login_Url");
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer 37adf0cb-e568-8a4a-a2ea-ea058247b79a");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev");
		
		ObjectMapper mapper = new ObjectMapper(); //jackson API:
		
		Login_Credential Login_Credential_obj = new Login_Credential("memtestwomen@ss.com","Bilal127"); //expected users obejct
				
		//object to json file:
		mapper.writeValue(new File("C:\\Users\\m.abdullah\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\Login_Credential.json"), Login_Credential_obj);
	
		String usersJsonString = mapper.writeValueAsString(Login_Credential_obj); //marshalling //java object to json in String:
		
		System.out.println(usersJsonString+"\n");
		
		closebaleHttpResponse = restClient.post(Users_login_Url, usersJsonString, headerMap); //call the API
		
	    int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode(); //validate response from API:
	    
	    System.out.println("Value of status code is: "+ statusCode+"\n"); //1. status code:
	    
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_422);
		
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8"); //2. JsonString:
				
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("The response from API is:"+ responseJson+"\n");
		
		String error_message = TestUtil.getValueByJPath(responseJson, "/error_message");
		
		System.out.println("Error message is-->"+ error_message+"\n");
		
		Assert.assertEquals(error_message, "Invalid username or password.");			
	}
	
    @Test(priority=3, enabled=true)
	
	public void Invalid_Email() throws JsonGenerationException, JsonMappingException, IOException
	{
		Users_login_Url = prop.getProperty("Users_login_Url");
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer 37adf0cb-e568-8a4a-a2ea-ea058247b79a");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev");
		
		ObjectMapper mapper = new ObjectMapper(); //jackson API:
		
		Login_Credential Login_Credential_obj = new Login_Credential("testwomen@ss.com","Bilal127"); //expected users obejct
				
		//object to json file:
		mapper.writeValue(new File("C:\\Users\\m.abdullah\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\Login_Credential.json"), Login_Credential_obj);
	
		String usersJsonString = mapper.writeValueAsString(Login_Credential_obj); //marshalling //java object to json in String:
		
		System.out.println(usersJsonString+"\n");
		
		closebaleHttpResponse = restClient.post(Users_login_Url, usersJsonString, headerMap); //call the API
		
	    int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode(); //validate response from API:
	    
	    System.out.println("Value of status code is: "+ statusCode+"\n"); //1. status code:
	    
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_422);
		
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8"); //2. JsonString:
				
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("The response from API is:"+ responseJson+"\n");
		
		String error_message = TestUtil.getValueByJPath(responseJson, "/error_message");
		
		System.out.println("Error message is-->"+ error_message+"\n");
		
		Assert.assertEquals(error_message, "Account not found. Please try again.");			
	}

	
}
