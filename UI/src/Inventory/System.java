package Inventory;
 
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
 
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
//    APIReference a = new APIReference();
//    a.getReservations();
    tabbedPane = new JTabbedPane();
    checkIn = new CheckInTabPanel();
    inventory = new InventoryTabPanel();
    overview = new OverviewTabPanel();
    checkout = new CheckoutPanel();
    
    JScrollPane scrollCheckIn = new JScrollPane(checkIn);
    JScrollPane scrollInventory = new JScrollPane(inventory);
    JScrollPane scrollOverview = new JScrollPane(overview);
    JScrollPane scrollCheckout = new JScrollPane(checkout);

    tabbedPane.addTab("Overview", scrollOverview);
    tabbedPane.addTab("Check out", scrollCheckout);
    tabbedPane.addTab("Check in", scrollCheckIn);
    tabbedPane.addTab("Inventory", scrollInventory);
    tabbedPane.addChangeListener(new ChangeListener() {
	  public void stateChanged(ChangeEvent arg0) {
		(inventory.getListPanel()).refresh();
		overview.refresh();
		checkout.refreshCheckouttab();
		}
      });
    panel.add(tabbedPane);
    panel.setBackground(Color.DARK_GRAY);

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

