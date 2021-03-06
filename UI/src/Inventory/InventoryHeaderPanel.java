package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryHeaderPanel extends JPanel{
  InventoryHeaderPanel headers;
  JLabel headerId = new JLabel("Item ID");
  JLabel headerDescription = new JLabel("Description");
  JLabel hearderCurrentState = new JLabel("Current State");
  
  public InventoryHeaderPanel() {
    headers = this;
    headers.setLayout(new GridLayout(1, 3));
    headers.add(headerId);
    headers.add(headerDescription);
    headers.add(hearderCurrentState);
    headers.setBackground(new Color(162,181,205));
    headers.setVisible(true);
    headers.setMaximumSize(new Dimension(2000,25));
  }
}
