package checkout_page;

import javax.swing.*;
import java.awt.*;

public class Displayer {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Basic Frame");
		CheckoutPanel coutPanel = new CheckoutPanel();
		Dimension frameSize = new Dimension(275,260);
		
		
		frame.setPreferredSize(frameSize);
		frame.setMaximumSize(frameSize);
		frame.setMinimumSize(frameSize);
		
		frame.setLocationRelativeTo(null);
		frame.add(coutPanel);
		frame.setVisible(true);
		
		
	}
	
}
