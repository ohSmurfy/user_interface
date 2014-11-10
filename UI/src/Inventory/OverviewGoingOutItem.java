package Inventory;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;

public class OverviewGoingOutItem extends JPanel {
  OverviewGoingOutItem panel;
  CheckoutItem current;
  public OverviewGoingOutItem(String email) {
	panel = this;
	panel.setLayout(new GridLayout(1, 2));
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,50));
	

    DateFormat format = new SimpleDateFormat( "h:mm a" );
    Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
	panel.add(new JLabel(email));
	panel.add(new JLabel(format.format(currentTimestamp)));
	panel.setVisible(true);
  }
}


