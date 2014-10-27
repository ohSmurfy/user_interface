package Inventory;

public class InventoryItem {
  String id;
  String discription;
  String state;
  
  InventoryItem(String itemId, String itemDiscription, String currentState) {
    id = itemId;
    discription = itemDiscription;
    state = currentState;
  }
  InventoryItem(String itemId, String itemDiscription) {
    id = itemId;
    discription = itemDiscription;
    state = "in";
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
}
