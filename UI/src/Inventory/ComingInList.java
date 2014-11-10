package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComingInList extends JPanel{
	
	ComingInList panel;
	public ComingInList(){
		panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		JLabel goingOut = new JLabel("Coming In");
		header.setLayout(new GridLayout(1, 3));
		header.add(goingOut);
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));

		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,2));
		JLabel nameColumn = new JLabel("Name");
		JLabel timeColumn = new JLabel("Time");
		description.add(nameColumn);
		description.add(timeColumn);
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));
		
	    
		JPanel inItems = new JPanel();
		inItems.setLayout(new GridLayout(1,1));
		
		Date todaysDate = new Date();

		
	    try {
	      SQLItemCheckinRepo goingOutList = new SQLItemCheckinRepo();
	      java.util.List<CheckoutItem> items = goingOutList.getAll();
	      for (CheckoutItem item : items) {
	    	  if(item.getDueDate().getDay() == todaysDate.getDay()){
	        	  	inItems.add(new OverviewComingInItemList(item));
	        	  	//outItems.add(item.getTime());
	        	  }
	      }
	    } catch (SQLException ex) {
	      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
	    } catch (ItemException ex){
	      JOptionPane.showMessageDialog(panel,ex);
	    }
	    
	    panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(header);
		panel.add(description);
		panel.add(inItems);
		
		
	}
	
	

}
