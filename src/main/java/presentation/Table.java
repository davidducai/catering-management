package presentation;

import java.util.Collection;
import java.util.HashSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businesslogic.model.MenuItem;

/**
 * Retine un <code>JTable</code> impreuna cu modelul acestuia.
 * Contine metode pentru popularea si actualizarea tabelului.
 * 
 * @author Ducai David
 *
 */
public class Table {

	@SuppressWarnings("serial")
	private DefaultTableModel tableModel = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	
	private JTable table = new JTable(tableModel);
	
	/**
	 * Genereaza header-ul tabelului.
	 */
	public void addHeader() {
		tableModel.addColumn("Denumire");
		tableModel.addColumn("Rating");
		tableModel.addColumn("Pret");
	}
	
	/**
	 * Populeaza tabelul cu obiectele din meniu (<code>menuItems</code>).
	 * @param items - HashSet care contine meniul
	 */
	public void populateTable(HashSet<MenuItem> items) {
		addHeader();
		
		for(MenuItem item : items) {
			tableModel.addRow(new Object[] {item.getTitle(), item.getRating(), item.computePrice()});
		}
		
		table.setModel(tableModel);
	}
	
	/** Actualizeaza tabelul cu valorile obiectelor din lista <code>items</code>. */
	public void updateTable(Collection<MenuItem> items) {
		tableModel.setRowCount(0);
		table.revalidate();
		
		for(MenuItem item : items) {
			tableModel.addRow(new Object[] {item.getTitle(), item.getRating(), item.computePrice()});
		}
		
		table.setModel(tableModel);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}
}
