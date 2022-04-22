import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GuenthersMarket {

	private static Scanner scan = new Scanner(System.in);

	private static Map<String, Double> items = new TreeMap<String, Double>();

	private static Map<String, Integer> bag = new TreeMap<String, Integer>();

	public static void main(String[] args) {
		Double totalSpent;
		String tellMeAvg = "";
		String tellMeMax = "";
		String tellMeMin = "";
		fillItemsMap();
		System.out.println("Hello, Welcome to Guenther's Market \n");

		menu(items);
		addToBag();
		totalSpent = runningTotal();

		System.out.println("Would you like to know the AVERAGE cost of your items before you leave? (y/n)");
		boolean awdy = false;
		while (!awdy) {
			try {
				tellMeAvg = scan.next();
				if (tellMeAvg.equalsIgnoreCase("y")) {
					average(totalSpent);
					awdy = true;
				} else if (tellMeAvg.equalsIgnoreCase("n")) {
					System.out.println("Average not requested");
					awdy = true;
				} else {
					throw new Exception("Invalid entry, try again enter (y/n)");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());

			}
		}

		System.out.println(
				"Would you like to know the item that cost the MOST from your receipt before you leave? (y/n)");
		boolean awdy2 = false;
		while (!awdy2) {
			try {
				tellMeMax = scan.next();
				if (tellMeMax.equalsIgnoreCase("y")) {
					max();
					awdy2 = true;
				} else if (tellMeMax.equalsIgnoreCase("n")) {
					System.out.println("Max not requested");
					awdy2 = true;
				} else {
					throw new Exception("Invalid entry, try again enter (y/n)");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		System.out.println(
				"Would you like to know the item that cost the LEAST from your receipt before you leave? (y/n)");
		boolean awdy3 = false;
		while (!awdy3) {
			try {
				tellMeMin = scan.next();
				if (tellMeMin.equalsIgnoreCase("y")) {
					least();
					awdy3 = true;
				} else if (tellMeMin.equalsIgnoreCase("n")) {
					System.out.println("Min not requested");
					awdy3 = true;
				} else {
					throw new Exception("Invalid entry, try again enter (y/n)");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		System.out.println("Thank you come again");
		scan.close();
	}

// Instantiate items<> with market offerings
	private static void fillItemsMap() {
		items.put("apple", .50);
		items.put("pear", .75);
		items.put("orange", .25);
		items.put("lime", 1.00);
		items.put("lemon", 1.00);
		items.put("granola", .50);
		items.put("gum", 1.00);
		items.put("detergent", 2.00);
		items.put("oats", 3.50);
		items.put("oil", 4.00);
	}

//Display a list of at least 8 item names and prices.
	private static void menu(Map<String, Double> items) {

		System.out.println("Here are a list of items you can buy here: ");
		Set<Entry<String, Double>> entries = items.entrySet();
		// using for loop
		for (Entry<String, Double> entry : entries) {
			System.out.println(entry.getKey() + " = $" + entry.getValue());
		}
	}

//Add items to the bag, and set quantity
	private static void addToBag() {

		String itemSelected = "";
		boolean awdy = false;
		while (!awdy) {
			System.out.println("Enter the name of the item you want to add to your bag");
			try {
				itemSelected = scan.nextLine();
				itemSelected = itemSelected.strip().toLowerCase();
				System.out.println("you chose " + itemSelected);
				if (items.containsKey(itemSelected)) {
					if (bag.get(itemSelected) == null) {
						bag.put(itemSelected, 0);
					} else {

					}
					System.out.println("Adding " + itemSelected + "@ $" + items.get(itemSelected));

					bag.put(itemSelected, bag.get(itemSelected) + 1);
					awdy = orderMore();
				} else {
					throw new Exception("Your input was invalid please enter a valid item  ");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}

	}

	// method returns t or f to continue ordering more or stop the While loop
	// containing this method.
	private static boolean orderMore() {
		String userInput = "";
		boolean orderMore = false;
		boolean awdy = false;
		while (!awdy) {
			System.out.println("Would you like to order another item? (\"yes\" or \"no\")");
			try {
				userInput = scan.nextLine();
				userInput = userInput.strip().toLowerCase();
				if (userInput.equalsIgnoreCase("no")) {
					orderMore = true;
					awdy = true;
				} else if (userInput.equalsIgnoreCase("yes")) {
					awdy = true;
				} else {
					throw new Exception("Invalid input.Try again");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		return orderMore;
		// System.out.println(orderMore);
	}

	// method gets returns the total value of the customers bag
	private static Double runningTotal() {
		Double totalSpent = 0.0;
		System.out.println("Thank you for shopping with us. Here is your reciept");
		System.out.println("Items     Quantity     Price\n" + "============================\n");
		for (Map.Entry<String, Integer> entry : bag.entrySet()) {
			System.out.format("%10s    %4s      %4s\n ", entry.getKey(), entry.getValue(), items.get(entry.getKey()));

			totalSpent += (entry.getValue() * items.get(entry.getKey()));
		}
		System.out.println("You spent a total of: " + totalSpent);
		return totalSpent;
	}

	private static void average(Double totalSpent) {
		Double averagePrice = 0.0;
		int itemsInBag = 0;

		for (Integer entry : bag.values()) {
			itemsInBag += entry;
			averagePrice = totalSpent / itemsInBag;
		}
		System.out.println("number of items in bag: " + itemsInBag);
		System.out.println("The average price of your items is: $" + averagePrice);

	}

	private static void max() {
		String mostExpItem = "";
		Double mostExpItemPrice = 0.0;
		for (Entry<String, Integer> entry : bag.entrySet()) {
			if (items.get(entry.getKey()) > mostExpItemPrice) {
				mostExpItemPrice = items.get(entry.getKey());
				mostExpItem = entry.getKey();
			} else {

			}
		}
		System.out.println("The most expensive item: " + mostExpItem + " @ $" + mostExpItemPrice);
	}

	private static void least() {
		String leastExpItem = "";
		Double leastExpItemPrice = 0.0;
		for (Entry<String, Integer> entry : bag.entrySet()) {
			if (leastExpItemPrice == 0.0) {
				leastExpItemPrice = items.get(entry.getKey());
			} else {

			}
			if (items.get(entry.getKey()) < leastExpItemPrice) {
				leastExpItemPrice = items.get(entry.getKey());
				leastExpItem = entry.getKey();
			} else {

			}
		}
		System.out.println("The least expensive item: " + leastExpItem + " @ $" + leastExpItemPrice);
	}
}