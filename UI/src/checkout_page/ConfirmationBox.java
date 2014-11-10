package checkout_page;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;


public class ConfirmationBox extends JFrame{
	
	ConfirmationBox cframe;
	
	public ConfirmationBox() {
		cframe = this;
		JPanel itemSummary = new JPanel();
		JButton checkout = new JButton("Checkout");
		checkout.setPreferredSize(new Dimension(35,60));
		
		cframe.setLayout(new BorderLayout());
		cframe.add(itemSummary);
		cframe.add(checkout);
		
		cframe.setPreferredSize(new Dimension(640,480));
		
	}
	
}
