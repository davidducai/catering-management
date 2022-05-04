package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import businesslogic.DeliveryService;
import businesslogic.model.BaseProduct;
import businesslogic.model.CompositeProduct;
import businesslogic.model.MenuItem;
import presentation.view.AdminView;

public class AdminController implements ActionListener, MouseListener {

	private AdminView view;
	private DeliveryService deliveryService;
	
	private List<MenuItem> selectedItemsList;
	
	public AdminController(AdminView view, DeliveryService deliveryService) {
		this.view = view;
		this.deliveryService = deliveryService;
		
		this.selectedItemsList = new ArrayList<MenuItem>();
		
		this.view.getMenuTable().populateTable(this.deliveryService.getMenuItems());
		
		this.view.getAddBaseButton().addActionListener(this);
		this.view.getAddCompositeButton().addActionListener(this);
		this.view.getEditButton().addActionListener(this);
		this.view.getRemoveButton().addActionListener(this);
		this.view.getExitButton().addActionListener(this);
		this.view.getReportsButton().addActionListener(this);
		this.view.getFinishCompositeButton().addActionListener(this);
		this.view.getImportButton().addActionListener(this);
		this.view.getMenuButton().addActionListener(this);
		this.view.getInsertCompositeButton().addActionListener(this);
		this.view.getInsertBaseButton().addActionListener(this);
		this.view.getUpdateButton().addActionListener(this);
		this.view.getTimeReportButton().addActionListener(this);
		this.view.getItemsReportButton().addActionListener(this);
		this.view.getClientsReportButton().addActionListener(this);
		this.view.getDayReportButton().addActionListener(this);
		this.view.getMenuTable().getTable().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == view.getExitButton()) {
			view.dispose();
		}
		if(e.getSource() == view.getMenuButton()) {
			showMenu();
		}
		if(e.getSource() == view.getImportButton()) {
			try {
				deliveryService.importProducts("products.csv");
				view.getMenuTable().updateTable(deliveryService.getMenuItems());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(view,"Nu se pot importa produsele!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getReportsButton()) {
			view.getTitleTextLabel().setText("Generare rapoarte");
			view.getContent().show(view.getContentPanel(),"reports");
		}
		if(e.getSource() == view.getAddBaseButton()) {
			view.getTitleTextLabel().setText("Adăugare produs");
			view.getContent().show(view.getContentPanel(),"insertBase");
		}
		if(e.getSource() == view.getEditButton()) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			if(selectedRow != -1) {
				String title = view.getMenuTable().getTableModel().getValueAt(selectedRow, 0).toString();
				String rating = view.getMenuTable().getTableModel().getValueAt(selectedRow, 1).toString();
				String price = view.getMenuTable().getTableModel().getValueAt(selectedRow, 2).toString();
				
				if(getItemFromSelectedRow(selectedRow) instanceof CompositeProduct) {
					view.getEditPriceField().setEnabled(false);
				}
				else {
					view.getEditPriceField().setEnabled(true);
				}
				
				view.getEditTitleField().setText(title);
				view.getEditRatingField().setText(rating);
				view.getEditPriceField().setText(price);
				view.getTitleTextLabel().setText("Actualizare produs");
				view.getContent().show(view.getContentPanel(),"edit");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un produs!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getRemoveButton()) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			if(selectedRow != -1) {
				deliveryService.removeItem(getItemFromSelectedRow(selectedRow)); // sterge produsul selectat din HashSet
				view.getMenuTable().updateTable(deliveryService.getMenuItems());
				view.getItemDescriptionArea().setText("");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un produs!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getAddCompositeButton()) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			if(selectedRow != -1) {
				MenuItem selectedItem = getItemFromSelectedRow(selectedRow);
				selectedItemsList.add(selectedItem);
				view.getItemLabel().setText(selectedItem.getTitle() + " adăugat ");
				view.getFinishCompositeButton().setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un produs pentru a putea realiza meniul!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getFinishCompositeButton()) {
			view.getTitleTextLabel().setText("Finalizare meniu");
			view.getContent().show(view.getContentPanel(),"insertComposite");
		}
		if(e.getSource() == view.getInsertCompositeButton()) {
			deliveryService.addItem(getCompositeProductFromFields());
			JOptionPane.showMessageDialog(view,"Meniul a fost introdus în listă!","Succes",JOptionPane.INFORMATION_MESSAGE);
			view.getMenuTable().updateTable(deliveryService.getMenuItems());
			selectedItemsList = new ArrayList<MenuItem>();
			view.getItemLabel().setText("");
			view.getCompositeTitleField().setText("");
			view.getCompositeRatingField().setText("");
			view.getFinishCompositeButton().setEnabled(false);
			showMenu();
		}
		if(e.getSource() == view.getInsertBaseButton()) {
			deliveryService.addItem(getBaseProductFromFields());
			JOptionPane.showMessageDialog(view,"Produsul a fost introdus în listă!","Succes",JOptionPane.INFORMATION_MESSAGE);
			view.getMenuTable().updateTable(deliveryService.getMenuItems());
			view.getBaseTitleField().setText("");
			view.getBaseCaloriesField().setText("");
			view.getBaseProteinField().setText("");
			view.getBaseFatField().setText("");
			view.getBaseSodiumField().setText("");
			view.getBaseRatingField().setText("");
			view.getBasePriceField().setText("");
			showMenu();
		}
		if(e.getSource() == view.getUpdateButton()) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			String title = view.getEditTitleField().getText();
			double rating = Double.parseDouble(view.getEditRatingField().getText());
			double price = Double.parseDouble(view.getEditPriceField().getText());
			
			deliveryService.editItem(getItemFromSelectedRow(selectedRow), title, rating, price);
			
			view.getMenuTable().updateTable(deliveryService.getMenuItems());
			
			view.getEditTitleField().setText("");
			view.getEditRatingField().setText("");
			view.getEditPriceField().setText("");
			showMenu();
		}
		if(e.getSource() == view.getTimeReportButton()) {
			try {
				deliveryService.generateTimeReport(view.getTimeStartField().getText(),view.getTimeEndField().getText());
				JOptionPane.showMessageDialog(view,"Raportul a fost generat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea raportului!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getItemsReportButton()) {
			try {
				deliveryService.generateItemsReport(Integer.parseInt(view.getItemCountField().getText()));
				JOptionPane.showMessageDialog(view,"Raportul a fost generat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException | IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea raportului!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getClientsReportButton()) {
			try {
				deliveryService.generateClientsReport((Integer.parseInt(view.getOrderCountField().getText())), Double.parseDouble(view.getPriceField().getText()));
				JOptionPane.showMessageDialog(view,"Raportul a fost generat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException | IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea raportului!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getDayReportButton()) {
			try {
				deliveryService.generateDayReport(new SimpleDateFormat("dd/MM/yyyy").parse(view.getDayField().getText()));
				JOptionPane.showMessageDialog(view,"Raportul a fost generat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException | ParseException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea raportului!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private MenuItem getItemFromSelectedRow(int selectedRow) {
		String title = view.getMenuTable().getTableModel().getValueAt(selectedRow, 0).toString();
		double rating = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 1);
		double price = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 2);
			
		return deliveryService.getItem(title, rating, price);
	}
	
	private void showMenu() {
		view.getTitleTextLabel().setText("Lista meniu");
		view.getContent().show(view.getContentPanel(),"menu");
	}
	
	private BaseProduct getBaseProductFromFields() {
		String title = view.getBaseTitleField().getText();
		double rating = Double.parseDouble(view.getBaseRatingField().getText());
		int calories = Integer.parseInt(view.getBaseCaloriesField().getText());
		double protein = Double.parseDouble(view.getBaseProteinField().getText());
		double fat = Double.parseDouble(view.getBaseFatField().getText());
		double sodium = Double.parseDouble(view.getBaseSodiumField().getText());
		double price = Double.parseDouble(view.getBasePriceField().getText());
		
		return new BaseProduct(title,rating,calories,protein,fat,sodium,price);
	}
	
	private CompositeProduct getCompositeProductFromFields() {
		String title = view.getCompositeTitleField().getText();
		double rating = Double.parseDouble(view.getCompositeRatingField().getText());
		
		return new CompositeProduct(title,rating,selectedItemsList);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			view.getItemDescriptionArea().setText("");
			view.getItemDescriptionArea().setText(getItemFromSelectedRow(selectedRow).getItemDescription());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
