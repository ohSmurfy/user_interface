package Inventory;
 
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
public class System extends JPanel {
  JTabbedPane tabbedPane;
  CheckInTabPanel checkIn;
  InventoryTabPanel inventory;
  OverviewTabPanel overview;
  System panel;
  public System() {
    panel = this;
    panel.setLayout(new GridLayout(1,1));
    
    tabbedPane = new JTabbedPane();
    checkIn = new CheckInTabPanel();
    inventory = new InventoryTabPanel();
    overview = new OverviewTabPanel();
    
    tabbedPane.addTab("Overview", overview);
    tabbedPane.addTab("Check out", new JPanel());
    tabbedPane.addTab("Check in", checkIn);
    tabbedPane.addTab("Inventory", inventory);
    tabbedPane.addChangeListener(new ChangeListener() {
	  public void stateChanged(ChangeEvent arg0) {
		(inventory.getListPanel()).refresh();
		overview.refresh();
		}
      });
    JScrollPane scroll = new JScrollPane(tabbedPane);
    panel.add(scroll);     
  }
  
  public CheckInTabPanel checkinTab(){
	  return checkIn;
  }
 
  public static void main(String[] args) {
    JFrame frame = new JFrame("Inventory System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new System());
    frame.pack();
    frame.setVisible(true);
  }
}

