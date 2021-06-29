package za.co.sfy.view;

import javax.swing.JPanel;

public class AddCDPanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public AddCDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAddCD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
