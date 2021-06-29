package za.co.sfy.view;

import javax.swing.JPanel;

public class DeletePanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public DeletePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterDeleteHome(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
