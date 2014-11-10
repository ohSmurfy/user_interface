package checkout_page;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReminderFrame extends JFrame{
	
	ReminderFrame frame;
	
	public ReminderFrame() {
		// needs to communicate with the database
		frame = this;
		frame.setTitle("Reminder");
		JPanel remindersForItems = new JPanel();
		JLabel tester = new JLabel("Testing");
		remindersForItems.setPreferredSize(new Dimension(200,300));
		JButton okay = new JButton("Okay");
		okay.setPreferredSize(new Dimension(35,60));
		okay.setMaximumSize(new Dimension(35,60));
		okay.setMinimumSize(new Dimension(35,60));
		okay.addActionListener(new OkayListener());
		
		frame.setLayout(new BorderLayout());
		
		frame.add(remindersForItems,BorderLayout.PAGE_START);
		frame.add(okay,BorderLayout.CENTER);
		
		frame.setPreferredSize(new Dimension(640,480));
	}
	
	private class OkayListener implements ActionListener {
		ConfirmationBox confirmation;
		public void actionPerformed(ActionEvent e) {
			confirmation = new ConfirmationBox();
			confirmation.setLocationRelativeTo(null);
			confirmation.pack();
			confirmation.setVisible(true);
			
		}
	}
	
}