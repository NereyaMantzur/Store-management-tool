package OOP;

@SuppressWarnings("serial")
public class ReachedMaxAmountException extends OnlineStoreGeneralException {

	public ReachedMaxAmountException(int maxProduct) {
		super("\nthe maximum amount allowed to reserve is " + maxProduct + "\n");
	}

}
