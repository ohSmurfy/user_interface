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
  JLabel itemDiscription;
  JLabel itemId;
  InventroyItemPanel panel;
  
  public InventroyItemPanel(InventoryItem item) {
    panel = this;
    panel.setLayout(new GridLayout(1, 3));
    itemDiscription = new JLabel(item.getDiscription());
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
            Container parent = panel.getParent();
            parent.removeAll();
            parent.add(new InventoryHeaderPanel());
            java.util.List<InventoryItem> items = inventory.getAll();
            for (InventoryItem item : items) {
              parent.add(new InventroyItemPanel(item));
              parent.add(Box.createVerticalStrut(5));
            }
            parent.revalidate();
            parent.repaint();
          } else JOptionPane.showMessageDialog(panel,"Updated!");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(panel,ex);
        }
      }
    });
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//    panel.setPreferredSize(new Dimension(400,25));
    panel.setMaximumSize(new Dimension(2000,25));
    
    panel.add(itemId);
    panel.add(itemDiscription);
    panel.add(combo);
    panel.setVisible(true);
  }
}
