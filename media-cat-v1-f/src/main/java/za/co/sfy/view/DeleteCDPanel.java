package za.co.sfy.view;

import javax.swing.JPanel;

public class DeleteCDPanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public DeleteCDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterDeleteCD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}