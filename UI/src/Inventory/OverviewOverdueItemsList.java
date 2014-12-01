package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OverviewOverdueItemsList extends JPanel {
  OverviewOverdueItemsList panel;
  public OverviewOverdueItemsList(){
    panel = this;
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    refresh();
    }
  public void refresh(){  
    panel.removeAll();

	JPanel header = new JPanel();
	header.add(new JLabel("Overdue"));
	header.setVisible(true);
	header.setMaximumSize(new Dimension(2000,25));
			
	JPanel description = new JPanel();
	description.setLayout(new GridLayout(1,2));
	description.add(new JLabel("Email"));
	description.add(new JLabel("Item Description"));
	description.setVisible(true);
	description.setMaximumSize(new Dimension(2000,25));

	panel.add(Box.createVerticalStrut(5));
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	panel.add(header);
	panel.add(description);
		    
    try {
	  SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
	  SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();
      Timestamp currentTime = new Timestamp((new java.util.Date()).getTime());
      for (CheckoutItem item : checkoutTable.getAll()) {
    	  if (item.dueDate.before(currentTime)){
    		  inventory.updateState(item.itemId, "overdue");
    		  panel.add(new OverviewOverdueItem(item));
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
