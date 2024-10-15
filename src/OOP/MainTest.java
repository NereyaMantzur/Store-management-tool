package OOP;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void firstChoice() throws FileNotFoundException {
		Store store = new Store(Main.getProductsFromFile("products_list.csv"));
		assertTrue(store.getAllItems()[0] instanceof Product);
	}

	@Test
	void secondChoice() throws FileNotFoundException, CartProductAlreadyExistException, ProductQuantityNotAvailableException, ReachedMaxAmountException {
		Store store = new Store(Main.getProductsFromFile("products_list.csv"));
		store.addToCart(store.getAllItems()[15], 5);
		assertTrue(store.getMyCart()[0].getQuantity() == 5);
	}

	@Test
	void thirdChoice() throws FileNotFoundException, CartProductAlreadyExistException, ProductQuantityNotAvailableException, ReachedMaxAmountException {
		Store store = new Store(Main.getProductsFromFile("products_list.csv"));
		int idChoice = 12;
		int index = idChoice - 1;
		store.addToCart(store.getAllItems()[index], 5);
		assertTrue(store.getAllItems()[index].getQuantity() == 25);
	}

	@Test
	void fourthChoice() throws FileNotFoundException, CartProductNotExistException, OnlineStoreGeneralException {
		Store store = new Store(Main.getProductsFromFile("products_list.csv"));
		int idChoice = 23;
		int index = idChoice - 1;
		store.addToCart(store.getAllItems()[index], 5);
		store.updateCart(idChoice, 2);
		assertTrue(store.getMyCart()[0].getQuantity() == 2);
	}

	@Test
	void fifthChoice() throws FileNotFoundException, CartProductAlreadyExistException, ProductQuantityNotAvailableException, ReachedMaxAmountException, CartProductNotExistException {
		Store store = new Store(Main.getProductsFromFile("products_list.csv"));
		int idChoice = 29;
		int index = idChoice - 1;
		store.addToCart(store.getAllItems()[index], 5);
		store.removeFromCart(idChoice);
		assertTrue(store.getMyCart().length == 0);
	}
}
