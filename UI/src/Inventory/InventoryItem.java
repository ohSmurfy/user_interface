package Inventory;

public class InventoryItem {
  String id;
  String description;
  String state;
  String reminder;
  InventoryItem(String itemId, String itemDescription, String currentState, String itemReminder) {
    id = itemId;
    description = itemDescription;
    state = currentState;
    reminder = itemReminder;
  }
  
  public String getId() {
    return id;
  }
  
  public String getDescription(){
    return description;
  }
  public String getState(){
    return state;
  }
  public String getReminder() {
    return reminder;
  }
}
