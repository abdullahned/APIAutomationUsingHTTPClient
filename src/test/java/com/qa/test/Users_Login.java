package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Login_Credential;
import com.qa.data.Users;


public class Users_Login extends TestBase {

	TestBase testBase = new TestBase();
	
	String Users_login_Url;
	
	RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	@Test(priority=3, enabled=true)
	
	public void valid_credentials() throws JsonGenerationException, JsonMappingException, IOException
	{
		Users_login_Url = prop.getProperty("Users_login_Url");
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Authorization", "Bearer 37adf0cb-e568-8a4a-a2ea-ea058247b79a");
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("SSDEBUG", "SelfieStylerDev");
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		
		Login_Credential Login_Credential_obj = new Login_Credential("memtestwomen@ss.com","Bilal123"); //expected users obejct
				
		//object to json file:
		mapper.writeValue(new File("C:\\Users\\m.abdullah\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\Login_Credential.json"), Login_Credential_obj);
		
		//java object to json in String:
		String usersJsonString = mapper.writeValueAsString(Login_Credential_obj); //marshalling
		
		System.out.println(usersJsonString);
		
		closebaleHttpResponse = restClient.post(Users_login_Url, usersJsonString, headerMap); //call the API
		
		//2. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("The response from API is:"+ responseJson);
		
		//json to java object:
		Login_Credential Login_Res_Obj = mapper.readValue(responseString, Login_Credential.class); //actual users object , un marshalling
		
		System.out.println(Login_Res_Obj);
		
		
		
		
	}
	
	
	
	
	
}
