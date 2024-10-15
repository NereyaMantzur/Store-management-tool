package OOP;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Store implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2359368202846107934L;

	public static enum type {
		Books, Clothing, Electronics
	}

	private final Product[] allItems;
	private Product[] myCart;
	private type productType;
	private final int MAX_PRODUCT_AMOUNT = 3;

	public Store(String[] strArr) {
		Arrays.sort(strArr);
		allItems = new Product[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			String[] temp = strArr[i].split(",");
			productType = type.valueOf(temp[0]);
			switch (productType) {
			case Books:
				allItems[i] = new Books(temp[1], Integer.parseInt(temp[2]), i + 1, temp[3], Integer.parseInt(temp[4]));
				break;
			case Clothing:
				allItems[i] = new Clothing(temp[1], Integer.parseInt(temp[2]), i + 1, temp[3], temp[4], temp[5]);
				break;
			case Electronics:
				allItems[i] = new Electronics(temp[1], Integer.parseInt(temp[2]), i + 1, temp[3], temp[4]);
				break;
			default:
				break;
			}
		}
		this.myCart = new Product[0];
	}

	public String addToCart(Product add, int thisMuch)
			throws CartProductAlreadyExistException, ProductQuantityNotAvailableException, ReachedMaxAmountException {
		if (myCart.length == MAX_PRODUCT_AMOUNT) {
			throw new ReachedMaxAmountException(MAX_PRODUCT_AMOUNT);
		}
		if (thisMuch > allItems[getProductIndex(add.getID(), allItems)].getQuantity() || thisMuch < 0) {
			throw new ProductQuantityNotAvailableException(thisMuch, add.getQuantity());
		} else if (myCart.length == 0) {
			allItems[getProductIndex(add.getID(), allItems)].reserve(thisMuch);
			myCart = Arrays.copyOf(myCart, myCart.length + 1);
			myCart[0] = productType(add);
			myCart[0].setQuantity(thisMuch);
			myCart[0].updateTime();
			return "Success\n";
		} else if (myCart.length > 0) {
			for (int i = 0; i < myCart.length; i++) {
				if (myCart[i].getID() == add.getID()) {
					if (myCart[i].getID() == add.getID()) {
						throw new CartProductAlreadyExistException(add.getName());
					}
				}
			}
			allItems[getProductIndex(add.getID(), allItems)].reserve(thisMuch);
			myCart = Arrays.copyOf(myCart, myCart.length + 1);
			myCart[myCart.length - 1] = productType(add);
			myCart[myCart.length - 1].setQuantity(thisMuch);
			myCart[myCart.length - 1].updateTime();

		}
		myCart[myCart.length - 1].updateTime();
		return "Success\n";
	}

	public Product productType(Product add) {
		if (add instanceof Books) {
			return new Books((Books) add);
		} else if (add instanceof Electronics) {
			return new Electronics((Electronics) add);
		}
		return new Clothing((Clothing) add);
	}

	public String removeFromCart(int remove) throws CartProductNotExistException {
		for (int i = 0; i < myCart.length; i++) {
			if (remove == myCart[myCart.length - 1].getID()) {
				allItems[getProductIndex(remove, allItems)]
						.setQuantity(allItems[getProductIndex(remove, allItems)].getQuantity()
								+ myCart[myCart.length - 1].getQuantity());
				myCart = Arrays.copyOf(myCart, myCart.length - 1);
				return "succses";
			} else if (remove == myCart[i].getID()) {
				allItems[getProductIndex(remove, allItems)].setQuantity(
						allItems[getProductIndex(remove, allItems)].getQuantity() + myCart[i].getQuantity());
				myCart[i] = myCart[myCart.length - 1];
				myCart = Arrays.copyOf(myCart, myCart.length - 1);
				return "succses";
			}
		}
		throw new CartProductNotExistException(""+remove);
	}

	public int getProductIndex(int idChoice, Product[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].getID() == idChoice) {
				return i;
			}
		}
		return 0;
	}

	public String updateCart(int idChoice, int thisMuch)
			throws OnlineStoreGeneralException, CartProductNotExistException {
		int index = getProductIndex(idChoice, myCart);
		if (idChoice > allItems.length || idChoice < 0) {
			throw new OnlineStoreGeneralException("\nError :" + (idChoice + 1) + "does not exists");
		}
		if (thisMuch > (allItems[idChoice].getQuantity() + myCart[index].getQuantity())) {
			throw new ProductQuantityNotAvailableException(thisMuch, (allItems[idChoice].getQuantity()));
		}
		for (int i = 0; i < myCart.length; i++) {
			if (thisMuch == 0) {
				removeFromCart(idChoice);
				return "success";
			} else if (myCart[i].getID() == idChoice) {
				getAllItems()[getProductIndex(idChoice, allItems)]
						.setQuantity(getAllItems()[getProductIndex(idChoice, allItems)].getQuantity()
								+ (getMyCart()[i].getQuantity() - thisMuch));
				getMyCart()[i].setQuantity(thisMuch);
				getMyCart()[i].updateTime();
				return "success";
			}
		}
		throw new CartProductNotExistException("" + idChoice);
	}

	public Product[] getAllItems() {
		return allItems;
	}

	public Product[] getMyCart() {
		return myCart;
	}

	public void setMyCart(Product[] emptyCart) {
		this.myCart = emptyCart;
	}

	public Product[] sort(int choice) {
		switch (choice) {
		case 1:
			Arrays.sort(allItems, new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					return o1.getClass().getSimpleName().toLowerCase()
							.compareTo(o2.getClass().getSimpleName().toLowerCase());
				}
			});
			break;
		case 2:
			Arrays.sort(allItems, new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
				}
			});
			break;
		case 3:
			Arrays.sort(allItems, new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					return o1.getQuantity() - o2.getQuantity();
				}
			});
			break;
		}
		return allItems;
	}

	public String toString(int id) {
		return String.format(myCart[id].toString() + myCart[id].getTime());
	}

}
