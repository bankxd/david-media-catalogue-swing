package za.co.sfy.view;

import javax.swing.JPanel;

public class AddCDPanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public AddCDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAddCD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
