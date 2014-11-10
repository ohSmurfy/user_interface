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
		header.add(new JLabel("Going Out"));
		header.setVisible(true);
		header.setMaximumSize(new Dimension(2000,25));

		//Descriptions
		JPanel description = new JPanel();
		description.setLayout(new GridLayout(1,2));
		description.add(new JLabel("Email"));
		description.add(new JLabel("Time"));
		description.setVisible(true);
		description.setMaximumSize(new Dimension(2000,25));
		
	    
	    panel.add(Box.createVerticalStrut(5));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(header);
		panel.add(description);

		panel.add(new OverviewGoingOutItem("millembi@uni.edu"));
	    panel.add(Box.createVerticalStrut(5));
		panel.add(new OverviewGoingOutItem("kyle@uni.edu"));

	}
	

}
