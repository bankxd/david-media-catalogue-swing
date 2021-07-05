package za.co.sfy.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class CenterDVDCatalogue extends AbstractCenterPanel {

	private static final long serialVersionUID = 1L;
	private ClientServiceInterface clientService;

	public CenterDVDCatalogue(ViewFrame viewFrame) {
		initComponents(viewFrame);
	}

	public void initComponents(ViewFrame viewFrame) {
		GridBagConstraints gridBagConstraints = initGridBag();
		clientService = new ClientService();
		setConstraints(0, 0, GridBagConstraints.FIRST_LINE_START, setTitleJLabel("DVD List"), gridBagConstraints);
		setConstraints(0, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, setTopSeparator(new Dimension(400, 5)), gridBagConstraints);
		setConstraints(0, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0.2, setDVDTable(clientService), gridBagConstraints);
		setConstraints(0, 3, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, setLowerSeparator(new Dimension(400, 5)), gridBagConstraints);
	}
}
