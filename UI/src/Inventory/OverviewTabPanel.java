package Inventory;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OverviewTabPanel extends JPanel {

OverviewTabPanel panel;
	
	public OverviewTabPanel(){
		
		OverviewMissingItemsList missingItemListPanel = new OverviewMissingItemsList();
		OverviewOverdueItemsList overdueItemListPanel = new OverviewOverdueItemsList();
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		myPanel.add(missingItemListPanel);
		myPanel.add(overdueItemListPanel);
		panel = this;
		OverviewGoingOutList goingOutListPanel = new OverviewGoingOutList();
		OverviewComingInList comingInListPanel = new OverviewComingInList();

	    panel.setLayout(new GridLayout(1,3));

	    panel.add(goingOutListPanel);
		panel.add(comingInListPanel);
		panel.add(myPanel);

	    panel.setVisible(true);
		
		
	}
	
	
}
