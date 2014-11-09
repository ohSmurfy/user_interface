package Inventory;
 
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
public class System extends JPanel {
    public System() {
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JComponent panel1 = makePanel();
        JComponent panel2 = makePanel();
        JComponent checkIn = new CheckInTabPanel();
        JComponent inventory = new InventoryTabPanel();
        panel1.setPreferredSize(new Dimension(600, 100));

        tabbedPane.addTab("Overview", panel1);
        tabbedPane.addTab("Check out", panel2);
        tabbedPane.addTab("Check in", checkIn);
        tabbedPane.addTab("Inventory", inventory);
        JScrollPane scroll = new JScrollPane(tabbedPane);
        add(scroll);
    }
    
//    this will change to 4 different functions creating inventory/in/out/overview
    protected JComponent makePanel() {
        JPanel panel = new JPanel(false);
        return panel;
    }
 
    public static void main(String[] args) {
      JFrame frame = new JFrame("Inventory System");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new System());
      frame.pack();
      frame.setVisible(true);
    }
}

