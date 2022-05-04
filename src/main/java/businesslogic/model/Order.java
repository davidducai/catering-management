package businesslogic.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	private int orderId;
	private String clientId;
	private Date orderDate;
	
	public Order() {}
	
	public Order(int orderId, String clientId, Date orderDate) {
		this.orderId = orderId;
		this.clientId = clientId;
		this.orderDate = orderDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int code = 1;
		
		code = prime * code + clientId.hashCode();
		code = prime * code + orderDate.hashCode();
		code = prime * code + orderId;
		
		return code;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}
}
