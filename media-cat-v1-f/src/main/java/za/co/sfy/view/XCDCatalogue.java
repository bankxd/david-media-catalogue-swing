package za.co.sfy.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import za.co.sfy.model.CDVO;
import za.co.sfy.model.MediaTypeVO;
import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class XCDCatalogue extends JPanel {

	ClientServiceInterface cs;

	public XCDCatalogue() {
		cs = new ClientService();
		initComponents();
	}

	public void initComponents() {
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel topLabel = new JLabel("CD List");
		JSeparator js1 = new JSeparator();
		js1.setPreferredSize(new Dimension(400, 5));
		JSeparator js2 = new JSeparator();
		js2.setPreferredSize(new Dimension(400, 5));

		List<MediaTypeVO> receiveAllOfMediaType = cs.receiveAllOfMediaType(new CDVO());
		String[][] data = new String[receiveAllOfMediaType.size()][5];
		for (int i = 0; i < receiveAllOfMediaType.size(); i++) {
			CDVO mtcd = (CDVO) receiveAllOfMediaType.get(i);
			String[] dataArr = new String[5];
			dataArr[0] = mtcd.getTitle();
			dataArr[1] = mtcd.getGenre();
			dataArr[2] = String.valueOf(mtcd.getLength());
			dataArr[3] = String.valueOf(mtcd.getTracks());
			dataArr[4] = mtcd.getArtists().toString().replaceAll("\\[|\\]", "");
			for (int j = 0; j < 5; j++) {
				data[i][j] = dataArr[j];
			}
		}

		String[] columnNames = { "Title", "Genre", "Duration", "Tracks", "Artists" };

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
