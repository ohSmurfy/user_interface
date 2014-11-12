package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CheckoutInventoryItem extends JPanel{
	CheckoutInventoryItem itemPanel;
	JButton add;
	InventoryItem current;
	public CheckoutInventoryItem(InventoryItem item) {
		itemPanel = this;    
		current = item;
		itemPanel.setLayout(new GridLayout(1,3));
	    itemPanel.add(new JLabel(item.getId()));
	    itemPanel.add(new JLabel(item.getDiscription()));
	    add = new JButton("Add to Cart");
	    add.addActionListener(new addToCart());
	    itemPanel.add(add);
	    itemPanel.setMaximumSize(new Dimension(2000,25));
	    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}
	
	private class addToCart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
        	CheckoutPanel checkoutTab =((CheckoutPanel) itemPanel.getParent().getParent().getParent());
        	List<InventoryItem> inventory = checkoutTab.getInventory();
        	List<InventoryItem> cart = checkoutTab.getCart();
	        inventory.remove(current);
	        cart.add(current);
	        checkoutTab.refreshCurrentInv();
	        checkoutTab.refreshCartPanel();
		}
	}
}

