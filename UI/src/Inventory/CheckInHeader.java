package Inventory;

import java.awt.*;

import javax.swing.*;

public class CheckInHeader extends JPanel{
  CheckInHeader panel;
  JLabel itemId = new JLabel("Item ID");
  JLabel itemDiscription = new JLabel("Discription");
  JLabel dueDate = new JLabel("Due Date");
  JLabel checkIn = new JLabel("");
  
  public CheckInHeader() {
  panel = this;
  panel.setLayout(new GridLayout(1, 4));
  panel.add(itemId);
  panel.add(itemDiscription);
  panel.add(dueDate);
  panel.add(checkIn);
  panel.setVisible(true);
  panel.setMaximumSize(new Dimension(2000,25));
  }
}
