package Inventory;

import java.sql.*;

public class CheckoutItem {
  String studentId;
  String studentEmail;
  String employeeId;
  String itemId;
  String itemDescription;
  Timestamp time;
  Timestamp dueDate;

  public CheckoutItem(String sId, String sEmail,String eId,String iId, String iDescription, Timestamp currentTime, Timestamp due) {
    studentId = sId; 
    studentEmail = sEmail;
    employeeId = eId;
    itemId = iId;
    itemDescription = iDescription;
    time = currentTime;
    dueDate = due;
  }
  
  public void updateEmployeeId(String id) {
	  employeeId = id;
  }
  
  public String getStudentId() {
    return studentId;
  }
  public String getStudentEmail() {
    return studentEmail;
  }
  public String getEmployeeId() {
    return employeeId;
  }
  public String getItemId() {
    return itemId;
  }
  public String getItemDescription() {
	return itemDescription;
  }
  public Timestamp getTime() {
    return time;
  }
  public Timestamp getDueDate() {
    return dueDate;
  }
}
