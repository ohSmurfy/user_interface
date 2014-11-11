package Inventory;

import java.sql.Timestamp;
import java.util.*;

public class Mockapi {
	List<InventoryItem> items;
	public Mockapi(){
		
	}
	public Reservation getUser(String sid) {
		if (sid == "mike") {
			items.add(new InventoryItem("1000","Camera 1", "in", "Check Batteries"));
			items.add(new InventoryItem("1010","Camera 2", "in", "Check Batteries"));
			return new Reservation("mike","mike@gmail.com",Timestamp.valueOf("2014-10-11 12:00:00"), items);	
		}
		else return new Reservation("jim","bob@gmail.com",Timestamp.valueOf("2014-10-11 12:00:00"), items);	
	}
}
