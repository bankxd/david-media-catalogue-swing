package za.co.sfy.view;

import javax.swing.JPanel;

public class DVDCataloguePanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public DVDCataloguePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterDVDCatalogue(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}

