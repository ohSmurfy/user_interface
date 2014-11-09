package Inventory;

import java.sql.*;

public class CheckoutItem {
  String studentId;
  String studentEmail;
  String employeeId;
  String itemId;
  String itemDiscription;
  Timestamp time;
  Timestamp dueDate;

  public CheckoutItem(String sId, String sEmail,String eId,String iId, String iDiscription, Timestamp currentTime, Timestamp due) {
    studentId = sId; 
    studentEmail = sEmail;
    employeeId = eId;
    itemId = iId;
    itemDiscription = iDiscription;
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
  public String getItemDiscription() {
	return itemDiscription;
  }
  public Timestamp getTime() {
    return time;
  }
  public Timestamp getDueDate() {
    return dueDate;
  }
}
