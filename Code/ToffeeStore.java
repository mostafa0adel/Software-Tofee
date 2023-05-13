import Controller.UserController;
import java.util.Scanner;

/**
 * This class represents the Toffee Store main class
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/12
 */
public class ToffeeStore {
    /**
     * @param args This is the main method
     * This method is used to run the program
     */
    public static void main(String[] args)
    {
        UserController userController = new UserController();
        System.out.println("Welcome to Toffee Store");
        System.out.println("We are glad to have you here");
        while (true)
        {
            System.out.println("Please choose one of the following options");
            System.out.println("1- Login");
            System.out.println("2- Register");
            System.out.println("3- View Catalog");
            System.out.println("4- Exit");
            int choice = new Scanner(System.in).nextInt();
            if(choice == 1)
            {
                userController.login();
                while (userController.checkIfUserIsLoggedIn())
                {
                    System.out.println("Please choose one of the following options");
                    System.out.println("1- View Catalog");
                    System.out.println("2- View Cart");
                    System.out.println("3- View Orders Details");
                    System.out.println("4- Add to Cart");
                    System.out.println("5- Place Order");
                    System.out.println("6- View Order Status");
                    System.out.println("7- Logout");
                    int choice2 = new Scanner(System.in).nextInt();
                    if(choice2 == 1)
                    {
                        userController.viewCatalog();
                    }
                    else if(choice2 == 2)
                    {
                        userController.displayCart();
                    }
                    else if(choice2 == 3)
                    {
                        userController.displayOrders();
                    }
                    else if(choice2 == 4)
                    {
                        userController.addToCart();
                    }
                    else if(choice2 == 5)
                    {
                        userController.makeanOrder();
                    }
                    else if(choice2 == 6)
                    {
                        userController.ShowOrderstate();
                    }
                    else if(choice2 == 7)
                    {
                        userController.logout();
                    }
                    else
                    {
                        System.out.println("Invalid Choice");
                    }
                }
            }
            else if(choice == 2)
            {
                userController.register();
            }
            else if(choice == 3)
            {
                userController.viewCatalog();
            }
            else if(choice == 4)
            {
                System.out.println("Thank you for using our store");
                System.out.println("Hope to see you again");
                System.out.println("Good Bye");
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }
}

