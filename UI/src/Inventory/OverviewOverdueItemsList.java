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

public class OverviewOverdueItemsList extends JPanel {
	OverviewOverdueItemsList panel;
	public OverviewOverdueItemsList(){
		
		panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		header.add(new JLabel("Overdue"));
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));
		
		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,2));
		description.add(new JLabel("Email"));
		description.add(new JLabel("Item"));
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));

		panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(header);
		panel.add(description);

	    
	    try {
		  SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
		  SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();
	      java.util.List<InventoryItem> items = inventory.getByCurrentState("overdue");
	      for (InventoryItem item1 : items) {
	    	for (CheckoutItem checkedoutItem : checkoutTable.getByItemId(item1.getId())){
		        panel.add(new OverviewOverdueItem(checkedoutItem));
		        panel.add(Box.createVerticalStrut(5));
	    	}
	      }
	    } catch (SQLException ex) {
	      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
	    } catch (ItemException ex){
	      JOptionPane.showMessageDialog(panel,ex);
	    }
		
	}

}
