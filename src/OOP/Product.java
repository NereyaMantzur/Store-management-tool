package OOP;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Product implements Reservable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579477451619656656L;
	private final int ID;
	private final String NAME;
	private int quantity;
	private String time;
	SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	public Product(String productName, int quantity, int id) {
		this.ID = id;
		this.NAME = productName;
		this.quantity = quantity;
	}

	public void updateTime() {
		Date d = new Date(System.currentTimeMillis());
		this.time = jdf.format(d);
	}

	public String showProduct() {
		if (quantity > 0) {
			return toString();
		} else if (quantity == 0) {
			return "Item Not avaiable";
		}
		return "";
	}

	public void addQuantity(int add) {
		this.quantity = quantity + add;
	}

	@Override
	public void reserve(int quantity) throws ReachedMaxAmountException {
		this.quantity -= quantity;
	}

	public String getName() {
		return NAME;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}

	public int getID() {
		return ID;
	}

	public String getTime() {
		return time;
	}

	@Override
	public abstract String toString();

}
