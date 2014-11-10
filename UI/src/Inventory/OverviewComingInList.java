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

public class OverviewComingInList extends JPanel{
	
	OverviewComingInList panel;
	public OverviewComingInList(){
		panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel header = new JPanel();
		JLabel comingIn = new JLabel("Coming In");
		header.add(comingIn);
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));

		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,3));
		JLabel nameColumn = new JLabel("Student Id");
		JLabel emailColumn = new JLabel("Student Email");
		JLabel timeColumn = new JLabel("Time");
		description.add(nameColumn);
		description.add(emailColumn);
		description.add(timeColumn);
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		
		java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
		
	    panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		panel.add(header);
		panel.add(description);
		
	    try {
	      SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();

	      for (String studentId : checkoutTable.getStudentIds(todaysDate)) {
	    	panel.add( new OverviewComingInItem(checkoutTable.getByStudentId(studentId)));
	  	    panel.add(Box.createVerticalStrut(5));

	      }
//	    	  if(item.getDueDate().getDay() == todaysDate.getDay()){
//	        	  	inItems.add(new OverviewComingInItem(item));
//	        	  	//outItems.add(item.getTime());
//	        	  }
//	      }
	    } catch (SQLException ex) {
	      JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
	    } catch (ItemException ex){
	      JOptionPane.showMessageDialog(panel,ex);
	    }		
	}
	
	

}
