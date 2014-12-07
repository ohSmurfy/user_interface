package Inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OverviewMissingItemsList extends JPanel {
	
  OverviewMissingItemsList panel;
  public OverviewMissingItemsList(){
	panel = this;
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(new Color(162,181,205));
	refresh();
  }
  public void refresh(){  
  panel.removeAll();
  JPanel header = new JPanel();
  header.setBackground(new Color(162,181,205));
  JLabel missing = new JLabel("Missing");
  missing.setFont(missing.getFont ().deriveFont (15.0f));
  header.add(missing);
  header.setVisible(true);
  header.setMaximumSize(new Dimension(2000,25));

  JPanel description = new JPanel();
  description.setBackground(new Color(162,181,205));
  description.setLayout(new GridLayout(1,2));
  description.add(new JLabel("Item Id"));
  description.add(new JLabel("Description"));
  description.setVisible(true);
  description.setMaximumSize(new Dimension(2000,25));
	
  panel.add(Box.createVerticalStrut(5));
  panel.setBorder(BorderFactory.createLineBorder(new Color(75,17,111), 2));
  panel.add(header);
  panel.add(description);
  
  
  try {
    SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
    java.util.List<InventoryItem> items = inventory.getByCurrentState("missing");
    for (InventoryItem item : items) {
      panel.add(new OverviewMissingItem(item));
      panel.add(Box.createVerticalStrut(5));
    }
  } catch (SQLException ex) {
    JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
  } catch (ItemException ex){
    JOptionPane.showMessageDialog(panel,ex);
  }
  panel.revalidate();
  panel.repaint();
  }
}
