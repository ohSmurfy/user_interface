package Inventory;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ReminderFrame extends JFrame{
	
	ReminderFrame frame;
	JPanel remindersForItems;
	JPanel buttonPanel;
	JButton okay;
	JPanel reminderPanel;
	Reservation currentReservation;
	CheckoutPanel panel;
	Dimension maxSize = new Dimension(2000,50);
	GridLayout reminderPanelLayout = new GridLayout(1,3);
	
	public ReminderFrame(Reservation reservation, CheckoutPanel checkoutPanel) {
		frame = this;
		panel = checkoutPanel;
		

		currentReservation = reservation;
		frame.setTitle("Reminder");
		
		remindersForItems = new JPanel();
		remindersForItems.setLayout(new BoxLayout(remindersForItems, BoxLayout.Y_AXIS));
		
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(maxSize);
		
        for (InventoryItem item : currentReservation.getItems()) {
        	reminderPanel = new JPanel();
        	reminderPanel.setLayout(reminderPanelLayout);
        	reminderPanel.add(new JCheckBox());
        	reminderPanel.add(new JLabel(item.getDescription()));
        	reminderPanel.add(new JLabel(item.getReminder()));
        	reminderPanel.setMaximumSize(maxSize);
        	remindersForItems.add(reminderPanel);
        }
        
		okay = new JButton("Done");
	    okay.addActionListener(new DoneClicked());
		
		buttonPanel.add(okay);
		remindersForItems.add(buttonPanel);
		frame.add(remindersForItems);
		frame.pack();
	}
	
	private class DoneClicked implements ActionListener {
		public void actionPerformed(ActionEvent e)
        {
			ConfirmationBox confirmation = new ConfirmationBox(currentReservation, panel);
			confirmation.setLocationRelativeTo(null);
			confirmation.pack();
			confirmation.setVisible(true);
			frame.dispose();
        }
	}
}