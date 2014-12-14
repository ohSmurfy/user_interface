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
	  
	  Dimension maxSize = new Dimension(2000,25);

	  JPanel panel = new JPanel();
	  panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	  JPanel itemId = new JPanel();
	  itemId.setLayout(new GridLayout(1,2));
	  itemId.add(new JLabel("item Id :"));
	  itemId.add(new JLabel(item.getId()));
	  itemId.setMaximumSize(maxSize);
	    
	  JPanel itemDescription = new JPanel();
	  itemDescription.setLayout(new GridLayout(1,2));
	  itemDescription.add(new JLabel("Description :"));
	  itemDescription.add(new JLabel(item.getDescription()));
	  itemDescription.setMaximumSize(maxSize);
      panel.add(itemId);
	  panel.add(itemDescription);

	  try{ 
		SQLCheckoutItemRepo checkoutTable = new SQLCheckoutItemRepo();
        for (CheckoutItem checkedout : checkoutTable.getByItemId(item.getId())) {
    		JPanel currentID = new JPanel();
    		currentID.setLayout(new GridLayout(1,2));
    		currentID.add(new JLabel("Student Id :"));
    		currentID.add(new JLabel(checkedout.getStudentId()));
    		currentID.setMaximumSize(maxSize);
    		
    		JPanel checkedOutBy = new JPanel();
    		checkedOutBy.setLayout(new GridLayout(1,2));
    		checkedOutBy.add(new JLabel("Checked out By :"));
    		checkedOutBy.add(new JLabel(checkedout.getEmployeeId()));
    		checkedOutBy.setMaximumSize(maxSize);
    		    
    		JPanel currentlyHas = new JPanel();
    		currentlyHas.setLayout(new GridLayout(1,2));
    		currentlyHas.add(new JLabel("Currently Has :"));
    		currentlyHas.add(new JLabel(checkedout.getStudentEmail()));
    		currentlyHas.setMaximumSize(maxSize);
    	    panel.add(currentID);
    		panel.add(currentlyHas);
    	    panel.add(checkedOutBy);
        }
        checkoutTable.close();
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
		checkedInBy.setMaximumSize(maxSize);
		    
		JPanel lastCheckedOut = new JPanel();
		lastCheckedOut.setLayout(new GridLayout(1,2));
		lastCheckedOut.add(new JLabel("Checked Out Last :"));
		lastCheckedOut.add(new JLabel(maybeItem.getStudentEmail()));
		lastCheckedOut.setMaximumSize(maxSize);
	    panel.add(checkedInBy);
		panel.add(lastCheckedOut);
		historyTable.close();
	  } catch(SQLException e) { 
		JPanel neverCheckedOut = new JPanel();
		neverCheckedOut.setLayout(new GridLayout(1,2));
		neverCheckedOut.add(new JLabel("Item has never been checked in"));
		neverCheckedOut.setMaximumSize(maxSize);
		panel.add(neverCheckedOut);
		try {historyTable.close();} catch(SQLException e2) {}
	  }

	  panel.setVisible(true);
	  frame.add(panel);
	  frame.setVisible(true);
	  frame.pack();
  }
}
