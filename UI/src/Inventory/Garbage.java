package Inventory;

import java.sql.*;
import java.util.Date;

import ItemCheckout.*;
public class Garbage {
 
  public static void main(String args[]) {
    try {
      SQLCheckoutItemRepo checkout = new SQLCheckoutItemRepo();
      checkout.getByDueDate(java.sql.Date.valueOf("2014-10-29"));
//      checkout.insertNewItem(new CheckoutItem("1","1","1","1",new Timestamp((new Date()).getTime()),new Timestamp((new Date()).getTime())));
      SQLInventoryItemRepo inventoryManager = new SQLInventoryItemRepo();
//      inventoryManager.insertNewItem(new InventoryItem("1002","camera 1", "out", "ads"));
//      System.out.println(inventoryManager.getItemById("1000").getId());
//      inventoryManager.updateState("1000","out");
      for (final CheckoutItem item : checkout.getByDueDate(java.sql.Date.valueOf("2014-10-29"))) {
        System.out.println(item.getStudentId());
      }
    } catch (SQLException ex) {
      System.out.println("Fail" + ex);
    }   
  }
}