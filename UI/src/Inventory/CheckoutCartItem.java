package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckoutCartItem extends JPanel{
	CheckoutCartItem itemPanel;
	JButton add;
	InventoryItem current;
	public CheckoutCartItem(InventoryItem item) {
		itemPanel = this;    
		current = item;
		itemPanel.setLayout(new GridLayout(1,3));
	    itemPanel.add(new JLabel(item.getId()));
	    itemPanel.add(new JLabel(item.getDiscription()));
	    add = new JButton("Remove from Cart");
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
	        inventory.add(current);
	        cart.remove(current);
	        checkoutTab.refreshCurrentInv();
	        checkoutTab.refreshCartPanel();
		}
	}
}

