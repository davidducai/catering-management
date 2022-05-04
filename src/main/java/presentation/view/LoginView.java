package presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JLabel titleLoginLabel = new JLabel("Autentificare", SwingConstants.CENTER);
	private JLabel usernameLoginLabel = new JLabel("Utilizator");
	private JLabel passwordLoginLabel = new JLabel("Parola    ");
	private JLabel logoLabel;
	
	private JTextField usernameLoginField = new JTextField(10);
	private JPasswordField passwordLoginField = new JPasswordField(10);
	
	private JTextField usernameRegisterField = new JTextField(10);
	private JPasswordField passwordRegisterField = new JPasswordField(10);
	
	private JPanel contentPanel = new JPanel();
	private JPanel titleLoginPanel = new JPanel();
	private JPanel usernameLoginPanel = new JPanel();
	private JPanel passwordLoginPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	
	private JPanel logoPanel = new JPanel();
	
	private JLabel titleRegisterLabel = new JLabel("Client nou", SwingConstants.CENTER);
	private JLabel usernameRegisterLabel = new JLabel("Utilizator");
	private JLabel passwordRegisterLabel = new JLabel("Parola    ");
	
	private JPanel titleRegisterPanel = new JPanel();
	private JPanel usernameRegisterPanel = new JPanel();
	private JPanel passwordRegisterPanel = new JPanel();
	private JPanel registerPanel = new JPanel();
	
	private JButton loginButton = new JButton("Conectare");
	private JButton registerButton = new JButton("Înregistrare");

	
	public LoginView() {
		
		this.setSize(600,500);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setTitle("Autentificare");
		
		contentPanel.setLayout(new GridLayout(4,2,25,0));
		
		loginButton.setPreferredSize(new Dimension(100,25));
		registerButton.setPreferredSize(new Dimension(101,25));
		
		titleLoginLabel.setFont(new Font("Calibri", Font.BOLD, 19));
		titleLoginPanel.setLayout(new GridBagLayout());
		titleRegisterLabel.setFont(new Font("Calibri", Font.BOLD, 19));
		titleRegisterPanel.setLayout(new GridBagLayout());
		
		logoPanel.setPreferredSize(new Dimension(1,70));
		logoPanel.setBackground(Color.white);
		ImageIcon logo = new ImageIcon("logo_small.png");
		logoLabel = new JLabel("",logo,JLabel.CENTER);
		
		contentPanel.add(titleLoginPanel);
		contentPanel.add(titleRegisterPanel);
		contentPanel.add(usernameLoginPanel);
		contentPanel.add(usernameRegisterPanel);
		contentPanel.add(passwordLoginPanel);
		contentPanel.add(passwordRegisterPanel);
		contentPanel.add(loginPanel);
		contentPanel.add(registerPanel);
		
		this.add(contentPanel,BorderLayout.CENTER);
		this.add(logoPanel,BorderLayout.SOUTH);
		
		titleLoginPanel.add(titleLoginLabel);
		usernameLoginPanel.add(usernameLoginLabel);
		usernameLoginPanel.add(usernameLoginField);
		passwordLoginPanel.add(passwordLoginLabel);
		passwordLoginPanel.add(passwordLoginField);
		loginPanel.add(loginButton);
		
		titleRegisterPanel.add(titleRegisterLabel);
		usernameRegisterPanel.add(usernameRegisterLabel);
		usernameRegisterPanel.add(usernameRegisterField);
		passwordRegisterPanel.add(passwordRegisterLabel);
		passwordRegisterPanel.add(passwordRegisterField);
		registerPanel.add(registerButton);
		
		logoPanel.add(logoLabel);
	
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JTextField getUsernameLoginField() {
		return usernameLoginField;
	}

	public JPasswordField getPasswordLoginField() {
		return passwordLoginField;
	}

	public JTextField getUsernameRegisterField() {
		return usernameRegisterField;
	}

	public JPasswordField getPasswordRegisterField() {
		return passwordRegisterField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}
}
