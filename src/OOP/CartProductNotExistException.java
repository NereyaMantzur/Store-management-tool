package OOP;

@SuppressWarnings("serial")
public class CartProductNotExistException extends OnlineStoreGeneralException {

	public CartProductNotExistException(String idChoice) {
		super("ERROR: id " + idChoice + " does not exist\n");
	}

}
