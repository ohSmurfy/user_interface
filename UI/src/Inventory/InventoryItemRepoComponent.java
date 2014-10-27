package Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryItemRepoComponent {
  public List<InventoryItem> getAll() throws SQLException;
  public InventoryItem getItemById(String id) throws SQLException, ItemException;
  public void insertNewItem(InventoryItem item) throws SQLException, ItemException;
  public void updateState(String id, String state) throws SQLException;
}
