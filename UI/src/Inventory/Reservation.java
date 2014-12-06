package Inventory;

import java.sql.Timestamp;
import java.util.List;

public class Reservation {
	String studentId;
	String studentEmail;
	Timestamp due;
	String goingOutTime;
	List<InventoryItem> reservedItems;
	
	public Reservation(String sId, String sEmail, String time, Timestamp dueDate, List<InventoryItem> items) {
		studentId = sId;
		studentEmail = sEmail;
		due = dueDate;
		reservedItems = items;
		goingOutTime = time;
	}
	public String getStudentId() {
		return studentId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public Timestamp getDue() {
		return due;
	}
	public List<InventoryItem> getItems(){
		return reservedItems;
	}
	public void updateReservedItems(List<InventoryItem> items) {
		reservedItems = items;
	}
	public String getGoingOutTime(){
		return goingOutTime;
	}
}
