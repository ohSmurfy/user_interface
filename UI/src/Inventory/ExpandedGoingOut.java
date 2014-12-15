package Inventory;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;

public class ExpandedGoingOut extends JFrame{
  ExpandedGoingOut frame;
  Reservation res;
  JPanel itemPane;
  Dimension maxSize = new Dimension(2000,25);
  GridLayout panelLayout = new GridLayout(1,2);
  
  public ExpandedGoingOut(Reservation aRes) {
	frame = this;
	res = aRes;

	JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel studentIDPanel = new JPanel();
    studentIDPanel.setLayout(panelLayout);
    studentIDPanel.add(new JLabel("Student Username :"));
    studentIDPanel.add(new JLabel(res.studentId));
    studentIDPanel.setMaximumSize(maxSize);
    
    JPanel studentEmailPanel = new JPanel();
    studentEmailPanel.setLayout(panelLayout);
    studentEmailPanel.add(new JLabel("Student Email :"));
    studentEmailPanel.add(new JLabel(res.studentEmail));
    studentEmailPanel.setMaximumSize(maxSize);

    JPanel timePanel = new JPanel();
    timePanel.setLayout(panelLayout);
    timePanel.add(new JLabel("Time :"));
    timePanel.add(new JLabel(res.goingOutTime));
    timePanel.setMaximumSize(maxSize);

    SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd h:mm a" );	
    JPanel checkinPanel = new JPanel();
    checkinPanel.setLayout(panelLayout);
    checkinPanel.add(new JLabel("Due Date :"));
    checkinPanel.add(new JLabel(format.format(res.getDue())));
    checkinPanel.setMaximumSize(maxSize);
    
	panel.add(studentIDPanel);
	panel.add(studentEmailPanel);
	panel.add(timePanel);
	panel.add(checkinPanel);

    itemPane = new JPanel();
	itemPane.setLayout(panelLayout);
	itemPane.add(new JLabel("Item Id"));
	itemPane.add(new JLabel("Description"));
	itemPane.setMaximumSize(maxSize);
	panel.add(itemPane);

    for (InventoryItem item : res.getItems()) {
        itemPane = new JPanel();
  	  itemPane.setLayout(new GridLayout(1,2));
  	  itemPane.add(new JLabel(item.id));
  	  itemPane.add(new JLabel(item.description));
  	  itemPane.setMaximumSize(maxSize);

  	  panel.add(itemPane);
      }
    
    panel.setVisible(true);
    frame.add(panel);
    frame.setVisible(true);
    frame.pack();
  }
}
