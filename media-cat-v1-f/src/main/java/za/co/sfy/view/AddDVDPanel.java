package za.co.sfy.view;

import javax.swing.JPanel;

public class AddDVDPanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public AddDVDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAddDVD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}