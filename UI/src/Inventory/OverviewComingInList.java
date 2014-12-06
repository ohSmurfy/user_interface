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

public class OverviewComingInList extends JPanel{
	
	OverviewComingInList panel;
	public OverviewComingInList(){
		panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
	    refresh();
	}
	  public void refresh(){  
		panel.removeAll();
		JPanel header = new JPanel();
		JLabel comingIn = new JLabel("Coming In");
		header.add(comingIn);
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));
		
		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,2));
		JLabel emailColumn = new JLabel("Student Email");
		JLabel timeColumn = new JLabel("Time");
		description.add(emailColumn);
		description.add(timeColumn);
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));
			
		java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
			
	    panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(new Color(75,17,111), 2));
			
		panel.add(header);
		panel.add(description);
			
	    try {
	      SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();

	      for (String studentId : checkoutTable.getStudentIds(todaysDate)) {
	    	panel.add( new OverviewComingInItem(checkoutTable.getByStudentId(studentId)));
	  	    panel.add(Box.createVerticalStrut(5));
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
