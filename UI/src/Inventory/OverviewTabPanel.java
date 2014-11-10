package Inventory;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OverviewTabPanel extends JPanel {

  OverviewTabPanel panel;
  OverviewGoingOutList goingOutListPanel; 
  OverviewComingInList comingInListPanel;
  OverviewMissingItemsList missingItemListPanel;
  OverviewOverdueItemsList overdueItemListPanel;
  public OverviewTabPanel(){
	panel = this;
    panel.setLayout(new GridLayout(1,3));

    goingOutListPanel = new OverviewGoingOutList();
	comingInListPanel = new OverviewComingInList();
	missingItemListPanel = new OverviewMissingItemsList();
	overdueItemListPanel = new OverviewOverdueItemsList();
		
	JPanel thirdRow = new JPanel();
	thirdRow.setLayout(new GridLayout(2,1));
	thirdRow.add(missingItemListPanel);
	thirdRow.add(overdueItemListPanel);
		
    panel.add(goingOutListPanel);
	panel.add(comingInListPanel);
	panel.add(thirdRow);
    panel.setVisible(true);
  }	
  
  public void refresh(){
    goingOutListPanel.refresh();
    comingInListPanel.refresh();
    missingItemListPanel.refresh();
    overdueItemListPanel.refresh();
  }
}
