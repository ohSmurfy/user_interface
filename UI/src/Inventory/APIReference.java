package Inventory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.*;


public class APIReference 
{
	// private String userName = "sam";
	//private String password = "password";

	
	public APIReference()
	{
		
	}
	
	public void getReservations()
	{
		HttpClient client = new DefaultHttpClient();
		String baseUrl = "https://pro.lib.uni.edu/booked/Web/Services/index.php/";
		String authenticateUrl = "Authentication/Authenticate";
		String reservationUrl = "Reservations/?startDateTime/2014-12-07T00:00:00-0600";//&endDateTime/2014-12-17T19:25:30-0600";
	    HttpPost post = new HttpPost(baseUrl+authenticateUrl);
	    
	    String creds = "{\"username\": \"fishesac\",\"password\": \"subarustisarefast\"}";
	    try 
	    {
	    	StringEntity e = new StringEntity(creds,HTTP.UTF_8);
	    	e.setContentType("application/json");
	        post.setEntity(e);
	   
	        HttpResponse response = client.execute(post);
	        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        JSONObject jo = new JSONObject(rd.readLine());
	        String sessionToken = jo.get("sessionToken").toString();
	        String userId = jo.get("userId").toString();
	        
//		    String header = "{\"X-phpScheduleIt-SessionToken\": \""+jo.get("sessionToken").toString()+","
//		    		+ "\"X-phpScheduleIt-UserId\": "+jo.get("userId").toString()+"}";
		    //post.releaseConnection();
	        
		    HttpGet getter = new HttpGet(baseUrl+reservationUrl);
		    getter.addHeader("X-Booked-SessionToken",sessionToken);
		    getter.addHeader("X-Booked-UserId", userId);
		    
		    //"startDate":"2014-12-04T05:00:00+0000","endDate":"2014-12-04T06:00:00+0000"
		    
		    //X-Booked-SessionToken
		    
//		    for(org.apache.http.Header i: response.getAllHeaders() )
//		    {
//		    	java.lang.System.out.println(i.getName());
//		    	java.lang.System.out.println(i.getValue());
//		    }
		    response = client.execute(getter);
		    BufferedReader reservationReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    
		    java.lang.System.out.println(reservationReader.readLine());
		    java.lang.System.out.println("Something");
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (JSONException e) 
	    {
	        e.printStackTrace();
	    }
	}
}
