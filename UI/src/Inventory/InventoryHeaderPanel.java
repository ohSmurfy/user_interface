package Inventory;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryHeaderPanel extends JPanel{
  InventoryHeaderPanel headers;
  JLabel headerId = new JLabel("Item ID");
  JLabel headerDiscription = new JLabel("Discription");
  JLabel hearderCurrentState = new JLabel("Current State");
  
  public InventoryHeaderPanel() {
    headers = this;
    headers.setLayout(new GridLayout(1, 3));
    headers.add(headerId);
    headers.add(headerDiscription);
    headers.add(hearderCurrentState);
    headers.setVisible(true);
    headers.setMaximumSize(new Dimension(2000,25));
  }
}
