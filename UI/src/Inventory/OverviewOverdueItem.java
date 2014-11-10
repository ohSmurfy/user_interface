package Inventory;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class OverviewOverdueItem extends JPanel {
  OverviewOverdueItem panel;
  JLabel itemDiscription;
  JLabel itemIdLabel;
  String itemId;
  JLabel due;
  CheckoutItem current;
  public OverviewOverdueItem(CheckoutItem item) {
	panel = this;
	current = item;
	panel.setLayout(new GridLayout(1, 3));
	itemDiscription = new JLabel(item.getItemDiscription());
	itemIdLabel = new JLabel(item.getItemId());
	itemId = item.getItemId();
	due = new JLabel(item.dueDate.toGMTString());
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	
	panel.add(itemIdLabel);
	panel.add(itemDiscription);
	panel.add(due);
	
	panel.setVisible(true);
  }
}
