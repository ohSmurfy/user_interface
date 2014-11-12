package Inventory;

import java.sql.Timestamp;
import java.util.*;

public class Mockapi {
	List<InventoryItem> items;
	public Mockapi(){
		items = new ArrayList<InventoryItem>();
		items.add(new InventoryItem("1011","Camera 3", "in", "Check Batteries"));

	}
	public Reservation getUser(String sid) {
		if (sid.equals("mike")) {
			items.add(new InventoryItem("1000","Camera 1", "out", "Clean the lense"));
			items.add(new InventoryItem("1010","Camera 2", "out", "Check batteries"));
			return new Reservation("mike","mike@gmail.com",Timestamp.valueOf("2014-10-11 12:00:00"), items);	
		}
		else return new Reservation("jim","bob@gmail.com",Timestamp.valueOf("2014-10-11 12:00:00"), items);	
	}
}
