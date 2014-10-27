package Inventory;

import java.sql.*;

public class Garbage {
 
  public static void main(String args[]) {
    try {
      SQLInventoryItemRepo inventoryManager = new SQLInventoryItemRepo();
//      inventoryManager.insertNewItem(new InventoryItem("1004","camera 1"));
//      System.out.println(inventoryManager.getItemById("1000").getId());
      inventoryManager.updateState("1000","out");
      for (final InventoryItem item : inventoryManager.getAll()) {
        System.out.println(item.getId());
      }
    } catch (SQLException ex) {
      System.out.println("Fail" + ex);
    }   
  }
}