package za.co.sfy.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

public class CenterAboutPanel extends AbstractCenterPanel {

	private static final long serialVersionUID = 3913597795791355034L;
	
	public CenterAboutPanel() {
		initComponents();
	}
	
	public void initComponents() {
		GridBagConstraints gridBagConstraints = initGridBag();
		setConstraints(0,0,GridBagConstraints.FIRST_LINE_START, setTitleJLabel("About"), gridBagConstraints);
		setConstraints(0,1,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER, 1, setTopSeparator(new Dimension(400, 25)), gridBagConstraints);
		setConstraints(0,2,GridBagConstraints.CENTER, setImage("C:\\Users\\daves\\Pictures\\mediaCatImg.png"), gridBagConstraints);
		setConstraints(0,3,GridBagConstraints.CENTER, setText("Media Catalogue displays all CDs and DVDs added by the user."), gridBagConstraints);
		setConstraints(0,4,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER, 1, setLowerSeparator(new Dimension(400, 5)), gridBagConstraints);
	}
	
	public Component setText(String string) {
		JLabel about = new JLabel(string);
		return about;
	}
}
