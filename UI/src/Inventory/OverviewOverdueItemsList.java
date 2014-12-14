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
    panel.setBackground(new Color(162,181,205));
    refresh();
    }
  public void refresh(){  
    panel.removeAll();

	JPanel header = new JPanel();
    header.setBackground(new Color(162,181,205));
	JLabel overdue = new JLabel("Overdue");
	overdue.setFont(overdue.getFont ().deriveFont (15.0f));
	header.add(overdue);	
	header.setVisible(true);
	header.setMaximumSize(new Dimension(2000,25));
			
	JPanel description = new JPanel();
	description.setLayout(new GridLayout(1,2));
	description.setBackground(new Color(162,181,205));
	description.add(new JLabel("Email"));
	description.add(new JLabel("Item Description"));
	description.setVisible(true);
	description.setMaximumSize(new Dimension(2000,25));

	panel.add(Box.createVerticalStrut(5));
	panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
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
      inventory.close();
      checkoutTable.close();
	} catch (SQLException ex) {
      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
    } catch (ItemException ex){
      JOptionPane.showMessageDialog(panel,ex);
    }
    panel.revalidate();
    panel.repaint();
  }
}
