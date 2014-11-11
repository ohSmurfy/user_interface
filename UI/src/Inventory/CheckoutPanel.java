package Inventory;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;

public class CheckoutPanel extends JPanel {
	
	CheckoutPanel panel;
	JTextField studentId;
	JTextField itemId;
	Reservation reservation;
	List<InventoryItem> inventory;
	List<InventoryItem> cart;
	
	public CheckoutPanel() {
		panel = this;
		studentId = new JTextField(10);
		itemId = new JTextField(10);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    JPanel reserveationPanel = new JPanel();
	    reserveationPanel.setLayout(new GridLayout(1,3));
	    
	    reserveationPanel.add(new JLabel("Student Id: "));
	    
	    reserveationPanel.add(studentId);
	    JButton getReservation = new JButton("Get Reservation!");
	    getReservation.addActionListener(new ReservationListener());
	    reserveationPanel.add(getReservation);
	    reserveationPanel.setMaximumSize(new Dimension(600,25));
	    
	    JPanel quickAddPanel = new JPanel();
	    quickAddPanel.setLayout(new GridLayout(1,3));
	    quickAddPanel.add(new JLabel("Item Id: "));
	    quickAddPanel.add(itemId);
	    JButton quickAddBtn = new JButton("Quick Add to Cart");
//	    quickAdd.addActionListener(new QuickAdd());
	    quickAddPanel.add(quickAddBtn);
	    quickAddPanel.setMaximumSize(new Dimension(600,25));
	    
	    try {
	    	SQLInventoryItemRepo inventoryTable = new SQLInventoryItemRepo();
	    	inventory = inventoryTable.getAll();
	    } catch (SQLException e) {JOptionPane.showMessageDialog(panel, "SQL Error" + e);}
	    
	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(1,3));
	    labelPanel.add(new JLabel("Inventory"));
	    labelPanel.add(new JLabel(""));
	    labelPanel.add(new JLabel("Checkout Cart"));
	    labelPanel.setMaximumSize(new Dimension(2000,25));
	    
	    
	    
	    JPanel inventoryCartPanel = new JPanel();
	    inventoryCartPanel.setLayout(new GridLayout(1,3));
	    inventoryCartPanel.add(new JLabel(""));
	    inventoryCartPanel.add(new JLabel(""));
	    inventoryCartPanel.add(new JLabel(""));
	    
	    
	    JPanel continuePanel = new JPanel();
	    continuePanel.setLayout(new GridLayout(1,3));
	    continuePanel.add(new JLabel(""));
	    continuePanel.add(new JLabel(""));

		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ContinueButtonListener());
	    continuePanel.add(continueButton);  
	    continuePanel.setMaximumSize(new Dimension(2000,50));

	    
	    panel.add(reserveationPanel);
	    panel.add(Box.createVerticalStrut(5));
	    panel.add(quickAddPanel);	    
	    panel.add(Box.createVerticalStrut(5));
	    panel.add(labelPanel);
	    panel.add(Box.createVerticalStrut(5));
	    panel.add(inventoryCartPanel);
	    panel.add(Box.createVerticalStrut(5));
	    panel.add(continuePanel);

//		panel.setLayout(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		
//		
//		JPanel inventoryPanel = new JPanel();
//		Dimension inventoryPanelSize = new Dimension(100,200);
//		JPanel inventoryList = new JPanel();
//		inventoryList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		JLabel inventory = new JLabel("Inventory");
//		inventoryPanel.setPreferredSize(inventoryPanelSize);
//		inventoryPanel.setMaximumSize(inventoryPanelSize);
//		inventoryPanel.setMinimumSize(inventoryPanelSize);
//		inventoryPanel.setLayout(new BoxLayout(inventoryPanel,BoxLayout.PAGE_AXIS));
//		inventoryPanel.add(inventory);
//		inventoryPanel.add(Box.createVerticalGlue());
//		inventoryPanel.add(inventoryList);
//		inventoryPanel.setVisible(true);
//		gbc.weightx = 0.3;
//		gbc.weighty = 0.3;
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		gbc.ipadx = 10;
//		gbc.ipady = 15;
//		panel.add(inventoryPanel,gbc);
//		
//		
//		JPanel cartPanel = new JPanel();
//		JPanel cartList = new JPanel();
//		cartList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		JLabel cart = new JLabel("Cart");
//		cartPanel.setPreferredSize(new Dimension(100,200));
//		cartPanel.setLayout(new BoxLayout(cartPanel,BoxLayout.PAGE_AXIS));
//		cartPanel.add(cart);
//		cartPanel.add(Box.createVerticalGlue());
//		cartPanel.add(cartList);
//		cartPanel.setVisible(true);
//		gbc.weightx = 0.5;
//		gbc.weighty = 0.5;
//		gbc.gridx = 2;
//		gbc.gridy = 1;
//		gbc.ipadx = 10;
//		gbc.ipady = 15;
//		panel.add(cartPanel, gbc);
//		
//		JPanel idSearchPanel = new JPanel();
//		JTextField studentIdSearch = new JTextField(10);
//		JButton getItemsByStudentId = new JButton("Enter");
//		JLabel patronId = new JLabel("Patron ID");
//		idSearchPanel.setPreferredSize(new Dimension(100,30));
//		idSearchPanel.setLayout(new BoxLayout(idSearchPanel,BoxLayout.LINE_AXIS));
//		idSearchPanel.add(studentIdSearch);
//		idSearchPanel.add(Box.createHorizontalGlue());
//		idSearchPanel.add(getItemsByStudentId);
//		idSearchPanel.setVisible(true);
//		gbc.weightx = 0.5;
//		gbc.weighty = 0.5;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.ipadx = 10;
//		gbc.ipady = 10;
//		panel.add(idSearchPanel,gbc);
//		
//		JPanel arrowButtonPanel = new JPanel();
//		Dimension arrowButtonSize = new Dimension(40,40);
//		JButton leftArrow = new JButton("<");
//		leftArrow.addActionListener(new RemoveItemListener());
//		leftArrow.setPreferredSize(arrowButtonSize);
//		leftArrow.setAlignmentX(Component.CENTER_ALIGNMENT);
//		JButton rightArrow = new JButton(">");
//		rightArrow.addActionListener(new AddItemListener());
//		rightArrow.setPreferredSize(arrowButtonSize);
//		rightArrow.setAlignmentX(Component.CENTER_ALIGNMENT);
//		arrowButtonPanel.setPreferredSize(new Dimension(60,200));
//		arrowButtonPanel.setLayout(new BoxLayout(arrowButtonPanel,BoxLayout.PAGE_AXIS));
//		arrowButtonPanel.add(leftArrow);
//		arrowButtonPanel.add(Box.createVerticalGlue());
//		arrowButtonPanel.add(rightArrow);
//		arrowButtonPanel.setVisible(true);
//		gbc.weightx = 0.5;
//		gbc.weighty = 0.5;
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		gbc.ipadx = 10;
//		gbc.ipady = 15;
//		panel.add(arrowButtonPanel,gbc);

		
		panel.setVisible(true);
	}
	
	private class ContinueButtonListener implements ActionListener {
			ReminderFrame reminder;
			List<InventoryItem> inv;
		public void actionPerformed(ActionEvent e) {
		    Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			try{
				SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
				inv = inventory.getAll();
			
			} catch (SQLException e2) {}
			reminder = new ReminderFrame(new Reservation("mike","Mike@gmail.com",currentTimestamp, inv));
			reminder.setLocationRelativeTo(null);
			reminder.pack();
			reminder.setVisible(true);
		}
		
	}
	
//	private class AddItemListener implements ActionListener {
//		
//		public void actionPerformed(ActionEvent e) {
//			//update by adding items to cart panel
//		}
//	}
//	
//	private class RemoveItemListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			//update by removing items from cart panel
//		}
//	}
	private class ReservationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Mockapi fakeApi = new Mockapi();
			reservation = fakeApi.getUser(studentId.getText());
		}
	}
}