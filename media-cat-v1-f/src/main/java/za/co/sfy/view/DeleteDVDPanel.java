package za.co.sfy.view;

import javax.swing.JPanel;

public class DeleteDVDPanel extends AbstractBackGroundPanel {
	
	private static final long serialVersionUID = -6941754009840686963L;
	
	public DeleteDVDPanel(ViewFrame viewFrame) {
		super(viewFrame);
		initComponents(new CenterDeleteDVD(viewFrame));
	}
	
	@Override
	public void initComponents(JPanel centralPanel) {
		super.initComponents(centralPanel);
	}
}
