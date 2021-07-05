package za.co.sfy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public abstract class AbstractBackGroundPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ViewFrame viewFrame;

	public AbstractBackGroundPanel(ViewFrame v) {
		this.viewFrame = v;
	}
	
	public void initComponents(JPanel centralPanel) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GREEN.brighter());

		JPanel navPanel = new JPanel(new BorderLayout());
		JPanel innerNavPanel = new JPanel(new BorderLayout());
		JPanel navButtons = new JPanel(new GridLayout(1, 3));
		navPanel.setVisible(true);
		navPanel.setBackground(Color.GREEN.darker());
		navPanel.setPreferredSize(new Dimension(1000, 60));

		JButton homeBut = new JButton("HOME");
		homeBut.setBackground(Color.GREEN.darker());
		homeBut.setForeground(Color.WHITE);
		homeBut.setBorderPainted(false);
		homeBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewFrame.putPanel(new HomePanel(viewFrame));
			}
		});
		JButton aboutBut = new JButton("ABOUT");
		aboutBut.setBackground(Color.GREEN.darker());
		aboutBut.setForeground(Color.WHITE);
		aboutBut.setBorderPainted(false);
		aboutBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewFrame.putPanel(new AboutPanel(viewFrame));
			}
		});
		JButton catalogueBut = new JButton("CATALOGUE");
		catalogueBut.setBackground(Color.GREEN.darker());
		catalogueBut.setForeground(Color.WHITE);
		catalogueBut.setBorderPainted(false);
		catalogueBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewFrame.putPanel(new CataloguePanel(viewFrame));
			}
		});

		navButtons.add(homeBut);
		navButtons.add(aboutBut);
		navButtons.add(catalogueBut);
		navButtons.setBackground(Color.GREEN.darker());
		innerNavPanel.add(navButtons);

		JPanel eastPanelNav = new JPanel();
		eastPanelNav.setBackground(Color.GREEN.darker());
		eastPanelNav.setPreferredSize(new Dimension(250, 50));

		JPanel westPanelNav = new JPanel(new BorderLayout());
		BufferedImage myPicture = null;
		try {
			// XXX to avoid hard coding values specific to your environment, I would recommend you use property files, see: https://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
			myPicture = ImageIO.read(new File("C:\\Users\\daves\\Pictures\\mediaCatImg.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error loading Image:", e);
		}
		JLabel picLabel = null;
		if (myPicture != null) {
			picLabel = new JLabel(new ImageIcon(myPicture));
			westPanelNav.add(picLabel);
		} else {
			picLabel = new JLabel("SFY");
			westPanelNav.add(picLabel);
		}
		westPanelNav.setBackground(Color.GREEN.darker());
		westPanelNav.setPreferredSize(new Dimension(250, 50));

		innerNavPanel.add(eastPanelNav, BorderLayout.EAST);
		innerNavPanel.add(westPanelNav, BorderLayout.WEST);
		navPanel.add(innerNavPanel);
		this.add(navPanel, BorderLayout.NORTH);

		JPanel mainCenterPanel = new JPanel();
		mainCenterPanel.setLayout(new BorderLayout());
		mainCenterPanel.setVisible(true);
		mainCenterPanel.setBackground(Color.WHITE);
		mainCenterPanel.setPreferredSize(new Dimension(100, 100));
		mainCenterPanel.setMaximumSize(new Dimension(100, 100));
		mainCenterPanel.setBorder(new LineBorder(Color.WHITE, 10, false));
		mainCenterPanel.add(centralPanel, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setVisible(true);
		centerPanel.setBackground(Color.GREEN.brighter());
		centerPanel.setPreferredSize(new Dimension(100, 100));
		centerPanel.setMaximumSize(new Dimension(100, 100));
		centerPanel.setBorder(new LineBorder(Color.GREEN.brighter(), 25, true));
		centerPanel.add(mainCenterPanel, BorderLayout.CENTER);
		this.add(centerPanel, BorderLayout.CENTER);

		JPanel westPanel = new JPanel();
		westPanel.setVisible(true);
		westPanel.setBackground(Color.GREEN.brighter());
		westPanel.setPreferredSize(new Dimension(200, 200));
		this.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setVisible(true);
		eastPanel.setBackground(Color.GREEN.brighter());
		eastPanel.setPreferredSize(new Dimension(200, 200));
		this.add(eastPanel, BorderLayout.EAST);

		JPanel footerPanel = new JPanel(new BorderLayout());
		JLabel footLabel = new JLabel("  © 2020 Media Catalogue");
		footLabel.setForeground(Color.WHITE);
		footerPanel.add(footLabel, BorderLayout.WEST);
		footerPanel.setVisible(true);
		footerPanel.setBackground(Color.GREEN.darker());
		footerPanel.setPreferredSize(new Dimension(1000, 60));
		this.add(footerPanel, BorderLayout.SOUTH);
	}
}
