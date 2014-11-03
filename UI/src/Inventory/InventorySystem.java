package Inventory;
 
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
public class InventorySystem extends JPanel {
    public InventorySystem() {
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JComponent panel1 = makePanel();
        JComponent panel2 = makePanel();
        JComponent panel3 = makePanel();
        JComponent inventory = new MakeInventoryPanel();
        panel1.setPreferredSize(new Dimension(600, 600));

        tabbedPane.addTab("Overview", panel1);
        tabbedPane.addTab("Check out", panel2);
        tabbedPane.addTab("Check in", panel3);
        tabbedPane.addTab("Inventory", inventory);
        add(tabbedPane);
    }
    
//    this will change to 4 different functions creating inventory/in/out/overview
    protected JComponent makePanel() {
        JPanel panel = new JPanel(false);
        return panel;
    }
 
    public static void main(String[] args) {
      JFrame frame = new JFrame("Inventory System");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new InventorySystem());
      frame.pack();
      frame.setVisible(true);
    }
}

