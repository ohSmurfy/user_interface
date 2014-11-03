package ItemCheckout;

import java.sql.*;
import java.util.*;

/*
 * create table dun
 * insert dun
 * getbydate dun
 * getAll dun
 * getByStudentId dun 
 * getByItemId dun 
 * delete dun
 */
public class SQLCheckoutItemRepo {
  
  public SQLCheckoutItemRepo() throws SQLException {
    create();
  }
  
  public void insertNewItem(CheckoutItem item) throws SQLException {
    String query = "insert into checkout(" +
        "studentId, studentEmail, employeeId, itemId, dueDate)" +
        "values(?,?,?,?,?)";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, item.getStudentId());
    stmt.setString(2, item.getStudentEmail());
    stmt.setString(3, item.getEmployeeId());
    stmt.setString(4, item.getItemId());
    stmt.setTimestamp(5, item.getDueDate());
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

  public void deleteCheckoutItemByItemId(String id) throws SQLException {
    String query = "delete from checkout where itemId = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    stmt.executeUpdate();
  }
  
  private Connection connect() throws SQLException{
    String dbURL = "jdbc:mysql://localhost/inventory";
    String username ="root";
    String password = "password";
    return DriverManager.getConnection(dbURL, username, password);
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
                   "time TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                   "dueDate TIMESTAMP not null)";
    if (!dbCon.getMetaData().getTables(null, null, "checkout", null).next()) 
      dbCon.createStatement().executeUpdate(table);
  }
}