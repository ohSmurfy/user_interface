package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OverviewGoingOutList extends JPanel
{
	
  OverviewGoingOutList panel;
  APIReference api;
  ArrayList<Reservation> reservations;
  public OverviewGoingOutList(){
    panel = this;
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(new Color(162,181,205));
    api = new APIReference();
    try {
		reservations = api.getReservations();
		refresh();
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		reservations = new ArrayList<Reservation>();
	}   
    refresh();
  
  }
  
  public void refresh()
  {  
	
    panel.removeAll();
    JButton button = new JButton("Get Reservations");
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
          try {
			reservations = api.getReservations();
			refresh();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        }
      });
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
	panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	panel.add(button);
	panel.add(header);
	panel.add(description);
    	
   	for (Reservation r : reservations)
   	{
   		panel.add(new OverviewGoingOutItem(r));
  	    panel.add(Box.createVerticalStrut(5));

    }
	
	panel.revalidate();
	panel.repaint();
  }	
}
