package Inventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Inventory.InventoryHeaderPanel;
import Inventory.InventoryItem;
import Inventory.ItemException;
import Inventory.SQLInventoryItemRepo;

public class InventroyItemPanel extends JPanel{
  String[] options = {"in","out","overdue","missing","delete"}; 
  JComboBox combo = new JComboBox(options);
  JLabel itemDescription;
  JLabel itemId;
  InventroyItemPanel panel;
  
  public InventroyItemPanel(InventoryItem item) {
    panel = this;
    panel.setLayout(new GridLayout(1, 3));
    itemDescription = new JLabel(item.getDescription());
    itemId = new JLabel(item.getId());
    combo.setSelectedItem(item.getState());
    combo.addActionListener(new ActionListener()    {
      public void actionPerformed(ActionEvent e)
      {
        try {
          SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
          inventory.updateState(itemId.getText(), combo.getSelectedItem().toString());
          if (combo.getSelectedItem().toString() == "delete") {
            JOptionPane.showMessageDialog(panel,"Deleted!");
            InventoryListItemsPanel parent = (InventoryListItemsPanel) panel.getParent();
            parent.refresh();
          } else JOptionPane.showMessageDialog(panel,"Updated!");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(panel,ex);
        }
      }
    });
    panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    panel.setMaximumSize(new Dimension(2000,25));    
    panel.add(itemId);
    panel.add(itemDescription);
    panel.add(combo);
    panel.setVisible(true);
  }
}
