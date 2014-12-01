package Inventory;

import java.sql.Timestamp;
import java.util.*;

public class Mockapi {
	List<InventoryItem> items;
	public Mockapi(){
		items = new ArrayList<InventoryItem>();
	}
	public Reservation getUser(String sid) {
		if (sid.equals("mike")) {
			items.add(new InventoryItem("1000","Camera 1", "out", "Clean the lense"));
			return new Reservation("mike","mike@gmail.com",Timestamp.valueOf("2014-11-20 19:00:00"), items);	
		}
		else{
			items.add(new InventoryItem("1011","Camera 3", "in", "Check Batteries"));
			return new Reservation("kyel","kyle@gmail.com",Timestamp.valueOf("2014-11-20 19:00:00"), items);	
		}
	}
}
