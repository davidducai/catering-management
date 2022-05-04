package presentation.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import presentation.Table;

@SuppressWarnings("serial")
public class ClientView extends TemplateView{

	private JPanel menuPanel = new JPanel();
	private JPanel itemsPanel = new JPanel();
	
	// menuPanel
	private JPanel searchPanel = new JPanel();
	private JPanel menuTablePanel = new JPanel();
	private JPanel menuButtonPanel = new JPanel();
	
	private JTextField titleField = new JTextField(10);
	private JTextField ratingField = new JTextField(10);
	private JTextField priceField = new JTextField(10);
	private JButton searchButton = new JButton("Căutare");
	
	private JTextArea itemDescriptionArea = new JTextArea();
	
	private JLabel itemLabel = new JLabel();
	private JButton addButton = new JButton("Adaugă");
	private JButton nextButton = new JButton("Înainte >");
	
	// itemsPanel
	private JPanel itemsTablePanel = new JPanel();
	private JPanel itemsButtonPanel = new JPanel();
	
	private JButton backButton = new JButton("< Înapoi");
	private JButton removeButton = new JButton("Șterge");
	private JButton finishButton = new JButton("Comandă");
	
	// buttonPanel
	private JButton newOrderButton = new JButton("Comandare");
	private JButton exitButton = new JButton("Delogare");
	
	
	// tabele
	private Table menuTable = new Table();
	private Table itemsTable = new Table();
	
	public ClientView() {
		super("Plasare comandă");
		
		buttonPanel.setLayout(new GridLayout(2,1,0,10));
		buttonPanel.add(newOrderButton);
		buttonPanel.add(exitButton);
		newOrderButton.setEnabled(false);
		
		menuPanel.setLayout(new BorderLayout());
		itemsPanel.setLayout(new BorderLayout());
		
		
		// menuPanel
		searchPanel.add(new JLabel("Denumire: "));
		searchPanel.add(titleField);
		searchPanel.add(new JLabel(" Rating minim: "));
		searchPanel.add(ratingField);
		searchPanel.add(new JLabel(" Preț maxim: "));
		searchPanel.add(priceField);
		searchPanel.add(searchButton);
		
		menuTablePanel.setLayout(new BorderLayout());
		menuTablePanel.add(new JScrollPane(menuTable.getTable()),BorderLayout.CENTER);
		
		itemDescriptionArea.setPreferredSize(new Dimension(200,0));
		itemDescriptionArea.setEditable(false);
		itemDescriptionArea.setBackground(UIManager.getColor("Panel.background"));
		
		menuButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menuButtonPanel.add(itemLabel);
		menuButtonPanel.add(addButton);
		menuButtonPanel.add(nextButton);
		
		menuPanel.add(searchPanel, BorderLayout.NORTH);
		menuPanel.add(menuTablePanel, BorderLayout.CENTER);
		menuPanel.add(menuButtonPanel, BorderLayout.SOUTH);
		menuPanel.add(itemDescriptionArea, BorderLayout.EAST);
		
		// itemsPanel
		itemsTablePanel.setLayout(new BorderLayout());
		itemsTablePanel.add(new JScrollPane(itemsTable.getTable()),BorderLayout.CENTER);
		
		itemsButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		itemsButtonPanel.add(backButton);
		itemsButtonPanel.add(removeButton);
		itemsButtonPanel.add(finishButton);
		
		itemsPanel.add(itemsTablePanel, BorderLayout.CENTER);
		itemsPanel.add(itemsButtonPanel, BorderLayout.SOUTH);
		
		
		// template
		contentPanel.add("menu", menuPanel);
		contentPanel.add("items", itemsPanel);
		
		titleTextLabel.setText("Selectare produse");
		
		
		// Final
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public JTextField getRatingField() {
		return ratingField;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JButton getFinishButton() {
		return finishButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public Table getMenuTable() {
		return menuTable;
	}

	public Table getItemsTable() {
		return itemsTable;
	}

	public JLabel getItemLabel() {
		return itemLabel;
	}

	public JTextArea getItemDescriptionArea() {
		return itemDescriptionArea;
	}

	public JButton getNewOrderButton() {
		return newOrderButton;
	}
}
