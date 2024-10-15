package OOP;

public interface Reservable {
	public void reserve(int quantity) throws ReachedMaxAmountException;
}
