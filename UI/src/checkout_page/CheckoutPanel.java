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
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.BorderLayout;

public class CheckoutPanel extends JPanel {
	
	CheckoutPanel panel;
	
	public CheckoutPanel() {
		panel = this;
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel inventoryPanel = new JPanel();
		JPanel inventoryList = new JPanel();
		inventoryList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel inventory = new JLabel("Inventory");
		inventoryPanel.setLayout(new BorderLayout());
		inventoryPanel.add(inventoryList, BorderLayout.NORTH);
		inventoryPanel.add(inventoryList, BorderLayout.CENTER);
		inventoryPanel.setPreferredSize(new Dimension(200, 100));
		inventoryPanel.setVisible(true);
		
		JPanel cartPanel = new JPanel();
		JPanel cartList = new JPanel();
		cartList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel cart = new JLabel("Cart");
		cartPanel.setLayout(new BorderLayout());
		cartPanel.add(cartList,BorderLayout.CENTER);
		cartPanel.add(cart,BorderLayout.NORTH);
		cartPanel.setPreferredSize(new Dimension(200,100));
		cartPanel.setVisible(true);
		
		JPanel idSearchPanel = new JPanel();
		JTextField studentIdSearch = new JTextField(10);
		JButton getItemsByStudentId = new JButton("Enter");
		idSearchPanel.setPreferredSize(new Dimension(30,100));
		idSearchPanel.setLayout(new BorderLayout());
		idSearchPanel.add(studentIdSearch,BorderLayout.EAST);
		idSearchPanel.add(getItemsByStudentId, BorderLayout.WEST);
		idSearchPanel.setVisible(true);
		
		JPanel arrowButtonPanel = new JPanel();
		JButton leftArrow = new JButton("<");
		leftArrow.addActionListener(new RemoveItemListener());
		JButton rightArrow = new JButton(">");
		rightArrow.addActionListener(new AddItemListener());
		arrowButtonPanel.setPreferredSize(new Dimension(150,100));
		arrowButtonPanel.setLayout(new BorderLayout());
		arrowButtonPanel.add(leftArrow,BorderLayout.NORTH);
		arrowButtonPanel.add(rightArrow,BorderLayout.SOUTH);
		arrowButtonPanel.setVisible(true);
		
		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ContinueButtonListener());
		continuePanel.setPreferredSize(new Dimension(30,100));
		continuePanel.setLayout(new BorderLayout());
		continuePanel.add(continueButton,BorderLayout.CENTER);
		
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

