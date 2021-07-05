package za.co.sfy.view;

import javax.swing.JPanel;

public class HomePanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = 1L;

	public HomePanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterHomePanel(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
