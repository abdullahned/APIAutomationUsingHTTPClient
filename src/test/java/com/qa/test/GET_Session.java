package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GET_Session extends TestBase {

	TestBase testBase = new TestBase();
	
	String Session_url;
	
    RestClient restClient = new RestClient() ;
	
	CloseableHttpResponse closebaleHttpResponse;
	
	
	public String Create_Get_Session() throws ClientProtocolException, IOException
	{
		
    	Session_url = prop.getProperty("GET_Session_url");
		
		closebaleHttpResponse = restClient.get(Session_url);

        int statuscode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
	//	System.out.println("Status code --->"+statuscode);
		
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8"); //Json String
		
		//JSONObject responseJson = new JSONObject(responseString);
		
	//	System.out.println("Response JSON from API--->" +responseString);	
		
		String expected = responseString.substring(15, 51);
		
	  //  System.out.println(expected);
	    
	    return expected;
	      

	}

}
