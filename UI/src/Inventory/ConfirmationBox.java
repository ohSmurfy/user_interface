package Inventory;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
public class ConfirmationBox extends JFrame{
	
	ConfirmationBox cframe;
	
	public ConfirmationBox() {
		cframe = this;
		cframe.setTitle("Confirmation");
		cframe.setLayout(new GridBagLayout());
		cframe.setPreferredSize(new Dimension(800,600));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel itemSummaryPanel = new JPanel();
		JPanel itemSummary = new JPanel();
		itemSummary.setPreferredSize(new Dimension(400,600));
		itemSummary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel summary = new JLabel("Summary");
		itemSummaryPanel.add(itemSummary);
		itemSummaryPanel.add(summary);
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		cframe.add(itemSummaryPanel,gbc);
		

		
		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(200,600));
		infoPanel.setLayout(new GridLayout(4,2));
		JLabel studentId = new JLabel("Student ID");
		JTextField sIdField = new JTextField();
		JLabel employeeId = new JLabel("Employee ID");
		JTextField eIdField = new JTextField();
		JLabel dueDate = new JLabel("Due Date: ");
		JTextField dueDateField = new JTextField();
		infoPanel.add(studentId);
		infoPanel.add(sIdField);
		infoPanel.add(employeeId);
		infoPanel.add(eIdField);
		infoPanel.add(dueDate);
		infoPanel.add(dueDateField);
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		cframe.add(infoPanel,gbc);
		
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setPreferredSize(new Dimension(200,600));
		JButton checkout = new JButton("Checkout");
		checkout.addActionListener(new CheckoutActionListener());
		checkout.setPreferredSize(new Dimension(35,60));
		checkoutPanel.add(checkout);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		cframe.add(checkoutPanel,gbc);
		
		
		
		
	}
	
	private class CheckoutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//work with database	
			cframe.dispose();
		}
	}
	
}