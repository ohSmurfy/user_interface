package Inventory;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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
import java.util.ArrayList;
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
	JButton continueButton;
	JButton removeFromCartBtn;
	JButton addToCartbtn;
	JButton quickAddBtn;
	JPanel cartPanel;
	JPanel inventoryPanel;
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
	    quickAddBtn = new JButton("Quick Add to Cart");
	    quickAddBtn.setEnabled(false); 

//	    quickAdd.addActionListener(new QuickAdd());
	    quickAddPanel.add(quickAddBtn);
	    quickAddPanel.setMaximumSize(new Dimension(600,25));
	    

	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(1,3));
	    labelPanel.add(new JLabel("Inventory"));
	    labelPanel.add(new JLabel(""));
	    labelPanel.add(new JLabel("Checkout Cart"));
	    labelPanel.setMaximumSize(new Dimension(2000,25));
	    
	    
	    
	    JPanel inventoryCartPanel = new JPanel();
	    inventoryCartPanel.setLayout(new GridLayout(1,3));

	    inventoryPanel = new JPanel();
	    inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
	    
	    refreshInventoryPanel();
        
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new FlowLayout());
		
		removeFromCartBtn = new JButton("<");
//		removeFromCartBtn.addActionListener(new RemoveItemListener());
		removeFromCartBtn.setPreferredSize(new Dimension(50,50));
		removeFromCartBtn.setEnabled(false); 

		addToCartbtn = new JButton(">");
//		addToCartbtn.addActionListener(new AddItemListener());
		addToCartbtn.setPreferredSize(new Dimension(50,50));
		addToCartbtn.setEnabled(false); 

		buttonPanel.add(addToCartbtn);
		buttonPanel.add(removeFromCartBtn);
		
	    cartPanel = new JPanel();
	    cartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

	    
	    inventoryCartPanel.add(inventoryPanel);
	    inventoryCartPanel.add(buttonPanel);
	    inventoryCartPanel.add(cartPanel);
	    
	    
	    
	    JPanel continuePanel = new JPanel();
	    continuePanel.setLayout(new GridLayout(1,3));
	    continuePanel.add(new JLabel(""));
	    continuePanel.add(new JLabel(""));

		continueButton = new JButton("Continue");
		continueButton.setEnabled(false); 

//		continueButton.addActionListener(new ContinueButtonListener());
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


		panel.setVisible(true);
	}
	
	public void refreshInventoryPanel() {
	    JPanel inventoryHeader = new JPanel();
	    inventoryHeader.setLayout(new GridLayout(1,3));
	    inventoryHeader.add(new JLabel("Item ID"));
	    inventoryHeader.add(new JLabel("Discription"));
	    inventoryHeader.add(new JLabel(""));
	    inventoryHeader.setMaximumSize(new Dimension(2000,25));
	    inventoryPanel.add(inventoryHeader);
	    try {
	    	SQLInventoryItemRepo inventoryTable = new SQLInventoryItemRepo();
	    	inventory = inventoryTable.getAll();
	    } catch (SQLException e) {JOptionPane.showMessageDialog(panel, "SQL Error" + e);}
	    
        for (InventoryItem item : inventory) {
    	    JPanel itemPanel = new JPanel();
    	    itemPanel.setLayout(new GridLayout(1,3));
    	    itemPanel.add(new JLabel(item.getId()));
    	    itemPanel.add(new JLabel(item.getDiscription()));
    	    itemPanel.add(new JCheckBox());
    	    itemPanel.setMaximumSize(new Dimension(2000,25));
    	    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	    inventoryPanel.add(Box.createVerticalStrut(5));
    	    inventoryPanel.add(itemPanel);
        }		
	}
	private void refreshCurrentInv() {
		inventoryPanel.removeAll();
		List<InventoryItem> temp = new ArrayList<InventoryItem>();
		JPanel inventoryHeader = new JPanel();
	    inventoryHeader.setLayout(new GridLayout(1,3));
	    inventoryHeader.add(new JLabel("Item ID"));
	    inventoryHeader.add(new JLabel("Discription"));
	    inventoryHeader.add(new JLabel(""));
	    inventoryHeader.setMaximumSize(new Dimension(2000,25));
	    inventoryPanel.add(inventoryHeader);
	    for (InventoryItem inventoryItem : inventory){
		    for (InventoryItem cartItem : cart){
		    	if (inventoryItem.getId().equals(cartItem.getId())) {
		    		temp.add(inventoryItem);
		    	}
		    }
	    }
	    inventory.removeAll(temp);
	    for (InventoryItem item : inventory) {
    	    JPanel itemPanel = new JPanel();
    	    itemPanel.setLayout(new GridLayout(1,3));
    	    itemPanel.add(new JLabel(item.getId()));
    	    itemPanel.add(new JLabel(item.getDiscription()));
    	    itemPanel.add(new JCheckBox());
    	    itemPanel.setMaximumSize(new Dimension(2000,25));
    	    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	    inventoryPanel.add(Box.createVerticalStrut(5));
    	    inventoryPanel.add(itemPanel);
        }		
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
	}
	public void refreshCartPanel() {
		cartPanel.removeAll();
		
	    JPanel cartHeader = new JPanel();
	    cartHeader.setLayout(new GridLayout(1,3));
	    cartHeader.add(new JLabel("Item ID"));
	    cartHeader.add(new JLabel("Discription"));
	    cartHeader.add(new JLabel(""));
	    cartHeader.setMaximumSize(new Dimension(2000,25));
	    cartPanel.add(cartHeader);

        for (InventoryItem item : cart) {
    	    JPanel itemPanel = new JPanel();
    	    itemPanel.setLayout(new GridLayout(1,3));
    	    itemPanel.add(new JLabel(item.getId()));
    	    itemPanel.add(new JLabel(item.getDiscription()));
    	    itemPanel.add(new JCheckBox());
    	    itemPanel.setMaximumSize(new Dimension(2000,25));
    	    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	    cartPanel.add(Box.createVerticalStrut(5));
    	    cartPanel.add(itemPanel);
        }		
        cartPanel.revalidate();
        cartPanel.repaint();
	}
	private class ContinueButtonListener implements ActionListener {
			ReminderFrame reminder;
		public void actionPerformed(ActionEvent e) {
			reservation.updateReservedItems(cart);
			reminder = new ReminderFrame(reservation);
			reminder.setLocationRelativeTo(null);
			reminder.pack();
			reminder.setVisible(true);
		}
		
	}
	
	private class AddItemListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//update by adding items to cart pane
		}
	}
	
	private class RemoveItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//update by removing items from cart panel
		}
	}
	private class ReservationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			inventoryPanel.remove(inventoryPanel.getComponent(0));
			continueButton.setEnabled(true);
			removeFromCartBtn.setEnabled(true);
			addToCartbtn.setEnabled(true);
			quickAddBtn.setEnabled(true);
			Mockapi fakeApi = new Mockapi();
			reservation = fakeApi.getUser(studentId.getText());
			cart = reservation.getItems();
			refreshCartPanel();
			refreshCurrentInv();
		}
	}
}