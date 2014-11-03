package Inventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class NewItem extends JPanel{
  public NewItem() {
    super(new FlowLayout());
    final NewItem item = this;
    final JFrame frame = new JFrame("New Item");
    JPanel panel = new JPanel(false);
    JLabel idLabel = new JLabel("Item ID");
    final JTextField id = new JTextField(10);
    JLabel discriptionLabel = new JLabel ("Discription");
    final JTextField discription = new JTextField(10);
    JLabel reminderLabel = new JLabel("Reminder");
    final JTextField reminder = new JTextField(20);
    
    JButton add = new JButton("Add Item");
    add.addActionListener(new ActionListener()    {
      public void actionPerformed(ActionEvent e)
      {
        try {

          SQLInventoryItemRepo inventory = new SQLInventoryItemRepo();
          inventory.insertNewItem(new InventoryItem(id.getText(), discription.getText(),"in",reminder.getText()));
          frame.dispose();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(frame, "SQL ERROR!" + ex);
        } catch (ItemException ex){
          JOptionPane.showMessageDialog(frame,ex);
        }
      }
    });
    panel.add(idLabel);
    panel.add(id);
    panel.add(discriptionLabel);
    panel.add(discription);
    panel.add(reminderLabel);
    panel.add(reminder);
    panel.add(add);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}