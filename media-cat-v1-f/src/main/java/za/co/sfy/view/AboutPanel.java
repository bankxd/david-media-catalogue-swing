package za.co.sfy.view;

import javax.swing.JPanel;

public class AboutPanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public AboutPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAboutPanel());
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
