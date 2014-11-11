package Inventory;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExpandedMissingItemView extends JFrame{
	  ExpandedMissingItemView frame;
	  JPanel itemPane;
	  SQLItemCheckinRepo historyTable;
	  CheckoutItem maybeItem;
  public ExpandedMissingItemView(InventoryItem item) {
	  frame = this;

	  JPanel panel = new JPanel();
	  panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	  JPanel itemId = new JPanel();
	  itemId.setLayout(new GridLayout(1,2));
	  itemId.add(new JLabel("item Id :"));
	  itemId.add(new JLabel(item.getId()));
	  itemId.setMaximumSize(new Dimension(2000,25));
	    
	  JPanel itemDiscription = new JPanel();
	  itemDiscription.setLayout(new GridLayout(1,2));
	  itemDiscription.add(new JLabel("Discription :"));
	  itemDiscription.add(new JLabel(item.getDiscription()));
	  itemDiscription.setMaximumSize(new Dimension(2000,25));
      panel.add(itemId);
	  panel.add(itemDiscription);

	  try{ 
		SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();
        for (CheckoutItem checkedout : checkoutTable.getByItemId(item.getId())) {
    		JPanel currentID = new JPanel();
    		currentID.setLayout(new GridLayout(1,2));
    		currentID.add(new JLabel("Student Id :"));
    		currentID.add(new JLabel(checkedout.getStudentId()));
    		currentID.setMaximumSize(new Dimension(2000,25));
    		
    		JPanel checkedOutBy = new JPanel();
    		checkedOutBy.setLayout(new GridLayout(1,2));
    		checkedOutBy.add(new JLabel("Checked out By :"));
    		checkedOutBy.add(new JLabel(checkedout.getEmployeeId()));
    		checkedOutBy.setMaximumSize(new Dimension(2000,25));
    		    
    		JPanel currentlyHas = new JPanel();
    		currentlyHas.setLayout(new GridLayout(1,2));
    		currentlyHas.add(new JLabel("Currently Has :"));
    		currentlyHas.add(new JLabel(checkedout.getStudentEmail()));
    		currentlyHas.setMaximumSize(new Dimension(2000,25));
    	    panel.add(checkedOutBy);
    		panel.add(currentlyHas);
        }
	  } catch(SQLException e) { 
          JOptionPane.showMessageDialog(panel, "SQL Error" + e);

	  }
	  
	  try{ 
		historyTable = new SQLItemCheckinRepo();
		maybeItem = historyTable.getNewestByItemId(item.getId());
		JPanel checkedInBy = new JPanel();
		checkedInBy.setLayout(new GridLayout(1,2));
		checkedInBy.add(new JLabel("Checked In By :"));
		checkedInBy.add(new JLabel(maybeItem.getEmployeeId()));
		checkedInBy.setMaximumSize(new Dimension(2000,25));
		    
		JPanel lastCheckedOut = new JPanel();
		lastCheckedOut.setLayout(new GridLayout(1,2));
		lastCheckedOut.add(new JLabel("Checked Out Last :"));
		lastCheckedOut.add(new JLabel(maybeItem.getStudentEmail()));
		lastCheckedOut.setMaximumSize(new Dimension(2000,25));
	    panel.add(checkedInBy);
		panel.add(lastCheckedOut);
		historyTable.close();
	  } catch(SQLException e) { 
		JPanel neverCheckedOut = new JPanel();
		neverCheckedOut.setLayout(new GridLayout(1,2));
		neverCheckedOut.add(new JLabel("Item has never been checked in"));
		neverCheckedOut.setMaximumSize(new Dimension(2000,25));
		panel.add(neverCheckedOut);
		try {historyTable.close();} catch(SQLException e2) {}
	  }

	  panel.setVisible(true);
	  frame.add(panel);
	  frame.setVisible(true);
	  frame.pack();
  }
}
