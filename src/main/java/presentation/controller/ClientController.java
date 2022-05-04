package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import businesslogic.DeliveryService;
import businesslogic.model.MenuItem;
import businesslogic.model.Order;
import presentation.view.ClientView;

public class ClientController implements ActionListener, MouseListener {

	private ClientView view;
	private DeliveryService deliveryService;
	
	private int orderId;
	private String clientId;
	private List<MenuItem> orderItems;
	
	
	public ClientController(ClientView view, DeliveryService deliveryService, String clientId) {
		this.view = view;
		this.deliveryService = deliveryService;
		
		this.orderId = this.deliveryService.getOrders().size() + 1;
		this.clientId = clientId;
		this.orderItems = new ArrayList<MenuItem>();
		
		this.view.getMenuTable().populateTable(this.deliveryService.getMenuItems());
		this.view.getItemsTable().addHeader();
		
		this.view.getExitButton().addActionListener(this);
		this.view.getSearchButton().addActionListener(this);
		this.view.getAddButton().addActionListener(this);
		this.view.getNextButton().addActionListener(this);
		this.view.getBackButton().addActionListener(this);
		this.view.getRemoveButton().addActionListener(this);
		this.view.getFinishButton().addActionListener(this);
		this.view.getNewOrderButton().addActionListener(this);
		this.view.getMenuTable().getTable().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getExitButton()) {
			view.dispose();
		}
		if(e.getSource() == view.getSearchButton()) {
			String title = "";
			double rating = -1, price = -1;
			
			if(!view.getTitleField().getText().isBlank()) {
				title = view.getTitleField().getText();
			}
			if(!view.getRatingField().getText().isBlank()) {
				rating = Double.parseDouble(view.getRatingField().getText());
			}
			if(!view.getPriceField().getText().isBlank()) {
				price = Double.parseDouble(view.getPriceField().getText());
			}
			
			view.getMenuTable().updateTable(deliveryService.searchItems(title, rating, price));
		}
		if(e.getSource() == view.getAddButton()) {
			int selectedRow = view.getMenuTable().getTable().getSelectedRow();
			
			if(selectedRow != -1) {
				String title = view.getMenuTable().getTableModel().getValueAt(selectedRow, 0).toString();
				double rating = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 1);
				double price = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 2);
				
				orderItems.add(deliveryService.getItem(title, rating, price));
				view.getItemLabel().setText(title + " adăugat ");
			}
		}
		if(e.getSource() == view.getNextButton()) {
			view.getTitleTextLabel().setText("Finalizare comandă");
			view.getContent().show(view.getContentPanel(), "items");
			
			view.getItemsTable().updateTable(orderItems);
		}
		if(e.getSource() == view.getBackButton()) {
			showMenu();
		}
		if(e.getSource() == view.getRemoveButton()) {
			int selectedRow = view.getItemsTable().getTable().getSelectedRow();
			
			if(selectedRow != -1) { // daca s-a selectat un rand
				orderItems.remove(selectedRow);
				view.getItemsTable().updateTable(orderItems);
			}
		}
		if(e.getSource() == view.getFinishButton()) {
			try {
				deliveryService.createOrder(new Order(orderId, clientId, new Date()), orderItems);
				view.getNewOrderButton().setEnabled(true);
				view.getBackButton().setEnabled(false);
				view.getRemoveButton().setEnabled(false);
				view.getFinishButton().setEnabled(false);
				JOptionPane.showMessageDialog(view,"Comanda a fost preluată!","Succes",JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea bonului comenzii!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getNewOrderButton()) {
			view.getNewOrderButton().setEnabled(false);
			view.getBackButton().setEnabled(true);
			view.getRemoveButton().setEnabled(true);
			view.getFinishButton().setEnabled(true);
			
			orderId++;
			orderItems = new ArrayList<MenuItem>();
			
			showMenu();
		}
	}

	private MenuItem getItemFromSelectedRow(int selectedRow) {
		String title = view.getMenuTable().getTableModel().getValueAt(selectedRow, 0).toString();
		double rating = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 1);
		double price = (Double) view.getMenuTable().getTableModel().getValueAt(selectedRow, 2);
			
		return deliveryService.getItem(title, rating, price);
	}
	
	private void showMenu() {
		view.getTitleTextLabel().setText("Selectare produse");
		view.getContent().show(view.getContentPanel(), "menu");
		view.getItemLabel().setText("");
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
