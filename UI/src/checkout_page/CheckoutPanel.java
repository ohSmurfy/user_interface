package checkout_page;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class CheckoutPanel extends JPanel {
	
	CheckoutPanel panel;
	
	public CheckoutPanel() {
		panel = this;
		JPanel itemListPanel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JButton continueButton = new JButton("Continue");
		continueButton.setPreferredSize(new Dimension(15,30));
		continueButton.addActionListener(new ContinueButtonListener());
		gbc.gridx = 2;
		gbc.gridy = 2;
		panel.add(continueButton,gbc);
		
		JPanel inventory = new JPanel();
		inventory.setPreferredSize(new Dimension(100,150));
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(itemListPanel, gbc);
		
		JButton addToCartArrow = new JButton(">");
		addToCartArrow.setPreferredSize(new Dimension(50,50));
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(addToCartArrow, gbc);
		
		
		JButton removeFromCartArrow = new JButton("<");
		removeFromCartArrow.setPreferredSize(new Dimension(50,50));
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(removeFromCartArrow, gbc);
		
		JPanel cart = new JPanel();
		cart.setPreferredSize(new Dimension(100,150));
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(cart,gbc);
		
		
		JTextField studentIdSearch = new JTextField();
		studentIdSearch.setPreferredSize(new Dimension(20,60));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(studentIdSearch, gbc);
		
		JButton getItemsByStudentId = new JButton("Enter");
		getItemsByStudentId.setPreferredSize(new Dimension(15,30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(getItemsByStudentId,gbc);
		
		panel.setVisible(true);
	}
	
	private class ContinueButtonListener implements ActionListener {
		ReminderFrame reminder;
					
		public void actionPerformed(ActionEvent e) {
			reminder = new ReminderFrame();
			reminder.setLocationRelativeTo(null);
			reminder.pack();
			reminder.setVisible(true);
		}
		
	}
	
	private class AddItemListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//update by adding items to cart panel
		}
	}
	
	private class RemoveItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//update by removing items from cart panel
		}
	}
	
	
	
}

