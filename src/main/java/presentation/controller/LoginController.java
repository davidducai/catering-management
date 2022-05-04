package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import businesslogic.DeliveryService;
import data.DataWriter;
import data.User;
import presentation.view.AdminView;
import presentation.view.ClientView;
import presentation.view.EmployeeView;
import presentation.view.LoginView;

public class LoginController implements ActionListener, WindowListener {

	private LoginView view;
	private DeliveryService deliveryService;
	
	private static final String ADMIN_USERNAME = "admin";
	private static final String EMPLOYEE_USERNAME = "employee";
	
	public LoginController(LoginView view, DeliveryService deliveryService) {
		this.view = view;
		this.deliveryService = deliveryService;
		
		this.view.getLoginButton().addActionListener(this);
		this.view.getRegisterButton().addActionListener(this);
		this.view.addWindowListener(this);
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getLoginButton()) {
			String username = view.getUsernameLoginField().getText();
			String password = view.getPasswordLoginField().getText();
			try {
				if(username.isBlank() || password.isBlank()) {
					JOptionPane.showMessageDialog(view,"Introduceți un nume de utilizator și o parolă!","Eroare",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(User.verifyCredentials(username, password) == true) {
						switch(username) {
						case ADMIN_USERNAME:
							AdminView adminView = new AdminView();
							AdminController adminController = new AdminController(adminView, deliveryService);
							break;
						case EMPLOYEE_USERNAME:
							EmployeeView employeeView = new EmployeeView();
							EmployeeController employeeController = new EmployeeController(employeeView, deliveryService);
							deliveryService.addObserver(employeeController);
							break;
						default:
							ClientView clientView = new ClientView();
							ClientController clientController = new ClientController(clientView, deliveryService, username);
							break;
						}
					}
					else {
						JOptionPane.showMessageDialog(view,"Credențiale incorecte!","Eroare",JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch(IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if(e.getSource() == view.getRegisterButton()) {
			String username = view.getUsernameRegisterField().getText();
			String password = view.getPasswordRegisterField().getText();
			try {
				if(username.isBlank() || password.isBlank()) {
					JOptionPane.showMessageDialog(view,"Introduceți un nume de utilizator și o parolă!","Eroare",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(User.findUsername(username) == false) { // verifica daca username-ul este valabil
						DataWriter.addUser(username, password);
						JOptionPane.showMessageDialog(view,"Înregistrare reușită!","Succes",JOptionPane.INFORMATION_MESSAGE);
						view.getUsernameRegisterField().setText("");
						view.getPasswordRegisterField().setText("");
					}
					else {
						JOptionPane.showMessageDialog(view,"Numele introdus este deja utilizat!","Eroare",JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		DataWriter.serialize(deliveryService); // salveaza datele din aplicatie la iesire
	}
	
	
	// nefolosite
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
