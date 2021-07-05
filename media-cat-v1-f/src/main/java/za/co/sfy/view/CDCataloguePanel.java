package za.co.sfy.view;

import javax.swing.JPanel;

public class CDCataloguePanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public CDCataloguePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterCDCatalogue(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
