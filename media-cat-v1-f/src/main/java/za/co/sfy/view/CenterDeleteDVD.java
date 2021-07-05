package za.co.sfy.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class CenterDeleteDVD extends AbstractCenterPanel {
	
	private static final long serialVersionUID = 1L;
	ClientServiceInterface clientService;

	public CenterDeleteDVD(ViewFrame viewFrame) {
        clientService = new ClientService();
		initComponents(viewFrame);
	}

	public void initComponents(ViewFrame viewFrame) {
		GridBagConstraints gridBagConstraints = initGridBag();
		setConstraints(0, 0, GridBagConstraints.FIRST_LINE_START, setTitleJLabel("Catalogue"), gridBagConstraints);
		setConstraints(0, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setTopSeparator(new Dimension(400, 5)), gridBagConstraints);
		setConstraints(0, 2, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setDeleteRadioGroup(viewFrame), gridBagConstraints);
		setConstraints(0, 3, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 0.2, setTableBox(setDVDTable(clientService, viewFrame)), gridBagConstraints);
		setConstraints(0, 4, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 0.0, setLowerSeparator(new Dimension(400, 5)), gridBagConstraints);
		setConstraints(0, 5, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setDeleteButton(viewFrame), gridBagConstraints);
	}
}
