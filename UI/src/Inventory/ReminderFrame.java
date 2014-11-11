package Inventory;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReminderFrame extends JFrame{
	
	ReminderFrame frame;
	JPanel remindersForItems;
	JPanel buttonPanel;
	JButton okay;
	JPanel reminderPanel;
	Reservation currentReservation;
	public ReminderFrame(Reservation reservation) {
		frame = this;
		currentReservation = reservation;
		frame.setTitle("Reminder");
		
		remindersForItems = new JPanel();
		remindersForItems.setLayout(new BoxLayout(remindersForItems, BoxLayout.Y_AXIS));
		
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(2000,50));
		
        for (InventoryItem item : currentReservation.getItems()) {
        	reminderPanel = new JPanel();
        	reminderPanel.setLayout(new GridLayout(1,2));
        	reminderPanel.add(new JLabel(item.getDiscription()));
        	reminderPanel.add(new JLabel(item.getReminder()));
        	reminderPanel.setMaximumSize(new Dimension(2000,50));
        	remindersForItems.add(reminderPanel);
        }
        
		okay = new JButton("Okay");
		okay.addActionListener(new OkayListener());
		
		buttonPanel.add(okay);
		remindersForItems.add(buttonPanel);
		frame.add(remindersForItems);
		frame.pack();
	}
	
	private class OkayListener implements ActionListener {
		ConfirmationBox confirmation;
		public void actionPerformed(ActionEvent e) {
			confirmation = new ConfirmationBox(currentReservation);
			confirmation.setLocationRelativeTo(null);
			confirmation.pack();
			confirmation.setVisible(true);
			frame.dispose();
			
		}
	}
	
}