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
public class AdminView extends TemplateView {

	private JPanel menuPanel = new JPanel();
	private JPanel insertBasePanel = new JPanel();
	private JPanel editPanel = new JPanel();
	private JPanel reportsPanel = new JPanel();
	private JPanel insertCompositePanel = new JPanel();
	
	
	// menuPanel
	private JPanel menuButtonPanel = new JPanel();
	private JPanel menuTablePanel = new JPanel();
	private JPanel addCompositePanel = new JPanel();
	
	private Table menuTable = new Table();
	
	private JTextArea itemDescriptionArea = new JTextArea();
	
	private JButton addBaseButton = new JButton("Produs nou");
	private JButton editButton = new JButton("Editare");
	private JButton removeButton = new JButton("Ștergere");
	
	private JLabel itemLabel = new JLabel();
	private JButton addCompositeButton = new JButton("Adăugare în meniu");
	private JButton finishCompositeButton = new JButton("Finalizare meniu");
	
	
	// addBasePanel
	private JPanel baseTitlePanel = new JPanel();
	private JPanel baseFirstRowPanel = new JPanel();
	private JPanel baseSecondRowPanel = new JPanel();
	private JPanel baseButtonPanel = new JPanel();
	
	private JTextField baseTitleField = new JTextField(30);
	private JTextField baseRatingField = new JTextField(5);
	private JTextField baseCaloriesField = new JTextField(5);
	private JTextField baseProteinField = new JTextField(5);
	private JTextField baseFatField = new JTextField(5);
	private JTextField baseSodiumField = new JTextField(5);
	private JTextField basePriceField = new JTextField(5);
	
	private JButton insertBaseButton = new JButton("Adăugare produs de bază");
	
	
	// editPanel
	private JPanel editTitlePanel = new JPanel();
	private JPanel editRatingPanel = new JPanel();
	private JPanel editPricePanel = new JPanel();
	private JPanel editButtonPanel = new JPanel();
	
	private JTextField editTitleField = new JTextField(30);
	private JTextField editRatingField = new JTextField(10);
	private JTextField editPriceField = new JTextField(10);
	
	private JButton updateButton = new JButton("Actualizare");
	
	
	// insertCompositePanel
	private JPanel compositeTitlePanel = new JPanel();
	private JPanel compositeRatingPanel = new JPanel();
	private JPanel compositeButtonPanel = new JPanel();
	
	private JTextField compositeTitleField = new JTextField(30);
	private JTextField compositeRatingField = new JTextField(10);
	
	private JButton insertCompositeButton = new JButton("Adăugare meniu nou");
	
	
	// reportsPanel
	private JPanel timeReportPanel = new JPanel();
	private JPanel itemsReportPanel = new JPanel();
	private JPanel clientsReportPanel = new JPanel();
	private JPanel dayReportPanel = new JPanel();
	
	private JTextField timeStartField = new JTextField(4);
	private JTextField timeEndField = new JTextField(4);
	private JTextField itemCountField = new JTextField(3);
	private JTextField orderCountField = new JTextField(3);
	private JTextField priceField = new JTextField(4);
	private JTextField dayField = new JTextField(5);
	
	private JButton timeReportButton = new JButton("Generare raport");
	private JButton itemsReportButton = new JButton("Generare raport");
	private JButton clientsReportButton = new JButton("Generare raport");
	private JButton dayReportButton = new JButton("Generare raport");
	
	
	// buttonPanel
	private JButton menuButton = new JButton("Meniu");
	private JButton importButton = new JButton("Importare");
	private JButton reportsButton = new JButton("Rapoarte");
	private JButton exitButton = new JButton("Delogare");
	
	
	// extra
	private Dimension fieldDimension = new Dimension(200,30);
	
	
	public AdminView() {
		super("Administrare");
		
		buttonPanel.setLayout(new GridLayout(4,1,0,10));
		buttonPanel.add(menuButton);
		buttonPanel.add(importButton);
		buttonPanel.add(reportsButton);
		buttonPanel.add(exitButton);
		
		
		// menuPanel
		menuButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menuButtonPanel.add(new JLabel("Produse de bază: "));
		menuButtonPanel.add(addBaseButton);
		menuButtonPanel.add(editButton);
		menuButtonPanel.add(removeButton);
		
		menuTablePanel.setLayout(new BorderLayout());
		menuTablePanel.add(new JScrollPane(menuTable.getTable()),BorderLayout.CENTER);
		
		itemDescriptionArea.setPreferredSize(new Dimension(200,0));
		itemDescriptionArea.setEditable(false);
		itemDescriptionArea.setBackground(UIManager.getColor("Panel.background"));
		
		addCompositePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addCompositePanel.add(new JLabel("Meniu nou: "));
		addCompositePanel.add(itemLabel);
		addCompositePanel.add(addCompositeButton);
		addCompositePanel.add(finishCompositeButton);
		
		menuPanel.setLayout(new BorderLayout());
		menuPanel.add(menuButtonPanel, BorderLayout.NORTH);
		menuPanel.add(menuTablePanel, BorderLayout.CENTER);
		menuPanel.add(addCompositePanel, BorderLayout.SOUTH);
		menuPanel.add(itemDescriptionArea, BorderLayout.EAST);
		
		
		// insertBasePanel
		baseTitleField.setPreferredSize(fieldDimension);
		baseRatingField.setPreferredSize(fieldDimension);
		baseCaloriesField.setPreferredSize(fieldDimension);
		baseProteinField.setPreferredSize(fieldDimension);
		baseFatField.setPreferredSize(fieldDimension); 
		baseSodiumField.setPreferredSize(fieldDimension);
		basePriceField.setPreferredSize(fieldDimension);
		
		baseTitlePanel.add(new JLabel("Denumire: "));
		baseTitlePanel.add(baseTitleField);
		baseFirstRowPanel.add(new JLabel("Calorii: "));
		baseFirstRowPanel.add(baseCaloriesField);
		baseFirstRowPanel.add(new JLabel(" Proteine: "));
		baseFirstRowPanel.add(baseProteinField);
		baseFirstRowPanel.add(new JLabel(" Grăsime: "));
		baseFirstRowPanel.add(baseFatField);
		baseFirstRowPanel.add(new JLabel(" Sodiu: "));
		baseFirstRowPanel.add(baseSodiumField);
		baseSecondRowPanel.add(new JLabel("Rating: "));
		baseSecondRowPanel.add(baseRatingField);
		baseSecondRowPanel.add(new JLabel(" Preț: "));
		baseSecondRowPanel.add(basePriceField);
		baseButtonPanel.add(insertBaseButton);
		
		insertBasePanel.setLayout(new GridLayout(5,1));
		insertBasePanel.add(new JPanel());
		insertBasePanel.add(baseTitlePanel);
		insertBasePanel.add(baseFirstRowPanel);
		insertBasePanel.add(baseSecondRowPanel);
		insertBasePanel.add(baseButtonPanel);
		
		
		// editPanel
		editTitleField.setPreferredSize(fieldDimension);
		editRatingField.setPreferredSize(fieldDimension);
		editPriceField.setPreferredSize(fieldDimension);
		
		editTitlePanel.add(new JLabel("Denumire: "));
		editTitlePanel.add(editTitleField);
		editRatingPanel.add(new JLabel("Rating: "));
		editRatingPanel.add(editRatingField);
		editPricePanel.add(new JLabel("Preț:    "));
		editPricePanel.add(editPriceField);
		editButtonPanel.add(updateButton);
		
		editPanel.setLayout(new GridLayout(5,1));
		editPanel.add(new JPanel());
		editPanel.add(editTitlePanel);
		editPanel.add(editRatingPanel);
		editPanel.add(editPricePanel);
		editPanel.add(editButtonPanel);
		
		
		// insertCompositePanel
		compositeTitleField.setPreferredSize(fieldDimension);
		compositeRatingField.setPreferredSize(fieldDimension);
		
		compositeTitlePanel.add(new JLabel("Denumire: "));
		compositeTitlePanel.add(compositeTitleField);
		compositeRatingPanel.add(new JLabel("Rating: "));
		compositeRatingPanel.add(compositeRatingField);
		compositeButtonPanel.add(insertCompositeButton);
		
		insertCompositePanel.setLayout(new GridLayout(4,1));
		insertCompositePanel.add(new JPanel());
		insertCompositePanel.add(compositeTitlePanel);
		insertCompositePanel.add(compositeRatingPanel);
		insertCompositePanel.add(compositeButtonPanel);
		
		
		// reportsPanel
		timeStartField.setPreferredSize(fieldDimension);
		timeEndField.setPreferredSize(fieldDimension);
		itemCountField.setPreferredSize(fieldDimension);
		orderCountField.setPreferredSize(fieldDimension);
		priceField.setPreferredSize(fieldDimension);
		dayField.setPreferredSize(fieldDimension);
		
		timeReportPanel.add(new JLabel("Comenzi într-un interval orar   |   De la: "));
		timeReportPanel.add(timeStartField);
		timeReportPanel.add(new JLabel("Până la: "));
		timeReportPanel.add(timeEndField);
		timeReportPanel.add(timeReportButton);
		
		itemsReportPanel.add(new JLabel("Produse comandate de un anumit număr de ori   |   Număr minim: "));
		itemsReportPanel.add(itemCountField);
		itemsReportPanel.add(itemsReportButton);
		
		clientsReportPanel.add(new JLabel("Clienți fideli   |   Număr minim comenzi: "));
		clientsReportPanel.add(orderCountField);
		clientsReportPanel.add(new JLabel(" Preț minim comandă: "));
		clientsReportPanel.add(priceField);
		clientsReportPanel.add(clientsReportButton);
		
		dayReportPanel.add(new JLabel("Comenzi preluate într-o anumită dată   |   Data: "));
		dayReportPanel.add(dayField);
		dayReportPanel.add(dayReportButton);
		
		reportsPanel.setLayout(new GridLayout(5,1));
		reportsPanel.add(new JPanel());
		reportsPanel.add(timeReportPanel);
		reportsPanel.add(itemsReportPanel);
		reportsPanel.add(clientsReportPanel);
		reportsPanel.add(dayReportPanel);
		
		
		// template
		contentPanel.add("menu",menuPanel);
		contentPanel.add("insertBase", insertBasePanel);
		contentPanel.add("edit", editPanel);
		contentPanel.add("reports", reportsPanel);
		contentPanel.add("insertComposite", insertCompositePanel);
		
		titleTextLabel.setText("Lista meniu");
		
		
		// final
		finishCompositeButton.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public Table getMenuTable() {
		return menuTable;
	}

	public JButton getAddBaseButton() {
		return addBaseButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JLabel getItemLabel() {
		return itemLabel;
	}

	public JButton getAddCompositeButton() {
		return addCompositeButton;
	}

	public JButton getFinishCompositeButton() {
		return finishCompositeButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JButton getReportsButton() {
		return reportsButton;
	}

	public JButton getImportButton() {
		return importButton;
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	public JTextField getCompositeTitleField() {
		return compositeTitleField;
	}

	public JTextField getCompositeRatingField() {
		return compositeRatingField;
	}

	public JButton getInsertCompositeButton() {
		return insertCompositeButton;
	}

	public JTextField getBaseTitleField() {
		return baseTitleField;
	}

	public JTextField getBaseRatingField() {
		return baseRatingField;
	}

	public JTextField getBaseCaloriesField() {
		return baseCaloriesField;
	}

	public JTextField getBaseProteinField() {
		return baseProteinField;
	}

	public JTextField getBaseFatField() {
		return baseFatField;
	}

	public JTextField getBaseSodiumField() {
		return baseSodiumField;
	}

	public JTextField getBasePriceField() {
		return basePriceField;
	}

	public JButton getInsertBaseButton() {
		return insertBaseButton;
	}

	public JTextField getEditTitleField() {
		return editTitleField;
	}

	public JTextField getEditRatingField() {
		return editRatingField;
	}

	public JTextField getEditPriceField() {
		return editPriceField;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JTextArea getItemDescriptionArea() {
		return itemDescriptionArea;
	}

	public JTextField getTimeStartField() {
		return timeStartField;
	}

	public JTextField getTimeEndField() {
		return timeEndField;
	}

	public JTextField getItemCountField() {
		return itemCountField;
	}

	public JTextField getOrderCountField() {
		return orderCountField;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public JTextField getDayField() {
		return dayField;
	}

	public JButton getTimeReportButton() {
		return timeReportButton;
	}

	public JButton getItemsReportButton() {
		return itemsReportButton;
	}

	public JButton getClientsReportButton() {
		return clientsReportButton;
	}

	public JButton getDayReportButton() {
		return dayReportButton;
	}
}
