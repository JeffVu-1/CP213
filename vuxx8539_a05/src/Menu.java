package cp213;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Jeff Vu
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Menu {

    // Attributes.

    // define a List of MenuItem objects
    // Note that this must be a *List* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

    // your code here
    private List<MenuItem> items;

    /**
     * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
     * into the Menu List.
     *
     * @param items an existing List of MenuItems.
     */
    public Menu(List<MenuItem> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {
        items = new ArrayList<>();
    
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] parts = line.split(" ", 2);

            if(parts.length == 2){
                try{
                    BigDecimal price = new BigDecimal(parts[0]);
                    String itemName = parts[1];
                    MenuItem menuItem = new MenuItem(itemName, price);
                    items.add(menuItem);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                }

            }else {
                System.out.println("Invalid line format: " + line);
            }
        }
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
	return this.items.get(i);
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {
        int Size = this.items.size();
	return Size;
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
        StringBuilder MenuBuilder = new StringBuilder();
        int Menulength = this.items.size();
        
        for(int i = 0; i < Menulength; ++i){
            MenuItem item = this.items.get(i);
            String formattedItem = String.format("%d) %-12s $%5.2f", i+1, item.getListing(), item.getPrice());
            MenuBuilder.append(formattedItem).append("\n");
        }

        return MenuBuilder.toString();
    }
}