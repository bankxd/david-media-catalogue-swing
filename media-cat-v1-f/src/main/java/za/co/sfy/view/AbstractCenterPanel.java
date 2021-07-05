package za.co.sfy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import za.co.sfy.model.CDVO;
import za.co.sfy.model.DVDVO;
import za.co.sfy.model.MediaTypeVO;
import za.co.sfy.services.ClientServiceInterface;

public abstract class AbstractCenterPanel extends JPanel {

	private int selectedCDRow;
	private String selectedCDTitle;
	private int selectedDVDRow;
	private String selectedDVDTitle;

	private static final long serialVersionUID = 1L;

	public GridBagConstraints initGridBag() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		return gridBagConstraint;
	}

	public JLabel setTitleJLabel(String title) {
		JLabel topLabel = new JLabel(title);
		return topLabel;
	}

	public JSeparator setTopSeparator(Dimension dimension) {
		JSeparator topJSeparator = new JSeparator();
		topJSeparator.setPreferredSize(dimension);
		return topJSeparator;
	}

	public JSeparator setLowerSeparator(Dimension dimension) {
		JSeparator lowerJSeparator = new JSeparator();
		lowerJSeparator.setPreferredSize(new Dimension(400, 5));
		return lowerJSeparator;
	}

	public JLabel setImage(String path) {
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\daves\\Pictures\\mediaCatImg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = null;
		if (myPicture != null) {
			picLabel = new JLabel(new ImageIcon(myPicture));
		} else {
			picLabel = new JLabel("SFY");
		}
		return picLabel;
	}
	
	public JPanel setTableBox(JPanel table) {
		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel("Delete Item");
		addTop.setBackground(Color.gray.brighter());
		addBox.setPreferredSize(new Dimension(480, 80));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		addBox.add(table, BorderLayout.CENTER);
		return addBox;
	}

	public JPanel setCDTable(ClientServiceInterface clientService) {
		List<MediaTypeVO> receiveAllOfMediaType = clientService.receiveAllOfMediaType(new CDVO());
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
		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(480, 290));
		tablePanel.add(sp);
		return tablePanel;
	}

	public JPanel setDVDTable(ClientServiceInterface clientService) {
		List<MediaTypeVO> receiveAllOfMediaType = clientService.receiveAllOfMediaType(new DVDVO());
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
		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(480, 290));
		tablePanel.add(sp);
		return tablePanel;
	}
	
	public JPanel setCDTable(ClientServiceInterface clientService, ViewFrame viewFrame) {
		List<MediaTypeVO> receiveAllOfMediaType = clientService.receiveAllOfMediaType(new CDVO());
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					setSelectedCDRow(table.getSelectedRow());
					setSelectedCDTitle(table.getValueAt(table.getSelectedRow(), 0).toString());
					viewFrame.putPanel(new SelectionDeleteConfirmationDVDPanel(viewFrame, getSelectedCDTitle()));
				}
			}
		});
		JScrollPane sp = new JScrollPane(table);
		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(480, 290));
		tablePanel.add(sp);
		return tablePanel;
	}

	public JPanel setDVDTable(ClientServiceInterface clientService, ViewFrame viewFrame) {
		List<MediaTypeVO> receiveAllOfMediaType = clientService.receiveAllOfMediaType(new DVDVO());
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (table.getSelectedRow() > -1) {
		        	setSelectedDVDRow(table.getSelectedRow());
		        	setSelectedDVDTitle(table.getValueAt(table.getSelectedRow(), 0).toString());
		        	viewFrame.putPanel(new SelectionDeleteConfirmationDVDPanel(viewFrame, getSelectedDVDTitle()));
		        }
		    }
		});
		JScrollPane sp = new JScrollPane(table);
		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(480, 290));
		tablePanel.add(sp);
		return tablePanel;
	}

	public JButton setDeleteButton(ViewFrame viewFrame, JPanel panel) {
		JButton deleteBut = new JButton("Delete Item");
		deleteBut.setPreferredSize(new Dimension(100, 25));
		deleteBut.setForeground(Color.white);
		deleteBut.setBackground(Color.green.darker());
		deleteBut.setBorder(new LineBorder(Color.green.brighter()));
		deleteBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame.putPanel(panel);
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
		return deleteBut;
	}

	public JButton setDeleteButton(ViewFrame viewFrame) {
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
		return deleteBut;
	}

	public JPanel setDeleteRadioGroup(ViewFrame viewFrame) {
		JRadioButton dvdRadio = new JRadioButton("DVD");
		dvdRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame.putPanel(new DeleteDVDPanel(viewFrame));
			}
		});
		JRadioButton cdRadio = new JRadioButton("CD");
		cdRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewFrame.putPanel(new DeleteCDPanel(viewFrame));
			}
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(cdRadio);
		bg.add(dvdRadio);
		JPanel radioBox = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(2, 1));
		grid.setBackground(Color.white);
		grid.setPreferredSize(new Dimension(50, 50));
		JPanel top = new JPanel();
		JLabel jl = new JLabel("Delete Item");
		top.setBackground(Color.gray.brighter());
		top.add(jl);
		radioBox.add(top, BorderLayout.NORTH);
		grid.add(cdRadio);
		grid.add(dvdRadio);
		radioBox.add(grid, BorderLayout.CENTER);
		return radioBox;
	}

	public JPanel setAddRadioGroup(ViewFrame viewFrame) {
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
		JPanel radioBox = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(2, 1));
		grid.setBackground(Color.white);
		grid.setPreferredSize(new Dimension(50, 50));
		JPanel top = new JPanel();
		JLabel jl = new JLabel("Add Item");
		top.setBackground(Color.gray.brighter());
		top.add(jl);
		radioBox.add(top, BorderLayout.NORTH);
		grid.add(cdRadio);
		grid.add(dvdRadio);
		radioBox.add(grid, BorderLayout.CENTER);
		return radioBox;
	}

	public JPanel setAddBox(String message, String messageReturned) {
		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel(message);
		addTop.setBackground(Color.gray.brighter());
		addBox.setPreferredSize(new Dimension(300, 30));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		if(messageReturned != null) {
			JLabel res = new JLabel(messageReturned);
			addBox.add(res);
			res.setHorizontalAlignment(JLabel.CENTER);
		}
		return addBox;
	}
	
	public JPanel setAddBox(String message) {
		JPanel addBox = new JPanel(new BorderLayout());
		JPanel addTop = new JPanel();
		JLabel addjl = new JLabel(message);
		addTop.setBackground(Color.gray.brighter());
		addBox.setPreferredSize(new Dimension(300, 30));
		addTop.add(addjl);
		addBox.add(addTop, BorderLayout.NORTH);
		return addBox;
	}

	public JPanel setAddGrid(JPanel addBox) {
		JPanel addGrid = new JPanel(new GridLayout(5, 2));
		addGrid.setBorder(new LineBorder(Color.white, 10));
		addGrid.setPreferredSize(new Dimension(40, 40));
		addGrid.setBackground(Color.white);
		addBox.add(addGrid, BorderLayout.CENTER);
		return addGrid;
	}

	public void setConstraints(int gridx, int gridy, int anchor, Component component,
			GridBagConstraints gridBagConstraint) {
		gridBagConstraint.gridx = gridx;
		gridBagConstraint.gridy = gridy;
		gridBagConstraint.anchor = anchor;
		add(component, gridBagConstraint);
	}

	public void setConstraints(int gridx, int gridy, int fill, int anchor, int weighty, Component component,
			GridBagConstraints gridBagConstraint) {
		gridBagConstraint.gridx = gridx;
		gridBagConstraint.gridy = gridy;
		gridBagConstraint.fill = fill;
		gridBagConstraint.weighty = weighty;
		gridBagConstraint.anchor = anchor;
		add(component, gridBagConstraint);
	}

	public void setConstraints(int gridx, int gridy, int fill, int anchor, Component component,
			GridBagConstraints gridBagConstraint) {
		gridBagConstraint.gridx = gridx;
		gridBagConstraint.gridy = gridy;
		gridBagConstraint.fill = fill;
		gridBagConstraint.anchor = GridBagConstraints.CENTER;
		add(component, gridBagConstraint);
	}

	public void setConstraints(int gridx, int gridy, int fill, int anchor, double weighty, Component component,
			GridBagConstraints gridBagConstraint) {
		gridBagConstraint.gridx = gridx;
		gridBagConstraint.gridy = gridy;
		gridBagConstraint.fill = fill;
		gridBagConstraint.weighty = weighty;
		gridBagConstraint.anchor = anchor;
		add(component, gridBagConstraint);
	}

	public int getSelectedCDRow() {
		return selectedCDRow;
	}

	public void setSelectedCDRow(int selectedCDRow) {
		this.selectedCDRow = selectedCDRow;
	}

	public String getSelectedCDTitle() {
		return selectedCDTitle;
	}

	public void setSelectedCDTitle(String selectedCDTitle) {
		this.selectedCDTitle = selectedCDTitle;
	}

	public int getSelectedDVDRow() {
		return selectedDVDRow;
	}

	public void setSelectedDVDRow(int selectedDVDRow) {
		this.selectedDVDRow = selectedDVDRow;
	}

	public String getSelectedDVDTitle() {
		return selectedDVDTitle;
	}

	public void setSelectedDVDTitle(String selectedDVDTitle) {
		this.selectedDVDTitle = selectedDVDTitle;
	}
}