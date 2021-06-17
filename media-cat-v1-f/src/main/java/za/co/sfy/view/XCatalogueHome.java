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
import javax.swing.border.LineBorder;

import za.co.sfy.model.DVDVO;
import za.co.sfy.services.ClientServiceInterface;

public class XCatalogueHome extends JPanel {

	private ViewFrame v;
	ClientServiceInterface cs;
	String messageReturned;

	public XCatalogueHome(ViewFrame v, String message) {
		this.v = v;
		this.messageReturned = message;
//        cs = new ClientService();
		initComponents();
	}
	
	public XCatalogueHome(ViewFrame v) { 
		this.v = v;
		initComponents();
	}

	// *******************************************************************

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
	        	v.putPanel(new AddDVDPanel(v));
	        }
	    });
		JRadioButton cdRadio = new JRadioButton("CD");
		cdRadio.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	v.putPanel(new AddCDPanel(v));
	        }
	    });
		ButtonGroup bg = new ButtonGroup();
		bg.add(cdRadio);
		bg.add(dvdRadio);

		// *******************************************************************

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

		// *******************************************************************

		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addGrid = new JPanel(new BorderLayout());
		addGrid.setPreferredSize(new Dimension(50, 0));
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel("Add Item");
		addTop.setBackground(Color.gray.brighter());
		addGrid.setBackground(Color.white);
		addBox.setBackground(Color.white);
		addBox.setPreferredSize(new Dimension(300, 30));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		addBox.add(addGrid, BorderLayout.CENTER);
		if(getMessageReturned() != null) {
			JLabel res = new JLabel(getMessageReturned());
			addBox.add(res);
			res.setHorizontalAlignment(JLabel.CENTER);
		}

		JButton deleteBut = new JButton("Delete Item");
		deleteBut.setPreferredSize(new Dimension(100, 25));
		deleteBut.setForeground(Color.white);
		deleteBut.setBackground(Color.green.darker());
		deleteBut.setBorder(new LineBorder(Color.green.brighter()));
		deleteBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				v.putPanel(new DeletePanel(v));
			}
		});
		deleteBut.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	deleteBut.setBackground(Color.gray.brighter());
		    }

		    public void mouseExited(MouseEvent evt) {
		    	deleteBut.setBackground(Color.green.darker());
		    }
		});

		// *******************************************************************

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
		this.add(deleteBut, gbc);

	}
	// *******************************************************************

	public String getMessageReturned() {
		return messageReturned;
	}

	public void setMessageReturned(String messageReturned) {
		this.messageReturned = messageReturned;
	}
	
	
}
