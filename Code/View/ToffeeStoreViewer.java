package View;

import Model.CatalogItem;
import Model.cart;
import Model.order;

import java.util.ArrayList;

/**
 * This class represents the Toffee Store Viewer
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/12
 * this class is used to display the store to the user
 */
public class ToffeeStoreViewer {
    /**
     * @param catalogItems This is the catalog items array
     * This method is used to display the catalog items
     */
    public void viewCatalog(CatalogItem[] catalogItems) {
        System.out.println("Catalog: ");
        System.out.println("--------------------------------------------------");
        int i = 0;
        // this loop iterates through the catalog items and displays the catalog items
        // if found
        while (catalogItems[i] != null) {
            System.out.println("Item name: " + catalogItems[i].getName());
            System.out.println("Item category: " + catalogItems[i].getCategory());
            System.out.println("Item description: " + catalogItems[i].getDescription());
            System.out.println("Item id: " + catalogItems[i].getId());
            System.out.println("Item brand: " + catalogItems[i].getBrand());
            System.out.println("Item price: " + catalogItems[i].getPrice());
            System.out.println("Item discount percentage: " + catalogItems[i].getDiscountPercentage() + "%");
            if (catalogItems[i].getId() % 2 == 0) {
                System.out.println("In Stock: " + catalogItems[i].getSealeditemsStock() + " units");
            } else {
                System.out.println("In Stock: " + catalogItems[i].getLooseitemsStock() + " kg");
            }
            System.out.println("--------------------------------------------------");
            i++;
        }
    }
    
    /**
     * @param Order This is the user order
     * This method is used to display the user order items
     */
    public void displayOrderItems(order Order)
    {
        // check if the user has items in the order
        if (Order.getOrderItems().containsKey(Order.getOrderID())) {
            // get the user order items
            ArrayList<CatalogItem> items = Order.getOrderItems().get(Order.getOrderID());
            // loop through the user order items
            System.out.println("--------------------------------------------------");
            System.out.println("Order ID: " + Order.getOrderID());
            System.out.println("Order owner name: " + Order.getOrderOwnerName());
            System.out.println("shipping address: " + Order.getShippingAddress());
            System.out.println("payment method: " + "Cash on delivery");
            System.out.println("--------------------------------------------------");
            for (CatalogItem catalogItem : items) {
                System.out.println("Item name: " + catalogItem.getName());
                System.out.println("Item price: " + catalogItem.getPrice());
                System.out.println("Item quantity: " + Order.getOrderItemQuantity().get(catalogItem.getId()));
                System.out.println("--------------------------------------------------");
            }
            System.out.println("Total price: " + Order.getTotalPrice());
        } else {
            System.out.println("You haven't ordered any items yet!");
        }

    }
    
    /**
     * @param Order This is the user order
     * This method is used to display the user order state
     */
    public void ViewOrderState(order Order)
    {
        if (Order.getOrderstat() == null) {
            System.out.println("Your order with ID : " + Order.getOrderID() + " is not found");
        } else {
            System.out.println("Your order with ID : " + Order.getOrderID() + " is " + Order.getOrderstat().status);
        }
    }
    /**
     * @param userCart This is the user cart
     * @param email This is the user email
     * This method is used to display the user cart items
     */
    public void viewCart(cart userCart,String email)
    {
        if (userCart.getCartItems().containsKey(email)) {
            System.out.println("Cart items: ");
            System.out.println("--------------------------------------------------");
            // this loop iterates through the cart items and displays the cart items
            // if found
            for (CatalogItem catalogItem : userCart.getCartItems().get(email)) {
                System.out.println("Item name: " + catalogItem.getName());
                System.out.println("Item category: " + catalogItem.getCategory());
                System.out.println("Item description: " + catalogItem.getDescription());
                System.out.println("Item id: " + catalogItem.getId());
                System.out.println("Item brand: " + catalogItem.getBrand());
                System.out.println("Item price: " + catalogItem.getPrice());
                System.out.println("Item discount percentage: " + catalogItem.getDiscountPercentage()+"%");
                System.out.println("Item quantity: " + userCart.getCartItemQuantity().get(catalogItem.getId()));
                System.out.println("--------------------------------------------------");

            }
            System.out.println("Total price: " + userCart.getTotalPrice());
        } else {
            System.out.println("No items in Your cart!");
        }

    }
}
