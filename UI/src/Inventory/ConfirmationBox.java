package Inventory;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class ConfirmationBox extends JFrame{
	
	ConfirmationBox cframe;
	JPanel itemPanel;
	Reservation currentReservation;
	JTextField employeeId;
	public ConfirmationBox(Reservation reservation) {
		cframe = this;
		currentReservation = reservation;
		employeeId = new JTextField(10);
		cframe.setTitle("Confirmation");
		JPanel summaryPanel = new JPanel();
		
		summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
		
		JPanel itemSummaryPanel = new JPanel();
		itemSummaryPanel.setLayout(new BoxLayout(itemSummaryPanel, BoxLayout.Y_AXIS));
		itemSummaryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(1,2));
		itemPanel.add(new JLabel("Item Id"));
		itemPanel.add(new JLabel("Description"));
		itemSummaryPanel.add(itemPanel);
		
        for (InventoryItem item : reservation.getItems()) {
    		itemPanel = new JPanel();
    		itemPanel.setLayout(new GridLayout(1,2));
    		itemPanel.add(new JLabel(item.getId()));
    		itemPanel.add(new JLabel(item.getDiscription()));
    		itemSummaryPanel.add(itemPanel);
        }
        summaryPanel.add(itemSummaryPanel);
        
		JPanel studentId = new JPanel();
		studentId.setLayout(new GridLayout(1,2));
		studentId.add(new JLabel("Student Id"));
		studentId.add(new JLabel(currentReservation.getStudentId()));
		summaryPanel.add(studentId);
		
		JPanel studentEmail = new JPanel();
		studentEmail.setLayout(new GridLayout(1,2));
		studentEmail.add(new JLabel("Student Email"));
		studentEmail.add(new JLabel(currentReservation.getStudentEmail()));
		summaryPanel.add(studentEmail);
        

	    DateFormat format = new SimpleDateFormat( "yyy/MM/dd h:mm a" );
		
		JPanel dueDate = new JPanel();
		dueDate.setLayout(new GridLayout(1,2));
		dueDate.add(new JLabel("Due Date"));
		dueDate.add(new JLabel(format.format(currentReservation.getDue())));
		summaryPanel.add(dueDate);
        
		JPanel employeeIDPanel = new JPanel();
		employeeIDPanel.setLayout(new GridLayout(1,2));
		employeeIDPanel.add(new JLabel("Enter Employee ID:"));
		employeeIDPanel.add(employeeId);
		summaryPanel.add(employeeIDPanel);
		
		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setLayout(new GridLayout(1,2));

		JButton cancel = new JButton("Cancel");
		JButton checkout = new JButton("Checkout");

		cancel.addActionListener(new CancelActionListener());
		checkout.addActionListener(new CheckoutActionListener());
		checkOutPanel.add(cancel);
		checkOutPanel.add(checkout);
		
		summaryPanel.add(checkOutPanel);
		cframe.add(summaryPanel);

	}

	private class CancelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//work with database	
			cframe.dispose();
		}
	}
	
	private class CheckoutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//work with database	
			cframe.dispose();
		}
	}
	
}