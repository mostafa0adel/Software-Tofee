package Controller;

import Model.CatalogItem;
import Model.cart;
import View.ToffeeStoreViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the controller for the cart
 * It is responsible for adding items to the cart, displaying the cart, and clearing the cart
 * It also saves the cart to a file
 * It also loads the cart from a file
 */
public class CartController {
    /**
     * This is the user cart
     */
    public cart UserCart;
    /**
     * This is the catalog controller
     */
    CatalogController catalogController = new CatalogController();
    /**
     * This is the toffee store viewer
     */
    ToffeeStoreViewer toffeeStoreViewer = new ToffeeStoreViewer();

    /**
     * @param email the user email
     */
    public CartController(String email)
    {
        UserCart = new cart(email);
    }
    /**
     * Default constructor
     */
    public CartController()
    {
    }

    /**
     * @param item the catalog item to be added to the cart
     * @param quantity the quantity of the item to be added to the cart
     * @param email the user email
     * This method adds an item to the cart
     */
    public void addToCart(CatalogItem item, double quantity, String email)
    {
        UserCart.addItemToCart(item, quantity, email);
        saveCart(email);
    }
    
    /**
     * @param email the user email
     * This method displays the cart
     */
    public void displayCart(String email)
    {
        toffeeStoreViewer.viewCart(UserCart, email);
    }


    /**
     * @param email the user email
     * This method loads the user cart from a file
     */
    public void loadCartItems(String email) {
        try {
            // create a new file
            File file = new File("Cart.txt");
            // create a new scanner
            Scanner sc = new Scanner(file);
            // loop through the file lines
            while (sc.hasNextLine()) {
                // get the line
                String line = sc.nextLine();
                // split the line by comma
                String[] lineArray = line.split(",");
                // check if the user email is equal to the email in the file
                if (lineArray[0].equals(email)) {
                    // get the catalog item by id
                    CatalogItem catalogItem = catalogController.getCatalogItem(Integer.parseInt(lineArray[1]));
                    // check if the cart items hashmap contains the user email
                    ArrayList<CatalogItem> items;
                    if (UserCart.getCartItems().containsKey(email)) {
                        items = UserCart.getCartItems().get(email);
                    } else {
                        // create a new arraylist to store the user cart items
                        items = new ArrayList<>();

                    }
                    items.add(catalogItem);
                    UserCart.setTotalPrice(UserCart.getTotalPrice() + (catalogItem.getPrice()
                            - (catalogItem.getPrice() * catalogItem.getDiscountPercentage() / 100))
                            * Double.parseDouble(lineArray[2]));

                    UserCart.getCartItemQuantity().put(catalogItem.getId(), Double.parseDouble(lineArray[2]));
                    UserCart.getCartItems().put(email, items);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cart file not found!");
        }
    }
    
    /**
     * @param email the user email
     * This method saves the user cart to a file
     */
    public void saveCart(String email) {
        System.out.println("Saving cart...");
        List<String> fileContent = new ArrayList<>();
        ArrayList<CatalogItem> itms = UserCart.getCartItems().get(email);
        File file = new File("Cart.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // split the line by comma
                String[] lineArray = line.split(",");
                if (lineArray[0].equals(email)) {
                    // get the catalog item by id
                    CatalogItem catalogItem = catalogController.getCatalogItem(Integer.parseInt(lineArray[1]));
                    // check if the cart items hashmap contains the user email
                    if (UserCart.getCartItems().containsKey(email)) {
                        // get the user cart items
                        for (CatalogItem itm : itms) {
                            if (itm.getId() == Integer.parseInt(lineArray[1])) {
                                lineArray[2] = String.valueOf(UserCart.getCartItemQuantity().get(catalogItem.getId()));
                                fileContent.add(email + "," + catalogItem.getId() + "," + lineArray[2]);
                                itms.remove(itm);
                                break;
                            }
                        }
                    }
                } else {
                    fileContent.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cart file not found!");
        }
        for (CatalogItem catalogItem : itms) {
            fileContent.add(email + "," + catalogItem.getId() + "," + UserCart.getCartItemQuantity().get(catalogItem.getId()));
        }
        try {
            FileWriter writer = new FileWriter("Cart.txt");
            for (String line : fileContent) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
            UserCart.getCartItems().remove(email);
            UserCart.setTotalPrice(0);
            loadCartItems(email);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
