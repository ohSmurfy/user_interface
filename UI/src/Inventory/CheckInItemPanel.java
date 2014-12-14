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
  JLabel itemDescription;
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
	itemDescription = new JLabel(item.getItemDescription());
	itemIdLabel = new JLabel(item.getItemId());
	itemId = item.getItemId();
	due = new JLabel(item.dueDate.toGMTString());
	
	checkIn.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	    try {
          String eId = ((CheckInTabPanel) panel.getParent().getParent()).getEmployeeId();
          if (eId.equals(null) || eId.equals("")) JOptionPane.showMessageDialog(panel,"please enter employee id before continuing");
          else {
            SQLCheckoutItemRepo outItems = new SQLCheckoutItemRepo();
            outItems.deleteCheckoutItemByItemId(current.getItemId());
            SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
            inventory.updateState(current.getItemId(), "in");
            current.updateEmployeeId(eId);
            SQLItemCheckinRepo checkIn = new SQLItemCheckinRepo();
            checkIn.insertNewItem(current);
            outItems.close();
            inventory.close();
            checkIn.close();
          }
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
	
	panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	
	panel.add(itemIdLabel);
	panel.add(itemDescription);
	panel.add(due);
	panel.add(checkIn);
	panel.setVisible(true);
  }
}


