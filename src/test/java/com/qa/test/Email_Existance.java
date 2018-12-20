package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class Email_Existance extends TestBase {

	TestBase testBase = new TestBase();
	
	String Email_Exist_Url;
	
	String New_Account;
	
	RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	
	@Test(priority=1, enabled=true)
	
	public void Account_Already_Exists() throws ClientProtocolException, IOException
	{
		
		Email_Exist_Url = prop.getProperty("Email_Exist_Url");
		
		closebaleHttpResponse = restClient.get(Email_Exist_Url);

        int statuscode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status code --->"+statuscode);
		
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_422, "Status code is not 422");
		
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8"); //Json String
		
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("Response JSON from API--->" +responseJson);
		
		String error_summary = TestUtil.getValueByJPath(responseJson, "/error_summary"); //error_summary:
		System.out.println("Error summary is -->"+ error_summary);
		Assert.assertEquals(error_summary, "Account Already Exists");
	  
		String error_message = TestUtil.getValueByJPath(responseJson, "/error_message"); //error_message:
		System.out.println("Error message is -->"+ error_message);		
		Assert.assertEquals(error_message, "An account already exists for this email. Please login or register with a different email address."); 	

	}
	
	
	@Test(priority=2)
	
	public void New_Account() throws ClientProtocolException, IOException
	{
		New_Account = prop.getProperty("New_Account");
		
		closebaleHttpResponse = restClient.get(New_Account);
		
        int statuscode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status code --->"+statuscode);
		
	    String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8"); //Json String
		
		JSONObject responseJson = new JSONObject(responseString);
		
		System.out.println("Response JSON from API--->" +responseJson);
		
		String status = TestUtil.getValueByJPath(responseJson, "/status"); //error_summary:
		
		System.out.println("value of status is-->"+ status);
		
		Assert.assertEquals(Integer.parseInt(status), 200);
		
       String message = TestUtil.getValueByJPath(responseJson, "/message"); //error_summary:
		
		System.out.println("Messsage is-->"+ message);
		
		Assert.assertEquals(message, "SUCCESS");			
		
	}
		
	
}
