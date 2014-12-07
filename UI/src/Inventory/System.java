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
    APIReference a = new APIReference();
    tabbedPane = new JTabbedPane();
    checkIn = new CheckInTabPanel();
    inventory = new InventoryTabPanel();
    overview = new OverviewTabPanel();
    checkout = new CheckoutPanel();
    try
    {
    	a.getReservations();
    }
    catch(ParseException p1)
    {
    	java.lang.System.out.println("Broke");
    }
    tabbedPane.addTab("Overview", overview);
    tabbedPane.addTab("Check out", checkout);
    tabbedPane.addTab("Check in", checkIn);
    tabbedPane.addTab("Inventory", inventory);
    tabbedPane.addChangeListener(new ChangeListener() {
	  public void stateChanged(ChangeEvent arg0) {
		(inventory.getListPanel()).refresh();
		overview.refresh();
		checkout.refreshCheckouttab();
		}
      });
    JScrollPane scroll = new JScrollPane(tabbedPane);
    panel.add(scroll);     
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

