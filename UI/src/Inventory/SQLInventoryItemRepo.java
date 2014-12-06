package Inventory;

import java.sql.*;
import java.util.*;

//creating a table works
//get All works 
//update state works
//get by id works
//insert works if item doesn't exist

public class SQLInventoryItemRepo {
  Connection dbCon;

  public SQLInventoryItemRepo() throws SQLException {
    create();
  }
  
  public List<InventoryItem> getAll() throws SQLException {
    String query = "select * from inventory order by item";
    ResultSet rs= connect().prepareStatement(query).executeQuery();
    return makeInventoryList(rs);
  }

  public List<InventoryItem> getByCurrentState(String state) throws SQLException {
    String query = "select * from inventory where inventory.current_state = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, state);
    ResultSet rs = stmt.executeQuery();
    return makeInventoryList(rs);
  }

  public InventoryItem getItemById(String id) throws SQLException, ItemException {
    String query = "select * from inventory where inventory.id = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) return toItem(rs);
    else throw new ItemException("item id doesnt' exist");
  }

  public void insertNewItem(InventoryItem item) throws SQLException, ItemException {
    Connection dbCon = connect();
    String query = "select id from inventory where inventory.id = ?";
    PreparedStatement stmt = dbCon.prepareStatement(query);
    stmt.setString(1,item.getId());
    if (!stmt.executeQuery().next()) {
      query = "insert into inventory values(?,?,?,?)";
      stmt = dbCon.prepareStatement(query);
      stmt.setString(1,item.getId());
      stmt.setString(2,item.getDescription());
      stmt.setString(3,item.getState());
      stmt.setString(4,item.getReminder());
      stmt.executeUpdate();
    }
    else throw new ItemException("item id already exists");
  }

  public void updateState(String id, String state) throws SQLException {
    if (state == "delete") deleteItem(id);
    else {
      String query = "update inventory set current_state= ? where id= ?";
      PreparedStatement stmt = connect().prepareStatement(query);
      stmt.setString(1, state);
      stmt.setString(2, id);
      stmt.executeUpdate();   
    }
  }
  
  private void deleteItem(String id) throws SQLException {
    String query = "delete from inventory where id = ?";
    PreparedStatement stmt = connect().prepareStatement(query);
    stmt.setString(1, id);
    stmt.executeUpdate();
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
  
  private List<InventoryItem> makeInventoryList(ResultSet rs) throws SQLException {
    List<InventoryItem> items = new ArrayList<InventoryItem>();   
    while (rs.next()) items.add(toItem(rs));
    return items;
  }
  
  private InventoryItem toItem(ResultSet rs) throws SQLException {
    return new InventoryItem(rs.getString("id"), rs.getString("item"), rs.getString("current_state"), rs.getString("reminder_message"));
  }
  
  private void create() throws SQLException {
    Connection dbCon = connect();
    String table = "create table inventory(id varchar(20) not null primary key," +
                   " item varchar(40) not null, " + 
                   "current_state varchar(15) not null default 'in'," +
                   "reminder_message varchar(120) not null default '')";
    if (!dbCon.getMetaData().getTables(null, null, "inventory", null).next()) {
    	dbCon.createStatement().executeUpdate(table);
    	
      	insertNewItem(new InventoryItem("1001","CAM 1", "out", "reminder"));
    	insertNewItem(new InventoryItem("1002","CAM 2", "in", "reminder"));
    	insertNewItem(new InventoryItem("1003","CAM 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("1004","CAM 4", "in", "reminder"));
    	insertNewItem(new InventoryItem("1005","CAM 5", "in", "reminder"));
    	insertNewItem(new InventoryItem("1006","CAM 6", "in", "reminder"));
    	
    	insertNewItem(new InventoryItem("2000","Tripod 1", "in", "reminder"));
    	insertNewItem(new InventoryItem("2001","Tripod 2", "in", "reminder"));
    	insertNewItem(new InventoryItem("2002","Tripod 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("2003","Tripod 4", "in", "reminder"));
    	insertNewItem(new InventoryItem("2004","Tripod 5", "in", "reminder"));
    	insertNewItem(new InventoryItem("2005","Tripod 6", "in", "reminder"));
    	
    	insertNewItem(new InventoryItem("3001","Wireless Lav 1", "in", "reminder"));
    	insertNewItem(new InventoryItem("3002","Wireless Lav 2", "in", "reminder"));
    	insertNewItem(new InventoryItem("3003","Wireless Lav 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("3004","Wireless Lav 4", "in", "reminder"));

    	insertNewItem(new InventoryItem("4001","Compact Flash 1", "in", "reminder"));
    	insertNewItem(new InventoryItem("4002","Compact Flash 2", "in", "reminder"));
    	insertNewItem(new InventoryItem("4003","Compact Flash 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("4004","Compact Flash 4", "missing", "reminder"));
    	insertNewItem(new InventoryItem("4005","Compact Flash 5", "in", "reminder"));
    	insertNewItem(new InventoryItem("4006","Compact Flash 6", "in", "reminder"));
    	

    	insertNewItem(new InventoryItem("5001","Battery 1", "in", "reminder"));
    	insertNewItem(new InventoryItem("5002","Battery 2", "in", "reminder"));
    	insertNewItem(new InventoryItem("5003","Battery 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("5004","Battery 4", "missing", "reminder"));
    	insertNewItem(new InventoryItem("5005","Battery 5", "in", "reminder"));
    	insertNewItem(new InventoryItem("5006","Battery 6", "in", "reminder"));
    	

    	insertNewItem(new InventoryItem("6001","Charger 1", "missing", "reminder"));
    	insertNewItem(new InventoryItem("6002","Charger 2", "missing", "reminder"));
    	insertNewItem(new InventoryItem("6003","Charger 3", "in", "reminder"));
    	insertNewItem(new InventoryItem("6004","Charger 4", "in", "reminder"));
    	insertNewItem(new InventoryItem("6005","Charger 5", "in", "reminder"));
    	insertNewItem(new InventoryItem("6006","Charger 6", "in", "reminder"));
    	
    	insertNewItem(new InventoryItem("7004","Instantly Shuttle USB", "in", "reminder"));
    }
  }
}
