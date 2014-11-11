package Inventory;

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
	JLabel idLabel = new JLabel("Student Id");
	JTextField studentId = new JTextField(10);
	JLabel employeeIdLabel = new JLabel("Employee Id");
	JTextField employeeId = new JTextField(10);
    JButton getItems = new JButton("Get Items");
    CheckInListPanel checkInListPanel;
	  public CheckInTabPanel() {
	    panel = this;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    checkInListPanel = new CheckInListPanel();
	    getItems.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {checkInListPanel.refresh(studentId.getText());};
	    });
	    
	    JPanel checkInHeader = new JPanel();
	    checkInHeader.setLayout(new GridLayout(2, 3));
	    checkInHeader.add(idLabel);
	    checkInHeader.add(studentId);
	    checkInHeader.add(getItems);
	    checkInHeader.add(employeeIdLabel);
	    checkInHeader.add(employeeId);
	    checkInHeader.setVisible(true);
	    
	    checkInHeader.setMaximumSize(new Dimension(600,50));
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