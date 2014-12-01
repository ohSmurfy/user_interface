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

public class InventoryNewItemPanel extends JFrame{
  JTextField id = new JTextField(10);
  JTextField description = new JTextField(10);
  JTextField reminder = new JTextField(10);
  JLabel idLabel = new JLabel("Item ID");
  JLabel descriptionLabel = new JLabel("Description");
  JLabel reminderLabel = new JLabel("Reminder");
  JPanel panel = new JPanel(false);
  JButton add = new JButton("Add Item");
  InventoryNewItemPanel frame;
  InventoryListItemsPanel itemListPanel;
  Container outer;
  public InventoryNewItemPanel(InventoryListItemsPanel listPanel) {
    itemListPanel = listPanel;
    frame = this;
    frame.setLocationRelativeTo(itemListPanel);
    panel = new JPanel(false);
    panel.setLayout(new GridLayout(4,2));
    
    add.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        try {
          SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
          inventory.insertNewItem(new InventoryItem(id.getText(), description.getText(),"in",reminder.getText()));
          frame.dispose();
          itemListPanel.refresh();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(frame, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(frame,ex);
        }
      }
    });
    panel.add(idLabel);
    panel.add(id);
    panel.add(descriptionLabel);
    panel.add(description);
    panel.add(reminderLabel);
    panel.add(reminder);
    panel.add(add);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
