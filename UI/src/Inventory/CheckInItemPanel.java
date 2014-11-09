package Inventory;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class CheckInItemPanel extends JPanel {
  CheckInItemPanel panel;
  JLabel itemDiscription;
  JLabel itemIdLabel;
  String itemId;
  JLabel due;
  JButton checkIn = new JButton("Check in!");
  CheckoutItem current;
  CheckInListPanel parent;
  public CheckInItemPanel(CheckoutItem item, CheckInListPanel p) {
	panel = this;
	parent = p;
	current = item;
	panel.setLayout(new GridLayout(1, 4));
	itemDiscription = new JLabel(item.getItemDiscription());
	itemIdLabel = new JLabel(item.getItemId());
	itemId = item.getItemId();
	due = new JLabel(item.dueDate.toGMTString());
	
	checkIn.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	          try {
	        	  SQLCheckoutItemRepo outItems = new SQLCheckoutItemRepo();
	              outItems.deleteCheckoutItemByItemId(current.getItemId());
	              SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
	              inventory.updateState(current.getItemId(), "in");
	              SQLItemCheckinRepo checkIn = new SQLItemCheckinRepo();
	              checkIn.insertNewItem(current);
	            } catch (SQLException ex) {
	              JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
	            } catch (ItemException ex){
	              JOptionPane.showMessageDialog(panel,ex);
	            }
	    	  parent.refresh(current.getStudentId());
	    	  parent.revalidate();
	    	  parent.repaint();
	      };
	    });
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	
	panel.add(itemIdLabel);
	panel.add(itemDiscription);
	panel.add(due);
	panel.add(checkIn);
	panel.setVisible(true);
  }
}


