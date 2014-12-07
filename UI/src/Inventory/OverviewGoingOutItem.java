package Inventory;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Component;
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
	ExpandedGoingOut goingOutPopup;
	Dimension maxSize = new Dimension(2000,25);
	Color defaultColor;
	Color borderColor = new Color(75,15,111);
	GridLayout panelLayout = new GridLayout(1,2);
  
	public OverviewGoingOutItem(Reservation aRes) {
		panel = this;
		res = aRes;
		panel.setLayout(panelLayout);
		defaultColor = panel.getBackground();
	
		panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		panel.setMaximumSize(maxSize);
	
		panel.add(new JLabel(res.getStudentEmail()));
		panel.add(new JLabel(res.getGoingOutTime()));
		panel.setVisible(true);
		panel.addMouseListener(new MouseHovering());
	}
	
	private class MouseHovering implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			System mainWindow = ((System) panel.getParent().getParent().getParent().getParent().getParent().getParent());
		    JTabbedPane tabedPane = ((JTabbedPane) panel.getParent().getParent().getParent());
		    mainWindow.checkoutTab().goingOutClicked(res);
		    tabedPane.setSelectedIndex(2);
		    goingOutPopup.dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			 panel.setBackground(borderColor);
			 goingOutPopup = new ExpandedGoingOut(res);
			 goingOutPopup.setLocation((int) Component.RIGHT_ALIGNMENT, (int) Component.TOP_ALIGNMENT);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(defaultColor);
		    goingOutPopup.dispose();
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


