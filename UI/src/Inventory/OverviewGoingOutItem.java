package Inventory;
import java.awt.event.MouseListener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class OverviewGoingOutItem extends JPanel {
  
	OverviewGoingOutItem panel;
	CheckoutItem current;
	Reservation res;
  
	public OverviewGoingOutItem(Reservation aRes) {
		panel = this;
		res = aRes;
		panel.setLayout(new GridLayout(1, 2));
	
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setMaximumSize(new Dimension(2000,50));
	
		panel.add(new JLabel(res.getStudentEmail()));
		panel.add(new JLabel(res.getGoingOutTime()));
		panel.setVisible(true);
	}
	
	private class MouseHovering implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}


