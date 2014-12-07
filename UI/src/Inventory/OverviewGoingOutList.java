package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    panel.setBackground(new Color(162,181,205));
    refresh();
  }
  public void refresh(){  
    panel.removeAll();
	JPanel header = new JPanel();
    header.setBackground(new Color(162,181,205));
	JLabel goingOut = new JLabel("Going Out");
	goingOut.setFont(goingOut.getFont ().deriveFont (15.0f));
	header.add(goingOut);
	header.setVisible(true);
	header.setMaximumSize(new Dimension(2000,25));

	JPanel description = new JPanel();
	description.setBackground(new Color(162,181,205));
	description.setLayout(new GridLayout(1,2));
	description.add(new JLabel("Email"));
	description.add(new JLabel("Time"));
	description.setVisible(true);
	description.setMaximumSize(new Dimension(2000,25));
				    
    panel.add(Box.createVerticalStrut(5));
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	panel.add(header);
	panel.add(description);
	
	ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
	items.add(new InventoryItem("1000","Camera 1", "out", "Clean the lense"));


	panel.add(new OverviewGoingOutItem(new Reservation("kyel","kyle@gmail.com","5:00 pm", Timestamp.valueOf("2014-12-03 19:00:00"), items)));
	panel.revalidate();
	panel.repaint();
  }	
}
