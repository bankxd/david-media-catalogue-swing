package za.co.sfy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import za.co.sfy.model.DVDVO;
import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class CenterAddDVD extends JPanel {

	private static final long serialVersionUID = -1693469099073081918L;

	private ViewFrame viewFrame;
	ClientServiceInterface cs;

	public CenterAddDVD(ViewFrame v) {
		this.viewFrame = v;
		cs = new ClientService();
		initComponents();
	}

	public void initComponents() {
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel topLabel = new JLabel("Catalogue");
		JSeparator js1 = new JSeparator();
		js1.setPreferredSize(new Dimension(400, 5));
		JSeparator js2 = new JSeparator();
		js2.setPreferredSize(new Dimension(400, 5));

		JRadioButton dvdRadio = new JRadioButton("DVD");
		dvdRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame.putPanel(new AddDVDPanel(viewFrame));
			}
		});
		JRadioButton cdRadio = new JRadioButton("CD");
		cdRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame.putPanel(new AddCDPanel(viewFrame));
			}
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(cdRadio);
		bg.add(dvdRadio);

		JPanel gridBox = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(2, 1));
		grid.setBackground(Color.white);
		grid.setPreferredSize(new Dimension(50, 50));
		JPanel top = new JPanel();
		JLabel jl = new JLabel("Add Item");
		top.setBackground(Color.gray.brighter());
		top.add(jl);
		gridBox.add(top, BorderLayout.NORTH);
		grid.add(cdRadio);
		grid.add(dvdRadio);
		gridBox.add(grid, BorderLayout.CENTER);

		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addGrid = new JPanel(new GridLayout(5, 2));
		addGrid.setBorder(new LineBorder(Color.white, 10));
		JTextField titleField = new JTextField();
		JTextField genreField = new JTextField();
		JTextField durationField = new JTextField();
		JTextField actorField = new JTextField();
		JTextField actressField = new JTextField();
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
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel("Add Item");
		addTop.setBackground(Color.gray.brighter());
		addGrid.setBackground(Color.white);
		addBox.setPreferredSize(new Dimension(300, 30));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		addBox.add(addGrid, BorderLayout.CENTER);

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
				boolean result = cs.createMediaType(dvd);
				viewFrame.putPanel(new ResultPanel(viewFrame, result == true? "Successfully Created DVD" : "Unsuccessful"));
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

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(topLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(gridBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 0.2;
		this.add(addBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(saveBut, gbc);
	}
}
