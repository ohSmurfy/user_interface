package Inventory;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;

public class ExpandedComingInView extends JFrame{
  CheckoutItem firstItem;
  ExpandedComingInView frame;
  JPanel itemPane;
  
  public ExpandedComingInView(List<CheckoutItem> items) {
	frame = this;
	
	Dimension maxSize = new Dimension(2000,25);

	JPanel panel = new JPanel();
	firstItem = items.get(0);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel studentIDPanel = new JPanel();
    studentIDPanel.setLayout(new GridLayout(1,2));
    studentIDPanel.add(new JLabel("Student Id :"));
    studentIDPanel.add(new JLabel(firstItem.getStudentId()));
    studentIDPanel.setMaximumSize(maxSize);
    
    JPanel studentEmailPanel = new JPanel();
    studentEmailPanel.setLayout(new GridLayout(1,2));
    studentEmailPanel.add(new JLabel("Student Email :"));
    studentEmailPanel.add(new JLabel(firstItem.getStudentEmail()));
    studentEmailPanel.setMaximumSize(maxSize);

    JPanel checkedOutByPanel = new JPanel();
    checkedOutByPanel.setLayout(new GridLayout(1,2));
    checkedOutByPanel.add(new JLabel("Checked Out By :"));
    checkedOutByPanel.add(new JLabel(firstItem.getEmployeeId()));
    checkedOutByPanel.setMaximumSize(maxSize);

    DateFormat format = new SimpleDateFormat( "h:mm a" );	
    JPanel timePanel = new JPanel();
    timePanel.setLayout(new GridLayout(1,2));
    timePanel.add(new JLabel("Time :"));
    timePanel.add(new JLabel(format.format(firstItem.dueDate)));
    timePanel.setMaximumSize(maxSize);

	panel.add(studentIDPanel);
	panel.add(studentEmailPanel);
	panel.add(checkedOutByPanel);
	panel.add(timePanel);

    itemPane = new JPanel();
	itemPane.setLayout(new GridLayout(1,2));
	itemPane.add(new JLabel("Item Id"));
	itemPane.add(new JLabel("Description"));
	itemPane.setMaximumSize(maxSize);
	panel.add(itemPane);
    for (CheckoutItem item : items) {
      itemPane = new JPanel();
	  itemPane.setLayout(new GridLayout(1,2));
	  itemPane.add(new JLabel(item.getItemId()));
	  itemPane.add(new JLabel(item.getItemDescription()));
	  itemPane.setMaximumSize(maxSize);

	  panel.add(itemPane);
    }
    panel.setVisible(true);
    frame.add(panel);
    frame.setVisible(true);
    frame.pack();
  }
}
