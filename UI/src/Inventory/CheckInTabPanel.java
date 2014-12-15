package Inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckInTabPanel extends JPanel{
	
	CheckInTabPanel panel;
	JLabel idLabel = new JLabel("Student Username");
	JTextField studentId = new JTextField(10);
	JLabel employeeIdLabel = new JLabel("Employee Name");
	JTextField employeeId = new JTextField(10);
    JButton getItems = new JButton("Get Items");
    CheckInListPanel checkInListPanel;
    Dimension maxSize = new Dimension(600,50);
    GridLayout headerLayout = new GridLayout(2,3);
    
	  public CheckInTabPanel() {
	    panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setBackground(new Color(162,181,205));
	    checkInListPanel = new CheckInListPanel();
	    getItems.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {checkInListPanel.refresh(studentId.getText());};
	    });
	    
	    JPanel checkInHeader = new JPanel();
	    checkInHeader.setLayout(headerLayout);
	    checkInHeader.add(idLabel);
	    checkInHeader.add(studentId);
	    checkInHeader.add(getItems);
	    checkInHeader.add(employeeIdLabel);
	    checkInHeader.add(employeeId);
	    checkInHeader.setBackground(new Color(162,181,205));
	    checkInHeader.setVisible(true);
	    
	    checkInHeader.setMaximumSize(maxSize);
	    panel.add(checkInHeader);
	    panel.add(checkInListPanel);
	    panel.setVisible(true);
	  }
	  public String getEmployeeId() {
		  return employeeId.getText();
	  }
	  public void overviewPanelClicked(String sId) {
		  studentId.setText(sId);
		  checkInListPanel.refresh(sId);
	  }
	}