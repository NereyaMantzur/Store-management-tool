package OOP;

public class Electronics extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3639692027286016582L;
	private String brand;
	private String model;

	public Electronics(String name, int quantity, int id, String brand, String model) {
		super(name, quantity, id);
		this.brand = brand;
		this.model = model;
	}

	public Electronics(Electronics other) {
		this(other.getName(), other.getQuantity(), other.getID(), other.brand, other.model);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("%2d |%-38s |%-7d |%-20s |%-25s", super.getID(),
				super.getName(), super.getQuantity(), brand, model));
		if (super.getTime() != null) {
			return sb.append(" |" + getTime()).toString();
		}
		return sb.toString();
	}

	@Override
	public void reserve(int quantity) throws ReachedMaxAmountException {
		if (quantity <= 3 && quantity > 0) {
			super.setQuantity(super.getQuantity() - quantity);
		}else {
			throw new ReachedMaxAmountException(3);
		}
	}
}
