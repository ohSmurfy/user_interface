package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OverviewGoingOutList extends JPanel{
	
	OverviewGoingOutList panel;
	public OverviewGoingOutList(){
		panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		JLabel goingOut = new JLabel("Going Out");
		header.setLayout(new GridLayout(1, 3));
		header.add(goingOut);
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));

		//Descriptions
		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,2));
		JLabel nameColumn = new JLabel("Name");
		JLabel timeColumn = new JLabel("Time");
		description.add(nameColumn);
		description.add(timeColumn);
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));
		
		
//		//Items
//		JPanel outItems = new JPanel();
//		outItems.setLayout(new GridLayout(1,1));
//		
//		Date todaysDate = new Date();
//		
//	    try {
//    	  SQLCheckoutItemRepo goingOutList = new SQLCheckoutItemRepo();
//    	  java.util.List<CheckoutItem> items = goingOutList.getAll();
//          for(CheckoutItem item : items){
//        	  if(item.getDueDate().getDay() == todaysDate.getDay()){
//        	  	outItems.add(new OverviewGoingOutItem(item));
//        	  	//outItems.add(item.getTime());
//        	  }
//          }
//
//		}
//		catch (SQLException ex) {
//		  JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
//		} catch (ItemException ex){
//		  JOptionPane.showMessageDialog(panel,ex);
//		}
		
	    
	    panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(header);
		panel.add(description);
//		panel.add(outItems);
		
	}
	

}
