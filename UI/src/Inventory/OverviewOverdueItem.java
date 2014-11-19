package Inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class OverviewOverdueItem extends JPanel {
  OverviewOverdueItem panel;
  CheckoutItem current;
  Color defaultColor;

  ExpandedOverdueView j;
  public OverviewOverdueItem(CheckoutItem item) {
	panel = this;
	defaultColor = panel.getBackground();
	current = item;
	panel.setLayout(new GridLayout(1, 2));
	new JLabel(current.getItemDiscription());
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	panel.add(new JLabel(current.getStudentEmail()));
	panel.add(new JLabel(current.getItemDiscription()));
	
	panel.setVisible(true);
	panel.addMouseListener(new Mouse());
  }
  private class Mouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System mainWindow = ((System) panel.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
	    JTabbedPane tabedPane = ((JTabbedPane) panel.getParent().getParent().getParent().getParent());
	    mainWindow.checkinTab().overviewPanelClicked(current.studentId);
	    tabedPane.setSelectedIndex(2);
	    j.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	    panel.setBackground(new Color(75,17,111));
	    j = new ExpandedOverdueView(current);
	    j.setLocation((int) Component.RIGHT_ALIGNMENT, (int) Component.TOP_ALIGNMENT);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	    panel.setBackground(defaultColor);
	    j.dispose();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
  }
}
