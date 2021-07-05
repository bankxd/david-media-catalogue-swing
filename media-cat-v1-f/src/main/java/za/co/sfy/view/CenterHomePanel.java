package za.co.sfy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CenterHomePanel extends AbstractCenterPanel {
	
	private static final long serialVersionUID = 1L;

	public CenterHomePanel(ViewFrame viewFrame) {
		initComponents(viewFrame);
	}

	public void initComponents(ViewFrame viewFrame) {
		GridBagConstraints gridBagConstraints = initGridBag();
		setConstraints(0, 0, GridBagConstraints.FIRST_LINE_START, setTitleJLabel("Home"), gridBagConstraints);
		setConstraints(0, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 1, setTopSeparator(new Dimension(400, 250)), gridBagConstraints);
		setConstraints(0, 2, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER, 1,  setLowerSeparator(new Dimension(400, 5)), gridBagConstraints);
		setConstraints(0, 3, GridBagConstraints.CENTER, setButtonPanel(viewFrame), gridBagConstraints);
	}

	
	public JPanel setButtonPanel(ViewFrame viewFrame) {
		JButton dvdBut = new JButton("DVDs");
		dvdBut.setPreferredSize(new Dimension(100, 25));
		dvdBut.setForeground(Color.white);
		dvdBut.setBackground(Color.green.darker());
		dvdBut.setBorder(new LineBorder(Color.green.brighter()));
		dvdBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewFrame.putPanel(new DVDCataloguePanel(viewFrame));
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
				viewFrame.putPanel(new CDCataloguePanel(viewFrame));
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
		return butPan;
	}
}
