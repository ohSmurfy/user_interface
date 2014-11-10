package checkout_page;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class ConfirmationBox extends JFrame{
	
	ConfirmationBox cframe;
	
	public ConfirmationBox() {
		cframe = this;
		JPanel itemSummary = new JPanel();
		itemSummary.setPreferredSize(new Dimension(200,400));
		JLabel summary = new JLabel("Summary");
		
		JButton checkout = new JButton("Checkout");
		checkout.setPreferredSize(new Dimension(35,60));
		
		cframe.setLayout(new BorderLayout());
		cframe.add(itemSummary,BorderLayout.CENTER);
		cframe.add(summary,BorderLayout.NORTH);
		cframe.add(checkout,BorderLayout.SOUTH);
		
		cframe.setPreferredSize(new Dimension(640,640/12*9));
		
	}
	
}
