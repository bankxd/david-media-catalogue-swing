package za.co.sfy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import za.co.sfy.model.DVDVO;
import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class CenterAddDVD extends AbstractCenterPanel {

	private static final long serialVersionUID = 1L;
	private ClientServiceInterface clientService;
	private JTextField titleField;
	private JTextField genreField;
	private JTextField durationField;
	private JTextField actorField;
	private JTextField actressField;

	public CenterAddDVD(ViewFrame viewFrame) {
		clientService = new ClientService();
		titleField = new JTextField();
		genreField = new JTextField();
		durationField = new JTextField();
		actorField = new JTextField();
		actressField = new JTextField();
		initComponents(viewFrame);
	}

	public void initComponents(ViewFrame viewFrame) {
		GridBagConstraints gridBagConstraints = initGridBag();
		JPanel addBox = setAddBox("Add Item");
		setAddGrid(addBox);
		setConstraints(0, 0, GridBagConstraints.FIRST_LINE_START, setTitleJLabel("Catalogue"), gridBagConstraints);
		setConstraints(0, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setTopSeparator(new Dimension(400, 5)) , gridBagConstraints);
		setConstraints(0, 2, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setAddRadioGroup(viewFrame), gridBagConstraints);
		setConstraints(0, 3, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 0.2, addBox, gridBagConstraints);
		setConstraints(0, 4, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 0.0, setLowerSeparator(new Dimension(400, 5)), gridBagConstraints);
		setConstraints(0, 5, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, setSaveButton(clientService, viewFrame), gridBagConstraints);
	}

	public JPanel setAddGrid(JPanel addBox) {
		JPanel addGrid = new JPanel(new GridLayout(5, 2));
		addGrid.setBorder(new LineBorder(Color.white, 10));
		addGrid.add(new JLabel("Title: "));
		addGrid.add(titleField);
		addGrid.add(new JLabel("Genre: "));
		addGrid.add(genreField);
		addGrid.add(new JLabel("Duration: "));
		addGrid.add(durationField);
		addGrid.add(new JLabel("Lead Actor: "));
		addGrid.add(actorField);
		addGrid.add(new JLabel("Lead Actress: "));
		addGrid.add(actressField);
		addGrid.setPreferredSize(new Dimension(40, 40));
		addGrid.setBackground(Color.white);
		addBox.add(addGrid, BorderLayout.CENTER);
		return addGrid;
	}

	public JButton setSaveButton(ClientServiceInterface clientService, ViewFrame viewFrame) {
		JButton saveBut = new JButton("Save");
		saveBut.setPreferredSize(new Dimension(100, 25));
		saveBut.setForeground(Color.white);
		saveBut.setBackground(Color.green.darker());
		saveBut.setBorder(new LineBorder(Color.green.brighter()));
		saveBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DVDVO dvd = new DVDVO();
				dvd.setType(dvd);
				dvd.setTitle(titleField.getText());
				dvd.setLength(Integer.parseInt(durationField.getText()));
				dvd.setGenre(genreField.getText());
				dvd.setLeadActor(actorField.getText());
				dvd.setLeadActress(actressField.getText());
				boolean result = clientService.createMediaType(dvd);
				viewFrame.putPanel(
						new ResultPanel(viewFrame, result == true ? "Successfully Created DVD" : "Unsuccessful"));
			}
		});
		saveBut.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				saveBut.setBackground(Color.gray.brighter());
			}

			public void mouseExited(MouseEvent evt) {
				saveBut.setBackground(Color.green.darker());
			}
		});
		return saveBut;
	}
}
