package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;

public class CheckInHeader extends JPanel{
	CheckInHeader panel;
	JLabel itemId = new JLabel("Item ID");
	JLabel itemDescription = new JLabel("Description");
	JLabel dueDate = new JLabel("Due Date");
	JLabel checkIn = new JLabel("");
	public CheckInHeader() {
		panel = this;
	    panel.setLayout(new GridLayout(1, 4));
		panel.add(itemId);
		panel.add(itemDescription);
		panel.add(dueDate);
		panel.add(checkIn);
	    panel.setVisible(true);
	    panel.setMaximumSize(new Dimension(2000,25));
	}
}
