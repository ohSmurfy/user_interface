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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;

public class OverviewComingInItem extends JPanel {
  OverviewComingInItem panel;
  JLabel due;
  CheckoutItem current;
  Color defaultColor;
  ExpandedComingInView j;
  List<CheckoutItem> allItems;
  
  public OverviewComingInItem(List<CheckoutItem> items) {
	panel = this;
	defaultColor = panel.getBackground();
	current = items.get(0);
	allItems = items;
	panel.setLayout(new GridLayout(1, 2));
	
    DateFormat format = new SimpleDateFormat( "h:mm a" );
	due = new JLabel(format.format(current.dueDate));
	
	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	panel.setMaximumSize(new Dimension(2000,50));
	panel.add(new JLabel(current.getStudentEmail()));
	panel.add(due);
	panel.addMouseListener(new Mouse());
	panel.setVisible(true);
  }
  
  private class Mouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
	    System mainWindow = ((System) panel.getParent().getParent().getParent().getParent().getParent().getParent());
	    JTabbedPane tabedPane = ((JTabbedPane) panel.getParent().getParent().getParent());
	    mainWindow.checkinTab().overviewPanelClicked(current.studentId);
	    tabedPane.setSelectedIndex(2);
	    j.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	    panel.setBackground(new Color(75,17,111));
	    j = new ExpandedComingInView(allItems);
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
