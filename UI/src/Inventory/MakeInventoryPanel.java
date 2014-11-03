package Inventory;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MakeInventoryPanel extends JPanel{

  public MakeInventoryPanel() {
    final MakeInventoryPanel panel = this;
    panel.setLayout(new FlowLayout());
    JButton newItem = new JButton("Add Item +");
    newItem.addActionListener(new ActionListener()    {
      public void actionPerformed(ActionEvent e)
      {
        JFrame frame = new JFrame("System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NewItem());
        frame.pack();
        frame.setVisible(true);;
      }
    });
    
    JPanel headers = new JPanel();
    JLabel headerId = new JLabel("Item ID");
    JLabel headerDiscription = new JLabel("Discription");
    JLabel hearderCurrentState = new JLabel("Current State");
    headers.add(headerId);
    headers.add(headerDiscription);
    headers.add(hearderCurrentState);
    headers.setVisible(true);
    
    JPanel inventoryListPane = new JPanel();
    inventoryListPane.setLayout(new BoxLayout(inventoryListPane, BoxLayout.Y_AXIS));
    inventoryListPane.add(headers);
    inventoryListPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
    try {
      SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
      java.util.List<InventoryItem> items = inventory.getAll();
      
      for (InventoryItem item : items) {
        inventoryListPane.add(new InventroyItemPanel(item));
      }
    
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
    } catch (ItemException ex){
      JOptionPane.showMessageDialog(panel,ex);
    }
  
    panel.add(inventoryListPane);
    panel.add(newItem);
    panel.setVisible(true);
  }
}