package OOP;

public class Clothing extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7954252487926315947L;
	private String size;
	private String color;
	private String gender;

	public enum genders {
		M, F
	}

	public Clothing(String name, int quantity, int id, String size, String color, String gender) {
		super(name, quantity, id);
		this.size = size;
		this.color = color;
		switch (genders.valueOf(gender)) {
		case F:
			this.gender = "Female";
			break;
		case M:
			this.gender = "Male";
			break;
		default:
			break;
		}
	}

	public Clothing(Clothing other) {
		this(other.getName(), other.getQuantity(), other.getID(), other.size, other.color);
		this.gender = other.gender;
	}

	public Clothing(String name, int quantity, int id, String size, String color) {
		super(name, quantity, id);
		this.size = size;
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("%2d |%-38s |%-7d |%-20s |%-19s |%s", super.getID(),
				super.getName(), super.getQuantity(), size, color, gender));
		if (super.getTime() != null) {
			return sb.append(" |" + getTime()).toString();
		}
		return sb.toString();
	}

}
