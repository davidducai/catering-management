package data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import businesslogic.model.MenuItem;
import businesslogic.model.Order;

/**
 * Contine metode pentru generarea de bonuri(pentru comenzi) si rapoarte.
 * 
 * @author Ducai David
 *
 */
public class FileGenerator {

	/** Genereaza un nume pentru un bon in functie de ID-ul comenzii. */
	private static String generateBillName(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append("order");
		sb.append(order.getOrderId());
		sb.append(".txt");
		return sb.toString();
	}
	
	/** Genereaza bonul pentru comanda. */
	public static void generateBill(Order order, List<MenuItem> items) throws IOException {
		String billName = generateBillName(order);
		File bill = new File(billName);
		bill.createNewFile();
		
		PrintWriter writer = new PrintWriter(bill);
		
		String delimiter = "============================================================================================";
		String format = "%-80s %10s\n";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		double total = 0;
		
		writer.println("Cod client: " + order.getClientId());
		writer.println("Cod comanda: " + order.getOrderId());
		writer.println("Data si ora: " + dateFormat.format(order.getOrderDate()) + "\n");
		writer.println(delimiter);
		writer.format(format,"Denumire produs","Pret unitar");
		writer.println(delimiter + "\n");
		for(MenuItem item : items) {
			double price = item.computePrice();
			writer.format(format, item.getTitle(), price);
			total += price;
		}
		writer.print("\n\n\n\n\n\n");
		writer.println(delimiter);
		writer.println("TOTAL: " + total);
		writer.println(delimiter);
		writer.flush();
		writer.close();
	}
	
	/** Genereaza un nume pentru un raport in functie de data si ora la care se genereaza. */
	private static String generateReportName() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		sb.append("report");
		sb.append(formatter.format(new Date()));
		sb.append(".txt");
		return sb.toString();
	}
	
	public static void generateTimeReport(String startTime, String endTime, Map<Order,List<MenuItem>> orders) throws IOException {
		String reportName = generateReportName();
		String reportTitle = "Comenzi preluate in intervalul orar: " + startTime + " - " + endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		File report = new File(reportName);
		
		report.createNewFile();
		
		PrintWriter writer = new PrintWriter(report);
		
		writer.println(reportTitle); 
		orders.entrySet()
			.forEach(order -> writer.print("Cod: " + order.getKey().getOrderId() + " - Client: " + order.getKey().getClientId() + " - Data: " + formatter.format(order.getKey().getOrderDate())));

		writer.flush();
		writer.close();
	}
	
	public static void generateItemsReport(int numberOfTimes, List<MenuItem> items) throws IOException {
		String reportName = generateReportName();
		String reportTitle = "Produse comandate mai mult de " + numberOfTimes + " ori:";
		File report = new File(reportName);
		
		report.createNewFile();
		
		PrintWriter writer = new PrintWriter(report);
		
		writer.println(reportTitle);
		for(MenuItem item : items) {
			writer.println(item.getTitle());
		}
		
		writer.flush();
		writer.close();
	}
	
	public static void generateClientsReport(int numberOfTimes, double price, List<String> clientIdList) throws IOException {
		String reportName = generateReportName();
		String reportTitle = "Clienții care au comandat mai mult de " + numberOfTimes + " ori:";
		File report = new File(reportName);
		
		report.createNewFile();
		
		PrintWriter writer = new PrintWriter(report);
		
		writer.println(reportTitle);
		for(String clientId : clientIdList) {
			writer.println(clientId);
		}
		
		writer.flush();
		writer.close();
	}
	
	public static void generateDayReport(String dateString, Map<MenuItem, Long> items) throws IOException {
		String reportName = generateReportName();
		String reportTitle = "Produse comandate in data de " + dateString;
		File report = new File(reportName);
		
		report.createNewFile();
		
		PrintWriter writer = new PrintWriter(report);
		
		writer.println(reportTitle);
		
		items.entrySet().forEach(item -> writer.println(item.getKey().getTitle() + " - " +item.getValue()));
		
		writer.flush();
		writer.close();
	}
}
