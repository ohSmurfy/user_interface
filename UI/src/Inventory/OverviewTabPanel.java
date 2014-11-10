package Inventory;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OverviewTabPanel extends JPanel {

OverviewTabPanel panel;
	
	public OverviewTabPanel(){
		
		MissingItemsList missingItemListPanel = new MissingItemsList();
		OverdueItemsList overdueItemListPanel = new OverdueItemsList();
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		myPanel.add(missingItemListPanel);
		myPanel.add(overdueItemListPanel);
		panel = this;
		GoingOutList goingOutListPanel = new GoingOutList();
		ComingInList comingInListPanel = new ComingInList();

	    panel.setLayout(new GridLayout(1,3));

	    panel.add(goingOutListPanel);
		panel.add(comingInListPanel);
		panel.add(myPanel);

	    panel.setVisible(true);
		
		
	}
	
	
}
