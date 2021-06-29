package za.co.sfy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class CenterHomePanel extends JPanel {
	
	private static final long serialVersionUID = 5036241293854159122L;

	public int index;
	
	private ViewFrame v;

	public CenterHomePanel(ViewFrame v) {
		this.v = v;
		initComponents();
	}

	public void initComponents() {
		index = 1;
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel topLabel = new JLabel("Home");
		JSeparator js1 = new JSeparator();
		js1.setPreferredSize(new Dimension(400, 250));
		JSeparator js2 = new JSeparator();
		js2.setPreferredSize(new Dimension(400, 5));

		JButton dvdBut = new JButton("DVDs");
		dvdBut.setPreferredSize(new Dimension(100, 25));
		dvdBut.setForeground(Color.white);
		dvdBut.setBackground(Color.green.darker());
		dvdBut.setBorder(new LineBorder(Color.green.brighter()));
		dvdBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				v.putPanel(new DVDCataloguePanel(v));
			}
		});
		dvdBut.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	dvdBut.setBackground(Color.gray.brighter());
		    }

		    public void mouseExited(MouseEvent evt) {
		    	dvdBut.setBackground(Color.green.darker());
		    }
		});
		JButton cdBut = new JButton("CDs");
		cdBut.setPreferredSize(new Dimension(100, 25));
		cdBut.setForeground(Color.white);
		cdBut.setBackground(Color.green.darker());
		cdBut.setBorder(new LineBorder(Color.green.brighter()));
		cdBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				v.putPanel(new CDCataloguePanel(v));
			}
		});
		cdBut.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	cdBut.setBackground(Color.gray.brighter());
		    }

		    public void mouseExited(MouseEvent evt) {
		    	cdBut.setBackground(Color.green.darker());
		    }
		});
		JPanel butPan = new JPanel();
		butPan.add(dvdBut);
		butPan.add(cdBut);

		gbc.gridx = 0;
		gbc.gridy = 0; 
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(topLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(butPan, gbc);

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
