package Inventory;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;

public class ExpandedOverdueView extends JFrame{
  ExpandedOverdueView frame;
  
  public ExpandedOverdueView(CheckoutItem item) {
	frame = this;

	JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JPanel studentIDPanel = new JPanel();
    studentIDPanel.setLayout(new GridLayout(1,2));
    studentIDPanel.add(new JLabel("Student Id :"));
    studentIDPanel.add(new JLabel(item.getStudentId()));
    studentIDPanel.setMaximumSize(new Dimension(2000,25));
    
    JPanel studentEmailPanel = new JPanel();
    studentEmailPanel.setLayout(new GridLayout(1,2));
    studentEmailPanel.add(new JLabel("Student Email :"));
    studentEmailPanel.add(new JLabel(item.getStudentEmail()));
    studentEmailPanel.setMaximumSize(new Dimension(2000,25));

    JPanel checkedOutByPanel = new JPanel();
    checkedOutByPanel.setLayout(new GridLayout(1,2));
    checkedOutByPanel.add(new JLabel("Checked Out By :"));
    checkedOutByPanel.add(new JLabel(item.getEmployeeId()));
    checkedOutByPanel.setMaximumSize(new Dimension(2000,25));

    DateFormat format = new SimpleDateFormat( "yyyy/MM/dd h:mm a" );	
    JPanel timePanel = new JPanel();
    timePanel.setLayout(new GridLayout(1,2));
    timePanel.add(new JLabel("due date/time :"));
    timePanel.add(new JLabel(format.format(item.dueDate)));
    timePanel.setMaximumSize(new Dimension(2000,25));

	panel.add(studentIDPanel);
	panel.add(studentEmailPanel);
	panel.add(checkedOutByPanel);
	panel.add(timePanel);

    JPanel itemIdPanel = new JPanel();
    itemIdPanel.setLayout(new GridLayout(1,2));
    itemIdPanel.add(new JLabel("Item Id :"));
    itemIdPanel.add(new JLabel(item.getItemId()));
    itemIdPanel.setMaximumSize(new Dimension(2000,25));

    JPanel descriptionPanel = new JPanel();
    descriptionPanel.setLayout(new GridLayout(1,2));
    descriptionPanel.add(new JLabel("Item Description:"));
    descriptionPanel.add(new JLabel(item.getItemDescription()));
    descriptionPanel.setMaximumSize(new Dimension(2000,25));
    panel.setVisible(true);
    frame.add(panel);
    frame.setVisible(true);
    frame.pack();
  }
}
