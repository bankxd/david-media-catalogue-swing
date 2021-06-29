package za.co.sfy.view;

import javax.swing.JPanel;

public class AboutPanel extends AbstractTemplate {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public AboutPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAboutPanel());
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
