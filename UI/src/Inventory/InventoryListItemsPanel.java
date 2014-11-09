package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Inventory.InventoryHeaderPanel;
import Inventory.InventoryItem;
import Inventory.ItemException;
import Inventory.SQLInventoryItemRepo;

public class InventoryListItemsPanel extends JPanel{ 
  InventoryListItemsPanel panel;
  public InventoryListItemsPanel(){  
    panel = this;
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(new InventoryHeaderPanel());
    panel.add(Box.createVerticalStrut(5));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    try {
      SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
      java.util.List<InventoryItem> items = inventory.getAll();
      for (InventoryItem item : items) {
        panel.add(new InventroyItemPanel(item));
        panel.add(Box.createVerticalStrut(5));
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
    } catch (ItemException ex){
      JOptionPane.showMessageDialog(panel,ex);
    }
  } 
  public void refresh(){
	    panel.removeAll();
	    panel.add(new InventoryHeaderPanel());
	    panel.add(Box.createVerticalStrut(5));
	      
	    try {
	      SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
	      panel.removeAll();
	      panel.add(new InventoryHeaderPanel());
	      java.util.List<InventoryItem> items = inventory.getAll();
	      for (InventoryItem item : items) {
	        panel.add(new InventroyItemPanel(item));
	        panel.add(Box.createVerticalStrut(5));
	      }
	      panel.revalidate();
	      panel.repaint();
	    } catch (SQLException ex) {
	      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
	    } catch (ItemException ex){
	      JOptionPane.showMessageDialog(panel,ex);
	    }
	  }
}