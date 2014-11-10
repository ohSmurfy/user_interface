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
import java.util.List;

import javax.swing.*;

public class OverviewComingInItem extends JPanel {
  OverviewComingInItem panel;
  JLabel due;
  CheckoutItem current;
  List<CheckoutItem> allItems;
  
  public OverviewComingInItem(List<CheckoutItem> items) {
	panel = this;
	current = items.get(0);
	allItems = items;
	panel.setLayout(new GridLayout(1, 3));
	
    DateFormat format = new SimpleDateFormat( "yyyy/MM/dd h:mm a" );
	due = new JLabel(format.format(current.dueDate));
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,50));
	
	panel.add(new JLabel(current.getStudentId()));
	panel.add(new JLabel(current.getStudentEmail()));
	panel.add(due);
	panel.setVisible(true);

  }
}
