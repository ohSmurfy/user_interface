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

public class OVerviewMissingItemsList extends JPanel {
	
	OVerviewMissingItemsList panel;
	public OVerviewMissingItemsList(){
		panel = this;
		
		JPanel header = new JPanel();
		JLabel goingOut = new JLabel("Missing");
		header.setLayout(new GridLayout(1, 3));
		header.add(goingOut);
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));

		panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(header);
	    
	    
	    /*try {
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
	    }*/
		
		
		
	}

}
