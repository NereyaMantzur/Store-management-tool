package OOP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//Store store = new Store(getProductsFromFile("products_list.csv"));
		String filename = "data.dat";
		Store store = readFromBinaryFile(filename);
		showMenu(store);
		writeToBinaryFile(filename, store);
	}

	private static Store readFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream(filename));
		Store s;
		s = (Store) f.readObject();
		f.close();
		return s;
	}

	private static void writeToBinaryFile(String filename, Store store) throws IOException {
		ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(filename));
		f.writeObject(store);
		f.close();
	}

	public static String[] getProductsFromFile(String path) throws FileNotFoundException {
		File f = new File(path);
		Scanner s = new Scanner(f);
		String line = s.nextLine();
		String[] strArr = new String[1];
		while (s.hasNextLine()) {
			line = s.nextLine();
			strArr[strArr.length - 1] = line;

			if (s.hasNextLine()) {
				strArr = Arrays.copyOf(strArr, strArr.length + 1);
			}
		}
		;
		s.close();
		return strArr;
	}

	private static String menu() {
		return "============menu=============\n" + "1| Show Product repository\n" + "2| Show shopping cart\n"
				+ "3| Add new product\n" + "4| Update quantity in cart\n" + "5| Remove Product\n"
				+ "6| Select sortion\n" + "7| Exit";
	}

	private static void showMenu(Store store) {
		Scanner s = new Scanner(System.in);
		int choice;
		do {
			System.out.println(menu());
			System.out.print("please enter your choice: ");
			choice = s.nextInt();
			switch (choice) {
			case 1: {
				showProducts(store);
				break;
			}
			case 2: {
				showCart(store);
				break;
			}
			case 3: {
				addProduct(store, s);
				break;
			}
			case 4: {
				updateCart(store, s);
				break;
			}
			case 5: {
				removeProduct(store, s);
				break;
			}
			case 6: {
				sortProduct(store, s);
				break;
			}
			case 7: {
				System.out.println("Saved\n" + "Bye Bye...");
				s.close();
				break;
			}
			}
		} while (choice != 7);

	}

	private static void sortProduct(Store store, Scanner s) {
		System.out.println("Select your sortion: \n" + "• for sortion by category press 1\n"
				+ "• for sortion by product name prees 2\n" + "• for sortion by avaiablitiy press 3\n");
		System.out.print("Please enter your choice: ");
		int choice = s.nextInt();
		store.sort(choice);
		showProducts(store);
	}

	public static void removeProduct(Store store, Scanner s) {
		System.out.println("What product would you like to remove? (ID)");
		int remove = s.nextInt();
		try {
			System.out.println(store.removeFromCart(remove));
		} catch (CartProductNotExistException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void updateCart(Store store, Scanner s) {
		try {
			if (store.getMyCart().length == 0) {
				System.out.println("No item in cart \n");
			} else {
				System.out.printf("%-3s|%-39s|%s\n", "ID", "Product", "Quantity");
				for (int i = 0; i < store.getMyCart().length; i++) {
					System.out.println(store.getMyCart()[i].toString());
				}
				System.out.print("What item you want to update in your cart?:");
				int idChoice = s.nextInt();
				System.out.print("How much do you want?");
				int thisMuch = s.nextInt();
				System.out.println(store.updateCart(idChoice, thisMuch));
			}
		} catch (CartProductNotExistException e) {
			System.err.println(e.getMessage());
		} catch (OnlineStoreGeneralException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void addProduct(Store store, Scanner s) {
		try {
			System.out.print("What item do you want? (ID): ");
			int idChoice = s.nextInt() - 1;
			if (idChoice > store.getAllItems().length) {
				throw new OnlineStoreGeneralException("\nError :ID - " + (idChoice + 1) + " does not exists");
			}
			System.out.printf("%2s |%-38s |%-8s\n", "ID", "Name", "Quantity");
			for (int i = 0; i < store.getAllItems().length; i++) {
				if (idChoice == store.getAllItems()[i].getID()) {
					System.out.printf(
							store.getAllItems()[store.getProductIndex(idChoice + 1, store.getAllItems())].toString());
					break;
				}
			}
			System.out.print("\nHow much do you want?: ");
			int thisMuch = s.nextInt();
			System.out.println(store.addToCart(
					store.getAllItems()[store.getProductIndex(idChoice + 1, store.getAllItems())], thisMuch));
		} catch (ProductQuantityNotAvailableException | CartProductAlreadyExistException e) {
			System.err.println(e.getMessage());
		} catch (OnlineStoreGeneralException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void showCart(Store store) {
		if (store.getMyCart().length == 0 || store.getMyCart() == null) {
			System.out.println("No item in the cart \n");
		} else {
			System.out.printf("%2s |%-38s |%-56s |%s\n", "ID", "Name", "Quantity", "Time");
			for (int i = 0; i < store.getMyCart().length; i++) {
				System.out.printf(store.getMyCart()[i].toString() + "\n");
			}
		}
		System.out.println();
	}

	public static void showProducts(Store store) {
		System.out.printf("%2s |%-38s |%s\n", "ID", "Name", "Quantity");
		for (int i = 0; i < store.getAllItems().length; i++) {
			if (store.getAllItems()[i] == null) {
				break;
			} else if (store.getAllItems()[i].getQuantity() == 0) {
				i++;
			}
			System.out.printf(store.getAllItems()[i].toString() + "\n");
		}
		System.out.println();
	}
}
