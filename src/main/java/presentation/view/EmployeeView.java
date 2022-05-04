package presentation.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class EmployeeView extends TemplateView {

	private JTextArea notificationsArea = new JTextArea("");
	private JScrollPane scrollPane = new JScrollPane(notificationsArea);
	
	private JButton exitButton = new JButton("Delogare");
	
	public EmployeeView() {
		super("Comenzi noi");
		
		buttonPanel.setLayout(new GridLayout(1,1,0,10));
		buttonPanel.add(exitButton);
		
		notificationsArea.setEditable(false);
		notificationsArea.setFont(new Font("",Font.PLAIN,15));
		contentPanel.add("notifications",scrollPane);
		
		titleTextLabel.setText("Notificări");
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JTextArea getNotificationsArea() {
		return notificationsArea;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
