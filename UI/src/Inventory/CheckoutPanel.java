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
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;

public class CheckoutPanel extends JPanel {
	
	CheckoutPanel panel;
	JTextField studentId;
	JTextField itemId;
	Reservation reservation;
	List<InventoryItem> originalInventory;
	List<InventoryItem> inventory;
	List<InventoryItem> cart;
	JButton continueButton;
	JButton quickAddBtn;
	JPanel cartPanel;
	JPanel inventoryPanel;
	Dimension maxSize = new Dimension(600,25);
	Color themeColor = new Color(162,181,205);
	JButton getReservation;
	public CheckoutPanel() {
		panel = this;
	    panel.setBackground(themeColor);
		studentId = new JTextField(10);
		itemId = new JTextField(10);
		itemId.setEditable(false);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    JPanel reserveationPanel = new JPanel();
	    reserveationPanel.setLayout(new GridLayout(1,3));
	    
	    reserveationPanel.add(new JLabel("Student Id: "));
	    reserveationPanel.setBackground(themeColor);
	    
	    reserveationPanel.add(studentId);
	    getReservation = new JButton("Get Reservation!");
	    getReservation.addActionListener(new ReservationListener());
	    reserveationPanel.add(getReservation);
	    reserveationPanel.setMaximumSize(maxSize);
	    
	    JPanel quickAddPanel = new JPanel();
	    quickAddPanel.setLayout(new GridLayout(1,3));
	    quickAddPanel.add(new JLabel("Item Id: "));
	    quickAddPanel.setBackground(themeColor);
	    quickAddPanel.add(itemId);
	    quickAddBtn = new JButton("Quick Add to Cart");
	    quickAddBtn.setEnabled(false); 

	    quickAddBtn.addActionListener(new QuickAdd());
	    quickAddPanel.add(quickAddBtn);
	    quickAddPanel.setMaximumSize(maxSize);
	    

	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(1,2));
	    labelPanel.add(new JLabel("Inventory"));
	    labelPanel.add(new JLabel("Checkout Cart"));

	    labelPanel.setMaximumSize(maxSize);
	    labelPanel.setBackground(themeColor);
	    
	    JPanel inventoryCartPanel = new JPanel();
	    inventoryCartPanel.setLayout(new GridLayout(1,2));

	    inventoryPanel = new JPanel();
	    inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
	    inventoryPanel.setBackground(themeColor);
	            
		
	    cartPanel = new JPanel();
	    cartPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
	    cartPanel.setBackground(themeColor);
	    
	    inventoryCartPanel.add(inventoryPanel);
	    inventoryCartPanel.add(cartPanel);
	    
	    
	    
	    JPanel continuePanel = new JPanel();
	    continuePanel.setLayout(new GridLayout(1,3));
	    continuePanel.add(new JLabel(""));
	    continuePanel.add(new JLabel(""));
	    continuePanel.setBackground(themeColor);

		continueButton = new JButton("Continue");
		continueButton.setEnabled(false); 

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


		panel.setVisible(true);
		refreshInvPage();
	}
	
	public boolean enabled() {
		if (quickAddBtn == null) return false;
		else return quickAddBtn.isEnabled();
	}
	public void refreshCheckouttab() {
		refreshInvPage();
		cart = new ArrayList<InventoryItem>();
		refreshCartPanel();
		studentId.setText("");
		itemId.setText("");
		continueButton.setEnabled(false);
		quickAddBtn.setEnabled(false);
		itemId.setEditable(false);

	}
	public void refreshInvPage() {
	    JPanel inventoryHeader = new JPanel();
	    inventoryPanel.removeAll();
	    inventoryHeader.setLayout(new GridLayout(1,3));
	    inventoryHeader.add(new JLabel("Item ID"));
	    inventoryHeader.add(new JLabel("Description"));
	    inventoryHeader.add(new JLabel(""));
	    inventoryHeader.setBackground(themeColor);
	    inventoryHeader.setMaximumSize(new Dimension(2000,25));
	    inventoryPanel.add(inventoryHeader);
	    try {
	    	SQLInventoryItemRepo inventoryTable = new SQLInventoryItemRepo();
	    	inventory = inventoryTable.getAll();
	    } catch (SQLException e) {JOptionPane.showMessageDialog(panel, "SQL Error" + e);}
	    
        for (InventoryItem item : inventory) {
    	    inventoryPanel.add(Box.createVerticalStrut(5));
    	    inventoryPanel.add(new CheckoutInventoryItem(item, enabled()));
        }		
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
	}
	
	public void goingOutClicked(Reservation newRes) {
		studentId.setText(newRes.getStudentId());
		getReservation.doClick();

	}
	
	public void refreshCurrentInv() {
		inventoryPanel.removeAll();
		List<InventoryItem> temp = new ArrayList<InventoryItem>();
		JPanel inventoryHeader = new JPanel();
	    inventoryHeader.setLayout(new GridLayout(1,3));
	    inventoryHeader.add(new JLabel("Item ID"));
	    inventoryHeader.add(new JLabel("Description"));
	    inventoryHeader.add(new JLabel(""));
	    inventoryHeader.setBackground(themeColor);
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
    	    inventoryPanel.add(Box.createVerticalStrut(5));
    	    inventoryPanel.add(new CheckoutInventoryItem(item, enabled()));
        }			
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
	}
	public void refreshCartPanel() {
		cartPanel.removeAll();
		
	    JPanel cartHeader = new JPanel();
	    cartHeader.setLayout(new GridLayout(1,3));
	    cartHeader.add(new JLabel("Item ID"));
	    cartHeader.add(new JLabel("Description"));
	    cartHeader.add(new JLabel(""));
	    cartHeader.setBackground(themeColor);
	    cartHeader.setMaximumSize(new Dimension(2000,25));
	    cartPanel.add(cartHeader);

        for (InventoryItem item : cart) {
    	    cartPanel.add(Box.createVerticalStrut(5));
    	    cartPanel.add(new CheckoutCartItem(item));
        }			
        cartPanel.revalidate();
        cartPanel.repaint();
	}
	private class ContinueButtonListener implements ActionListener {
			ReminderFrame reminder;
		public void actionPerformed(ActionEvent e) {
			reservation.updateReservedItems(cart);
			reminder = new ReminderFrame(reservation, panel);
			reminder.setLocationRelativeTo(null);
			reminder.pack();
			reminder.setVisible(true);
		}
		
	}
	private class QuickAdd implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InventoryItem tempItem = null; 

			for (InventoryItem item : inventory) {
				if (item.getId().equals(itemId.getText())){
					tempItem = item;
				}
			}
			if (tempItem != null) {
				inventory.remove(tempItem);
				cart.add(tempItem);
			}
			refreshCartPanel();
			refreshCurrentInv();
		}
	}

	private class ReservationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        if (studentId.getText().equals(null) || studentId.getText().equals("")) 
	        	JOptionPane.showMessageDialog(panel,"please enter student id before continuing");
	        else {
	        	if (cart != null){
	        		inventory.addAll(cart);
	        		cart = new ArrayList<InventoryItem>();
	        	}
				continueButton.setEnabled(true);
				quickAddBtn.setEnabled(true);
				itemId.setEditable(true);
				
				
				APIReference api = new APIReference();
				try {
					ArrayList<Reservation> resverations = api.getReservations();
					for (Reservation r: resverations){
						if (studentId.getText().equals(r.getStudentId())){
							reservation = r;
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				cart = reservation.getItems();
				refreshCartPanel();
				refreshCurrentInv();
	        }
		}
	}
	public List<InventoryItem> getCart() {
		return cart;
	}
	public List<InventoryItem> getInventory() {
		return inventory;
	}
}