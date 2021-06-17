package za.co.sfy.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import za.co.sfy.model.DVDVO;
import za.co.sfy.model.MediaTypeVO;
import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

@SuppressWarnings("serial")
public class XDVDCatalogue extends JPanel {

	ClientServiceInterface cs;

	public XDVDCatalogue() {
		cs = new ClientService();
		initComponents();
	}

	public void initComponents() {
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel topLabel = new JLabel("DVD List");
		JSeparator js1 = new JSeparator();
		js1.setPreferredSize(new Dimension(400, 5));
		JSeparator js2 = new JSeparator();
		js2.setPreferredSize(new Dimension(400, 5));

		List<MediaTypeVO> receiveAllOfMediaType = cs.receiveAllOfMediaType(new DVDVO());
		String[][] data = new String[receiveAllOfMediaType.size()][5];
		for (int i = 0; i < receiveAllOfMediaType.size(); i++) {
			DVDVO mtdvd = (DVDVO) receiveAllOfMediaType.get(i);
			String[] dataArr = new String[5];
			dataArr[0] = mtdvd.getTitle();
			dataArr[1] = mtdvd.getGenre();
			dataArr[2] = String.valueOf(mtdvd.getLength());
			dataArr[3] = mtdvd.getLeadActor();
			dataArr[4] = mtdvd.getLeadActress();
			for (int j = 0; j < 5; j++) {
				data[i][j] = dataArr[j];
			}
		}

		String[] columnNames = { "Title", "Genre", "Duration", "Lead Actor", "Lead Actress" };

		JTable table = new JTable(data, columnNames);

		table.setDefaultEditor(Object.class, null);
		
		JScrollPane sp = new JScrollPane(table);
		
		
		JPanel t = new JPanel();
		t.setPreferredSize(new Dimension(480, 290));
		t.add(sp);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(topLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(t, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js2, gbc);

	}

}
