package Inventory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JOptionPane;

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
	private String username = "";
	private String studentScheduleIdId = "";
	private String password = "";
	private ArrayList todaysReferenceNumbers = new ArrayList();
	private ArrayList<APIHelper> users = new ArrayList<APIHelper>();
	
	Date date =new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	HttpClient client = new DefaultHttpClient();
	String baseUrl = "https://pro.lib.uni.edu/booked/Web/Services/index.php/";
	String authenticateUrl = "Authentication/Authenticate";
	String reservationUrl = "Reservations/?scheduleId=2&startDateTime="+ sdf.format(date);
	String reservationUrl2 = "Reservations/";
    HttpPost post = new HttpPost(baseUrl+authenticateUrl);
    String creds = "{\"username\": \""+ username + "\",\"password\": \""+ password+"\"}";
    String sessionToken;
    String userId;
    JSONObject jo;
    BufferedReader rd;
    HttpResponse response;
    
	public APIReference()
	{
		StringEntity e = new StringEntity(creds,HTTP.UTF_8);
    	e.setContentType("application/json");
        post.setEntity(e);
        try
        {
        	response = client.execute(post);
        	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        	jo = new JSONObject(rd.readLine());
        	sessionToken = jo.get("sessionToken").toString();
        	userId = jo.get("userId").toString();
        }
        catch (IOException a) 
	    {
	        a.printStackTrace();
	    }
	    catch (JSONException a) 
	    {
	        a.printStackTrace();
	    }
	}
	
	public ArrayList<Reservation> getReservations() throws ParseException 
	{
		ArrayList<Reservation> reservations = new  ArrayList<Reservation>();

	    
	    try 
	    {
	    	
	        
	        
		    HttpGet getReservationsCall = new HttpGet(baseUrl+reservationUrl);
		    getReservationsCall.addHeader("X-Booked-SessionToken",sessionToken);
		    getReservationsCall.addHeader("X-Booked-UserId", userId);

		    response = client.execute(getReservationsCall);
		    BufferedReader reservationReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    JSONObject returningReservations = new JSONObject(reservationReader.readLine());
		    
		    String reservationDate = "";
		    String checkoutTime = "";
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
		    
		    
		    JSONArray jsArray = returningReservations.getJSONArray("reservations");

		    for(int i =0; i< jsArray.length(); i++)
		    {
		    	Calendar cal = Calendar.getInstance(); // creates calendar h:mm a
		       
				cal.setTime(formatter.parse(((JSONObject) jsArray.get(i)).get("startDate").toString().replace('T', ' ').substring(0, 19)));
				
				cal.add(Calendar.HOUR_OF_DAY, -6);
				reservationDate = sdf.format(cal.getTime());
				checkoutTime = timeFormat.format(cal.getTime());
			
		        if (reservationDate.equals(sdf.format(date)))
		        {
		        	String endDateTime = "";
			       
					cal.setTime(formatter.parse(((JSONObject) jsArray.get(i)).get("endDate").toString().replace('T', ' ').substring(0, 19)));
					cal.add(Calendar.HOUR_OF_DAY, -6);
					endDateTime = formatter.format(cal.getTime());
					
			        
			        Timestamp ts = Timestamp.valueOf(endDateTime);
		   
			        String refNum = ((JSONObject) jsArray.get(i)).get("referenceNumber").toString();
		    		String reservationUserId = ((JSONObject) jsArray.get(i)).get("userId").toString();
		    		String equipmentName = ((JSONObject) jsArray.get(i)).get("resourceName").toString();
		    		todaysReferenceNumbers.add(refNum);
		        
		        
		    		Boolean foundMatch = false;
		    		for(int n = 0;n<users.size();n++)
		    		{
		    			if(users.get(n).checkId(reservationUserId))
		    			{
		    				users.get(n).addEquipment(equipmentName);
		    				foundMatch = true;
		    			}
		    		}
		    		
		    		if( (!foundMatch) || users.size() == 0)
		    		{
		    			APIHelper temp = new APIHelper(reservationUserId,refNum, checkoutTime, ts);
		    			temp.addEquipment(equipmentName);
		    			users.add(temp);
		    		}
		        }
		    }

  
		    for(int i = 0;i<users.size();i++)
		    {
		    	String refNum = (String) users.get(i).getReferenceNumber();
		    	
		    	HttpGet getReservationInfoCall = new HttpGet(baseUrl+reservationUrl2+refNum);
		    	getReservationInfoCall.addHeader("X-Booked-SessionToken",sessionToken);
		    	getReservationInfoCall.addHeader("X-Booked-UserId", userId);
			    
			    response = client.execute(getReservationInfoCall);
			    BufferedReader reservationInfoReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			    JSONObject reservationInfo = new JSONObject(reservationInfoReader.readLine());
			    
			    String email = reservationInfo.getJSONObject("owner").get("emailAddress").toString();
			    
			    String[] parts = email.split("@");
			    String catID = parts[0];
			    ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
			    
			    
			    for(String itemName: users.get(i).getEquipment())
			    {
			    	try 
			    	{
			    		SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
			    		items.add(inventory.getItemByItemName(itemName));
			    		inventory.close();
			    		
			   	    } 
			    	catch (SQLException ex) 
			    	{
			    		
			   	    } 
			    	catch (ItemException ex)
			    	{
			    		
			   	    }
			    }
			    
			  reservations.add(new Reservation(catID,
					  email,
					  users.get(i).getGoingOutTime(),
					  users.get(i).getDueDateTime(),
					  items));
		    }
		    
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (JSONException e) 
	    {
	        e.printStackTrace();
	    }
	    return reservations;
	}
}
