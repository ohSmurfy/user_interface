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

public class OverviewMissingItem extends JPanel {
  OverviewMissingItem panel;
  ExpandedMissingItemView expanded;
  JLabel itemDescription;
  JLabel itemIdLabel;
  InventoryItem current;
  Color defaultColor;

  public OverviewMissingItem(InventoryItem item) {
	panel = this;
	current = item;
	defaultColor = panel.getBackground();

	panel.setLayout(new GridLayout(1, 2));
	
	panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	panel.setMaximumSize(new Dimension(2000,25));
	
	panel.add(new JLabel(current.getId()));
	panel.add(new JLabel(current.getDescription()));
	panel.addMouseListener(new Mouse());
	panel.setVisible(true);
  }
  private class Mouse implements MouseListener {

    @Override
	public void mouseClicked(MouseEvent arg0) {

    }

	@Override
	public void mouseEntered(MouseEvent arg0) {
	  panel.setBackground(new Color(165,101,186));
	  expanded = new ExpandedMissingItemView(current);
	  expanded.setLocation((int) Component.RIGHT_ALIGNMENT, (int) Component.TOP_ALIGNMENT);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	  panel.setBackground(defaultColor);
	  expanded.dispose();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	}
}
