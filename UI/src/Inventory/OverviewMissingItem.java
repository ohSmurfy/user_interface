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

public class OverviewMissingItem extends JPanel {
  OverviewMissingItem panel;
  JLabel itemDiscription;
  JLabel itemIdLabel;
  InventoryItem current;
  
  public OverviewMissingItem(InventoryItem item) {
	panel = this;
	current = item;
	panel.setLayout(new GridLayout(1, 2));
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	
	panel.add(new JLabel(current.getId()));
	panel.add(new JLabel(current.getDiscription()));
	panel.setVisible(true);
  }
}
