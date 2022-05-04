package presentation.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Toate ferestrele utilizeaza acest sablon.
 * Titlul ferestrei si zona de continut (contentPanel) se modifica in functie de utilizator. Restul elementelor de design raman neschimbate.
 *
 * @author Ducai David
 */
@SuppressWarnings("serial")
public class TemplateView extends JFrame {
/**  */
	
	protected JLabel logoLabel = new JLabel();
	protected JLabel titleTextLabel = new JLabel();
	
	protected JPanel logoPanel = new JPanel();
	protected JPanel mainPanel = new JPanel();
	protected JPanel titleTextPanel = new JPanel();
	protected JPanel contentPanel = new JPanel();
	protected JPanel buttonPanel = new JPanel();
	
	Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	protected CardLayout content;
	
	
	TemplateView(String viewTitle){
		
		this.setTitle(viewTitle);
		this.setSize(1000,765);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("logo.png");
		logoLabel = new JLabel("",logo,JLabel.CENTER);
		
		logoPanel.setPreferredSize(new Dimension(200,800));
		logoPanel.setBackground(Color.white);

		logoPanel.add(logoLabel);
		
		buttonPanel.setBackground(logoPanel.getBackground());
		
		logoPanel.add(buttonPanel);
		
		titleTextLabel.setFont(new Font("Tahoma",Font.BOLD,25));
		titleTextLabel.setPreferredSize(new Dimension(700,100));
		
		titleTextPanel.add(titleTextLabel);
		
		contentPanel.setPreferredSize(new Dimension(700,570));
		contentPanel.setBorder(border);
		contentPanel.setLayout(new CardLayout());
		content = (CardLayout) contentPanel.getLayout();
		
		mainPanel.add(titleTextPanel);
		mainPanel.add(contentPanel);
		
		this.add(logoPanel,BorderLayout.WEST);
		this.add(mainPanel,BorderLayout.CENTER);

		this.setLocationRelativeTo(null);
	}
	
	public JLabel getTitleTextLabel() {
		return titleTextLabel;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public CardLayout getContent() {
		return content;
	}
}
