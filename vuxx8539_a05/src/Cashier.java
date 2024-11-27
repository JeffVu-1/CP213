package cp213;

import java.awt.RenderingHints.Key;
import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
    
    printCommands();

    Scanner scanner = new Scanner(System.in);
    Order order = new Order();

    while (true) {
        System.out.print("Command: ");
        int commandValue = scanner.nextInt();
        scanner.nextLine();

        if (commandValue == 0) {
            break;
        } else if (commandValue >= 1 && commandValue <= 7) {
            MenuItem selectedItem = menu.getItem(commandValue-1);
            int quantity = askForQuantity(scanner);
            if (selectedItem != null && quantity > 0) {
                order.add(selectedItem, quantity);
            } else {
                System.out.println("Invalid selection or quantity");
            }
        } else {
            System.out.println("Not a valid menu item");
        }
        printCommands();
    }

    System.out.println("----------------------------------------");
    System.out.println("Receipt");
    System.out.println(order);
    return order;
    }
}