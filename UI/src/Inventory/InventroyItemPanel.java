package Inventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class InventroyItemPanel extends JPanel{
  JLabel itemDiscription;
  JLabel itemId;
  JComboBox combo;
  String[] options = {"in","out","overdue","missing","delete"}; 
  public InventroyItemPanel(InventoryItem item) {
    final InventroyItemPanel panel = this;
    panel.setLayout(new FlowLayout());
    itemDiscription = new JLabel(item.getDiscription());
    itemId = new JLabel(item.getId());
    combo = new JComboBox(options);
    combo.setSelectedItem(item.getState());
    combo.addActionListener(new ActionListener()    {
      public void actionPerformed(ActionEvent e)
      {
        try {
          SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
          inventory.updateState(itemId.getText(), combo.getSelectedItem().toString());
          if (combo.getSelectedItem().toString() == "delete") {
            JOptionPane.showMessageDialog(panel,"Deleted!");
            Container parent = panel.getParent();
            parent.remove(panel);
            parent.repaint();
          } else JOptionPane.showMessageDialog(panel,"Updated!");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(panel, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(panel,ex);
        }
      }
    });
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    panel.setPreferredSize(new Dimension(300,50));
    panel.add(itemId);
    panel.add(itemDiscription);
    panel.add(combo);
    panel.setVisible(true);
  }
}
