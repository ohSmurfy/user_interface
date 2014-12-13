package Inventory;

import java.sql.*;
import java.util.*;

public class SQLCheckoutItemRepo {
  Connection dbCon;

  public SQLCheckoutItemRepo() throws SQLException {
    create();
  }
  
  public void insertNewItem(CheckoutItem item) throws SQLException {
    String query = "insert into checkout(" +
        "studentId, studentEmail, employeeId, itemId, itemDescription, dueDate)" +
        "values(?,?,?,?,?,?)";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, item.getStudentId());
    stmt.setString(2, item.getStudentEmail());
    stmt.setString(3, item.getEmployeeId());
    stmt.setString(4, item.getItemId());
    stmt.setString(5, item.getItemDescription());
    stmt.setTimestamp(6, item.getDueDate());
    stmt.executeUpdate();
    }
  
  public List<String> getStudentIds(java.sql.Date date) throws SQLException {
	  String query = "select DISTINCT studentId from checkout where Date(dueDate) = ?";
	  PreparedStatement stmt = connect().prepareStatement(query);
	  stmt.setDate(1, date);
	  ResultSet rs = stmt.executeQuery();
	  List<String> ids = new ArrayList<String>();   
	  while (rs.next()) ids.add(rs.getString("studentId"));
	  return ids;
	  
  }
  public List<CheckoutItem> getByDueDate(java.sql.Date date) throws SQLException {
    String query = "select * from checkout where DATE(dueDate) = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setDate(1, date);
    ResultSet rs = stmt.executeQuery();
    return makeCheckoutList(rs);
  }
  
  public List<CheckoutItem> getAll() throws SQLException {
    String query = "select * from checkout";
    ResultSet rs = connect().prepareStatement(query).executeQuery();
    return makeCheckoutList(rs);
  }
  public List<CheckoutItem> getByStudentId(String id) throws SQLException {
    String query = "select * from checkout where studentId = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    ResultSet rs = stmt.executeQuery();
    return makeCheckoutList(rs);
  }

  public List<CheckoutItem> getByItemId(String id) throws SQLException {
    String query = "select * from checkout where itemId = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    ResultSet rs = stmt.executeQuery();
    return makeCheckoutList(rs);
  }

  public void deleteCheckoutItemByItemId(String id) throws SQLException {
    String query = "delete from checkout where itemId = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    stmt.executeUpdate();
  }
  
  public void close() throws SQLException{
	  dbCon.close();
  }
  private Connection connect() throws SQLException{
	if (dbCon == null) {
	  String dbURL = "jdbc:mysql://localhost/inventory";
	  String username ="root";
      String password = "password";	
      dbCon = DriverManager.getConnection(dbURL, username, password);
	}
    return dbCon;
  }
//  
  private List<CheckoutItem> makeCheckoutList(ResultSet rs) throws SQLException {
    List<CheckoutItem> items = new ArrayList<CheckoutItem>();   
    while (rs.next()) items.add(toItem(rs));
    return items;
  }
  
  private CheckoutItem toItem(ResultSet rs) throws SQLException {
    return new CheckoutItem(rs.getString("studentId"), 
        rs.getString("studentEmail"), 
        rs.getString("employeeId"), 
        rs.getString("itemId"), 
        rs.getString("itemDescription"), 
        rs.getTimestamp("time"), 
        rs.getTimestamp("dueDate"));  
    }
  
  private void create() throws SQLException {
    Connection dbCon = connect();
    String table = "create table checkout(" +
                   "studentId varchar(20) not null," +
                   "studentEmail varchar(50) not null," +
                   "employeeId varchar(50) not null," +
                   "itemId varchar(20) not null," +
                   "itemDescription varchar(40) not null," +
                   "time TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                   "dueDate TIMESTAMP not null)";
    if (!dbCon.getMetaData().getTables(null, null, "checkout", null).next()) {
      dbCon.createStatement().executeUpdate(table);

      insertNewItem(new CheckoutItem("millembi", "millembi@uni.edu", "hallkae", "1001", "CAM 1", null, Timestamp.valueOf("2014-12-6 12:00:00")));
      insertNewItem(new CheckoutItem("millembi", "millembi@uni.edu", "hallkae", "4001", "Compact Flash 1", null, Timestamp.valueOf("2014-12-6 12:00:00")));
      insertNewItem(new CheckoutItem("millembi", "millembi@uni.edu", "hallkae", "5001", "Battery 1", null, Timestamp.valueOf("2014-12-6 12:00:00")));
      insertNewItem(new CheckoutItem("millembi", "millembi@uni.edu", "hallkae", "6001", "Charger 1", null, Timestamp.valueOf("2014-12-6 12:00:00")));
      insertNewItem(new CheckoutItem("millembi", "millembi@uni.edu", "hallkae", "3001", "Wireless Lav 1", null, Timestamp.valueOf("2014-12-6 12:00:00")));
    
      insertNewItem(new CheckoutItem("meyersak", "meyersak@uni.edu", "tommyb", "1002", "CAM 2", null, Timestamp.valueOf("2014-12-8 18:00:00")));
      insertNewItem(new CheckoutItem("meyersak", "meyersak@uni.edu", "tommyb", "4002", "Compact Flash 2", null, Timestamp.valueOf("2014-12-8 18:00:00")));
      insertNewItem(new CheckoutItem("meyersak", "meyersak@uni.edu", "tommyb", "5002", "Battery 2", null, Timestamp.valueOf("2014-12-8 18:00:00")));
      insertNewItem(new CheckoutItem("meyersak", "meyersak@uni.edu", "tommyb", "6002", "Charger 2", null, Timestamp.valueOf("2014-12-8 18:00:00")));
      insertNewItem(new CheckoutItem("meyersak", "meyersak@uni.edu", "tommyb", "3002", "Wireless Lav 2", null, Timestamp.valueOf("2014-12-8 18:00:00")));
    
    }
    }
}
