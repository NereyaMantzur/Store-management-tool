package OOP;

@SuppressWarnings("serial")
public class ProductQuantityNotAvailableException extends OnlineStoreGeneralException {

	public ProductQuantityNotAvailableException(int thisMuch, int quantity) {
		super("ERROR: tried to add " + thisMuch + " but only " + quantity + " available\n");
	}

}
