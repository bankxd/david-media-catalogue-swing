package za.co.sfy.view;

import javax.swing.JPanel;

public class CDCataloguePanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public CDCataloguePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterCDCatalogue());
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
