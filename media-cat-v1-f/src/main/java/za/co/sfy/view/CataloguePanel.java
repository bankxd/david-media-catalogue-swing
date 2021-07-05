package za.co.sfy.view;

import javax.swing.JPanel;

public class CataloguePanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public CataloguePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterCatalogueHome(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}