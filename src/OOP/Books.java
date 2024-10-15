package OOP;


public class Books extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6389950579814963868L;
	private String writer;
	private int pages;

	public Books(String name, int quantity, int id, String writer, int pages) {
		super(name, quantity, id);
		this.writer = writer;
		this.pages = pages;
	}

	public Books(Books other) {
		this(other.getName(), other.getQuantity(), other.getID(), other.writer, other.pages);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("%2d |%-38s |%-7d |%-20s |%-25d", super.getID(),
				super.getName(), super.getQuantity(), writer, pages));
		if (super.getTime() != null) {
			return sb.append(" |" + getTime()).toString();
		}
		return sb.toString();
	}

	
}
