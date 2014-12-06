package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

public class CheckInListPanel extends JPanel{ 
	CheckInListPanel panel;
  public CheckInListPanel(){  
    panel = this;
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createLineBorder(new Color(75,17,111), 2));
    panel.setPreferredSize(new Dimension(400,400));
    panel.add(new CheckInHeader());
    panel.add(Box.createVerticalStrut(5));


  } 
  public void refresh(String studentId){  
	  panel.removeAll();
	  panel.add(new CheckInHeader());
      panel.add(Box.createVerticalStrut(5));
      
      try {
    	  SQLCheckoutItemRepo outItems = new SQLCheckoutItemRepo();
          List<CheckoutItem> items = outItems.getByStudentId(studentId);
          
          if (items.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "No Items are checkedout to " + studentId);
          } else {
            for (CheckoutItem item : items) {
              panel.add(new CheckInItemPanel(item, panel));
              panel.add(Box.createVerticalStrut(5));
            }
          }
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(panel,ex);
        }
      panel.revalidate();
      panel.repaint();
  }
}