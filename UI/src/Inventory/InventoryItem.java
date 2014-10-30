package Inventory;

public class InventoryItem {
  String id;
  String discription;
  String state;
  String reminder;
  InventoryItem(String itemId, String itemDiscription, String currentState, String itemReminder) {
    id = itemId;
    discription = itemDiscription;
    state = currentState;
    reminder = itemReminder;
  }
  
  public String getId() {
    return id;
  }
  
  public String getDiscription(){
    return discription;
  }
  public String getState(){
    return state;
  }
  public String getReminder() {
    return reminder;
  }
}
