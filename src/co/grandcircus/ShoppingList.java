package co.grandcircus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ShoppingList {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean stillShopping = true;
		System.out.println("Welcome to Guenther's Market!");
		addSpacing(2);
		List<String> marketItems = createMarketItems();
		List<Double> itemPrice = createItemPrice();
		List<String> itemsInCart = new ArrayList<>();
		List<Double> pricesInCart =  new ArrayList<>();
		
		HashMap<String, Double> shoppingItems = new HashMap<>();
		
		shoppingItems = createHashMap(marketItems, itemPrice);

		while (stillShopping == true) {
			displayMenu(marketItems, itemPrice);
			addSpacing(2);
			String userInput = Validator.getString(scnr, "What item would you like to order?  ");
			userInput = checkUserInput(userInput, marketItems);
			
			addToCart(userInput, itemsInCart, pricesInCart, marketItems, itemPrice);
			
			stillShopping = continueShopping(scnr, "Would you like to order anything else (y/n)? ");
			addSpacing(1);

		}
		printCart(itemsInCart, pricesInCart);
		System.out.println(maxItem(itemsInCart, pricesInCart));
		System.out.println(minItem(itemsInCart, pricesInCart));

		scnr.close();

	}

	public static void displayMenu(List<String> items, List<Double> price) {
		System.out.printf("%-20s Price \n", "Item");
		System.out.println("==================================");
		// print out string array here
		// use for loop
		int counter = 0;
		for (String item : items) {
			// double itemPrice = (double)price[items.indexOf(item)];
			System.out.printf("%d %-20s %-20s \n", counter+1, item, price.get(counter));
			counter++;

		}

	}

	public static List<String> createMarketItems() {
		List<String> marketItems = new ArrayList<>();
		marketItems.add("apple");
		marketItems.add("banana");
		marketItems.add("cauliflower");
		marketItems.add("dragonfruit");
		marketItems.add("Elderberry");
		marketItems.add("figs");
		marketItems.add("grapefruit");
		marketItems.add("honeydew");

		return marketItems;

	}

	public static List<Double> createItemPrice() {
		List<Double> itemPrice = new ArrayList<>();
		itemPrice.add(0.99);
		itemPrice.add(0.59);
		itemPrice.add(1.59);
		itemPrice.add(2.19);
		itemPrice.add(1.79);
		itemPrice.add(2.09);
		itemPrice.add(1.99);
		itemPrice.add(3.49);

		return itemPrice;
	}

	public static boolean checkMenu(String userOrder, List<String> arr, List<Double> price) {
		if (arr.contains(userOrder)) {
			System.out.println("Adding " + userOrder + " to cart at " + price.get(arr.indexOf(userOrder)));
			addSpacing(1);
			return true;
		} else {
			return false;
		}

	}
	
	public static HashMap<String, Double> createHashMap(List<String> arr, List<Double> price){

		HashMap<String, Double> map = new HashMap<>();
		for(int i = 0; i < arr.size(); i++) {
			map.put(arr.get(i), price.get(i));
		}
		return map;
	}
	
	public static String checkUserInput(String userInput, List<String> arr) {
		try {
		int num = Integer.parseInt(userInput);
		return arr.get(num - 1);
		}catch (Exception e) {
			return userInput;
		}
	}
	
	public static void addToCart(String userOrder, List<String> cartItems, List<Double> cartPrice, List<String> items, List<Double> price) {
		if(checkMenu(userOrder, items, price)) {
			cartItems.add(userOrder);
			cartPrice.add(price.get(items.indexOf(userOrder)));
		}else {
			System.out.println("Sorry, we dont' have those. Please try again. ");
			addSpacing(1);
		}
		
	}
	
	public static void printCart(List<String> cartItems, List<Double> cartPrice) {
		double sum = 0;
		System.out.println("Thanks for your order!");
		System.out.println("Here's what you got:");
		for(int i = 0; i < cartItems.size(); i++) {
			System.out.printf("%-15s %-15s \n", cartItems.get(i), cartPrice.get(i));
			sum += cartPrice.get(i);
		}
		
		System.out.print("Average price per item in order was ");
		System.out.printf("%.2f \n", + sum/cartPrice.size());
	}

	public static boolean continueShopping(Scanner scnr, String promp) {
		System.out.print(promp);
		String choice = scnr.next();
		if (choice.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String maxItem(List<String> cartItems, List<Double> cartPrice) {
		double max = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			if(max > cartPrice.get(i)) {
				continue;
			}else {
				max = cartPrice.get(i);
			}
		}
		int indexOfMax = cartPrice.indexOf(max);
		return "The most expensive item is " + cartItems.get(indexOfMax) + " at a price of " + cartPrice.get(indexOfMax) + ".";
	}
	
	public static String minItem(List<String> cartItems, List<Double> cartPrice) {
		double min = 5;
		for (int i = 0; i < cartItems.size(); i++) {
			if(min < cartPrice.get(i)) {
				continue;
			}else {
				min = cartPrice.get(i);
			}
		}
		int indexOfMin = cartPrice.indexOf(min);
		return "The lease expensive item is " + cartItems.get(indexOfMin) + " at a price of " + cartPrice.get(indexOfMin) + ".";
	}

	public static void addSpacing(int num) {
		// add spacing via recursion
		if (num > 0) {
			System.out.println("");
			addSpacing(num - 1);
		}
	}

}
