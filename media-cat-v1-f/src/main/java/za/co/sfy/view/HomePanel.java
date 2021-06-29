package za.co.sfy.view;

import javax.swing.JPanel;

public class HomePanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public HomePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterHomePanel(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
