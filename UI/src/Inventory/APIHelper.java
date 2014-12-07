package Inventory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class APIHelper 
{
	String userId;
	String refNumber;
	ArrayList<String> equipmentName;
	String goingOutTime;
	Timestamp dueDateTime;
	
	public APIHelper(String id, String refNum, String got, Timestamp ddt) 
	{
		userId = id;
		equipmentName = new ArrayList<String>();
		refNumber = refNum;
		goingOutTime = got;
		dueDateTime = ddt;
	}
	public boolean checkId(String is) 
	{
		return is.equals(userId);
	}
	public void addEquipment(String item)
	{
		equipmentName.add(item);
	}
	public ArrayList<String> getEquipment()
	{
		return equipmentName;
	}
	public String getReferenceNumber()
	{
		return refNumber;
	}
	public String getGoingOutTime()
	{
		return goingOutTime;
	}
	public Timestamp getDueDateTime()
	{
		return dueDateTime;
	}
	
	public String toString()
	{
		return "UserID: "+userId + "\nReference Number: " + refNumber + "\nGoing out Time: "+goingOutTime +
				"\nDue Date Time: "+ dueDateTime +"\nStuff: " + equipmentName.toString();
	}
}