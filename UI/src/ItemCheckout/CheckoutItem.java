package ItemCheckout;

import java.sql.*;

public class CheckoutItem {
  String studentId;
  String studentEmail;
  String employeeId;
  String itemId;
  Timestamp time;
  Timestamp dueDate;

  public CheckoutItem(String sId, String sEmail,String eId,String iId, Timestamp currentTime, Timestamp due) {
    studentId = sId; 
    studentEmail = sEmail;
    employeeId = eId;
    itemId = iId;
    time = currentTime;
    dueDate = due;
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
  public Timestamp getTime() {
    return time;
  }
  public Timestamp getDueDate() {
    return dueDate;
  }
}