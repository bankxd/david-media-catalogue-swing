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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import za.co.sfy.model.CDVO;
import za.co.sfy.model.DVDVO;
import za.co.sfy.model.MediaTypeVO;
import za.co.sfy.services.ClientService;
import za.co.sfy.services.ClientServiceInterface;

public class XDeleteDVD extends JPanel {
	
	private ViewFrame v;
	ClientServiceInterface cs;
	int selectedRow;
	String selectedTitle;

	public XDeleteDVD(ViewFrame v) {
		this.v = v;
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
	        	v.putPanel(new DeleteDVDPanel(v));
	        }
	    });
		JRadioButton cdRadio = new JRadioButton("CD");
		cdRadio.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	v.putPanel(new DeleteCDPanel(v));
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
		JLabel jl = new JLabel("Delete Item");
		top.setBackground(Color.gray.brighter());
		top.add(jl);
		gridBox.add(top, BorderLayout.NORTH);
		grid.add(cdRadio);
		grid.add(dvdRadio);
		gridBox.add(grid, BorderLayout.CENTER);
		
		// *******************************************************************
		
		List<MediaTypeVO> receiveAllOfMediaType = cs.receiveAllOfMediaType(new DVDVO());
		String[][] data = new String[receiveAllOfMediaType.size()][5];
		for (int i = 0; i < receiveAllOfMediaType.size(); i++) {
			DVDVO mtdvd = (DVDVO) receiveAllOfMediaType.get(i);
			String[] dataArr = new String[5];
			dataArr[0] = mtdvd.getTitle();
			dataArr[1] = mtdvd.getGenre();
			dataArr[2] = String.valueOf(mtdvd.getLength());
			dataArr[3] = String.valueOf(mtdvd.getLeadActor());
			dataArr[4] = mtdvd.getLeadActress();
			for (int j = 0; j < 5; j++) {
				data[i][j] = dataArr[j];
			}
		}

		String[] columnNames = { "Title", "Genre", "Duration", "Lead Actor", "Lead Actress" };

		JTable table = new JTable(data, columnNames);
		table.setDefaultEditor(Object.class, null);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (table.getSelectedRow() > -1) {
		        	setSelectedRow(table.getSelectedRow());
		        	setSelectedTitle(table.getValueAt(table.getSelectedRow(), 0).toString());
		        	v.putPanel(new SelectionDeleteConfirmationDVD(v, getSelectedTitle()));
		        }
		    }
		});

		JScrollPane sp = new JScrollPane(table);
		
		
		JPanel t = new JPanel();
		t.setPreferredSize(new Dimension(480, 290));
		t.add(sp);

		// *******************************************************************

		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel("Delete Item");
		addTop.setBackground(Color.gray.brighter());
		addBox.setPreferredSize(new Dimension(480, 80));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		addBox.add(t, BorderLayout.CENTER);

		JButton deleteBut = new JButton("Delete Item");
		deleteBut.setPreferredSize(new Dimension(100, 25));
		deleteBut.setForeground(Color.white);
		deleteBut.setBackground(Color.green.darker());
		deleteBut.setBorder(new LineBorder(Color.green.brighter()));
		deleteBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getSelectedTitle() {
		return selectedTitle;
	}

	public void setSelectedTitle(String selectedTitle) {
		this.selectedTitle = selectedTitle;
	}
	
	
	
}