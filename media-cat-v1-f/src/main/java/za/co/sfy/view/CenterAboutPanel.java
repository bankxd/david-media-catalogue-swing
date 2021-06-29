package za.co.sfy.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class CenterAboutPanel extends JPanel {
	
	private static final long serialVersionUID = 3913597795791355034L;

	public CenterAboutPanel() {
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel topLabel = new JLabel("About");
		JSeparator js1 = new JSeparator();
		js1.setPreferredSize(new Dimension(400, 25));
		JSeparator js2 = new JSeparator();
		js2.setPreferredSize(new Dimension(400, 5));

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
    	
    	JLabel about = new JLabel("Media Catalogue displays all CDs and DVDs added by the user.");

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
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(picLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(about, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(js2, gbc);
	}
}
