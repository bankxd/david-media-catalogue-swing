package za.co.sfy.view;

import javax.swing.JPanel;

public class AddDVDPanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public AddDVDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterAddDVD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}