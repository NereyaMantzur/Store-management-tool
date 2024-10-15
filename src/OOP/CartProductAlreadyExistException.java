package OOP;

@SuppressWarnings("serial")
public class CartProductAlreadyExistException extends OnlineStoreGeneralException {

	public CartProductAlreadyExistException(String name) {
		super("ERROR: the product "+name+" already exists in the cart\n");
	}

}
