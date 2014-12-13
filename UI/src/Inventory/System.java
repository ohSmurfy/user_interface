package Inventory;
 
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.text.ParseException;
 
public class System extends JPanel {
  JTabbedPane tabbedPane;
  CheckInTabPanel checkIn;
  InventoryTabPanel inventory;
  OverviewTabPanel overview;
  CheckoutPanel checkout;
  System panel;
  public System() {
    panel = this;
    panel.setLayout(new GridLayout(1,1));
    
    tabbedPane = new JTabbedPane();
    checkIn = new CheckInTabPanel();
    inventory = new InventoryTabPanel();
    overview = new OverviewTabPanel();
    checkout = new CheckoutPanel();
  
    JScrollPane scrollCheckin = new JScrollPane(checkIn);
    JScrollPane scrollInventory = new JScrollPane(inventory);
    JScrollPane scrollOverview = new JScrollPane(overview);
    JScrollPane scrollCheckout = new JScrollPane(checkout);

    tabbedPane.addTab("Overview", scrollOverview);
    tabbedPane.addTab("Check out", scrollCheckout);
    tabbedPane.addTab("Check in", scrollCheckin);
    tabbedPane.addTab("Inventory", scrollInventory);
    
    tabbedPane.addChangeListener(new ChangeListener() {
	  public void stateChanged(ChangeEvent arg0) {
		(inventory.getListPanel()).refresh();
		overview.refresh();
		}
      });
    panel.add(tabbedPane);     
  }
  
  public CheckInTabPanel checkinTab(){
	  return checkIn;
  }
 
  public CheckoutPanel checkoutTab(){
	  return checkout;
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Inventory System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new System());
    frame.pack();
    frame.setVisible(true);
  }
}

