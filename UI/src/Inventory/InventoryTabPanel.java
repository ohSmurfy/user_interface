package Inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class InventoryTabPanel extends JPanel{
  InventoryTabPanel panel;
  InventoryListItemsPanel inventoryListPanel;
  public InventoryTabPanel() {
    panel = this;
    panel.setBackground(new Color(162,181,205));
    inventoryListPanel = new InventoryListItemsPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    JButton newItem = new JButton("Add Item +");
    newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
    newItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {new InventoryNewItemPanel(inventoryListPanel);};
    });
    panel.add(newItem);
    panel.add(inventoryListPanel);
    panel.setVisible(true);
  }
  public InventoryListItemsPanel getListPanel() {
	  return inventoryListPanel;
  }
}