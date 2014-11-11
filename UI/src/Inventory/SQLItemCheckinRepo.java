package Inventory;

import java.sql.*;
import java.util.*;

public class SQLItemCheckinRepo {
  Connection dbCon;
  public SQLItemCheckinRepo() throws SQLException {
    create();
  }
  
  public void insertNewItem(CheckoutItem item) throws SQLException {
    String query = "insert into checkIn(" +
        "studentId, studentEmail, employeeId, itemId, itemDiscription, dueDate)" +
        "values(?,?,?,?,?,?)";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, item.getStudentId());
    stmt.setString(2, item.getStudentEmail());
    stmt.setString(3, item.getEmployeeId());
    stmt.setString(4, item.getItemId());
    stmt.setString(5, item.getItemDiscription());
    stmt.setTimestamp(6, item.getDueDate());
    stmt.executeUpdate();
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

  public CheckoutItem getNewestByItemId(String id) throws SQLException {
	String query = "select * from checkIn where itemId = ? order by time desc Limit 1";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    return toItem(rs);
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
        rs.getString("itemDiscription"), 
        rs.getTimestamp("time"), 
        rs.getTimestamp("dueDate"));  
    }
  
  private void create() throws SQLException {
    dbCon = connect();
    String table = "create table checkIn(" +
                   "studentId varchar(20) not null," +
                   "studentEmail varchar(50) not null," +
                   "employeeId varchar(50) not null," +
                   "itemId varchar(20) not null," +
                   "itemDiscription varchar(40) not null," +
                   "time TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                   "dueDate TIMESTAMP not null)";
    if (!dbCon.getMetaData().getTables(null, null, "checkIn", null).next()) 
      dbCon.createStatement().executeUpdate(table);
  }
}
