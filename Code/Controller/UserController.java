package Controller;
import  Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the controller for the user
 * It is responsible for logging in and registering the user
 * It is also responsible for make the user logout,add to cart, display cart, display orders, and display catalog
 * It also saves the user to a file
 * It also loads the user from a file
 * It also loads the user voucher code
 * It also saves the user voucher code
 * 
 */
public class UserController {
    /**
     * user This is the user
     */
    User user = new User();
    /**
     * cartController This is the cart controller
     */
    CartController cartController;
    /**
     * catalogController This is the catalog controller
     */
    CatalogController catalogController = new CatalogController();
    /**
     * orderController This is the order controller
     */
    OrderController orderController;
    
    /**
     * This method makes the user login
     */
    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email address: ");
        String email = sc.nextLine();
        while (user.getUser(email) == null) {
            System.out.println("User not exists, try again!");
            System.out.println("Enter your email address: ");
            email = sc.nextLine();
        }
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        if (user.checkUserPassword(email, password)) {
            user.login(email, password);
        }
        cartController = new CartController(email);
        cartController.loadCartItems(email);
        orderController = new OrderController(email);
        loadUserVoucherCode(email);
    }
    /**
     * This method makes the user register
     */
    public void register(){
        OTPSender otpSender = new OTPSender();
        String from = "tofee.store@gmail.com";
        String subject = "Toffee Store OTP Verification";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email address: ");
        String email = sc.nextLine();
        // Check email address validity
        while (!email.matches("^[a-zA-Z0-9]+[_.-]?[a-zA-Z0-9]+@[a-zA-Z0-9]+-?[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,}+$")) {
            System.out.println("Invalid email address, try again!");
            System.out.println("Enter your email address: ");
            email = sc.nextLine();
        }
        // Check if the user already exists
        while (user.getUser(email) != null) {
            System.out.println("User already exists, try again!");
            System.out.println("Enter your email address: ");
            email = sc.nextLine();
        }
        System.out.println("Enter your password: ");
        System.out.println(
                "Make sure the password is at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character!");
        String password = sc.nextLine();
        // check password strength
        while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            System.out.println(
                    "Password must contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character!");
            System.out.println("Enter your password: ");
            password = sc.nextLine();
        }
        System.out.println("Enter your address: ");
        String address = sc.nextLine();
        int otp = OTPSender.generateOTP();
        String text = "Welcome to Toffee Store!"+
                "\n"+ "Your OTP is: " + otp +
                "\n" + "Please enter this OTP to verify your account.";
        System.out.println("Sending OTP...to your email....");
        System.out.println("Please wait...it may take a few seconds...");
        boolean b = otpSender.sendEmail(email, from, subject, text);
        if (b) {
            System.out.println("OTP sent successfully to your email!");
        } else {
            System.out.println("There is problem in sending OTP to your email!");
        }
        System.out.println("Enter your OTP: ");
        int enteredOtp = sc.nextInt();
        while (enteredOtp != otp) {
            System.out.println("You have entered the wrong OTP!");
            System.out.println("Enter your OTP: ");
            enteredOtp = sc.nextInt();
        }
            user.register(email, password, address);
            user.saveUser();
            System.out.println("You have successfully registered!");
    }
    /**
     * This method is responsible for displaying the catalog
     */
    public void displayCart(){
        cartController.displayCart(user.getEmail());
    }
    /**
     *  This method is responsible for make order from the cart or the catalog
     */
  
    public void makeanOrder() {
        System.out.println("If you want to buy  items in your cart, please enter 1 else enter 0");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            orderController.placeOrderfromCart(cartController.UserCart, user.getEmail());
            return;
        }
        System.out.println("Enter the item id");
        int id = sc.nextInt();
        if(id<101 || id>=120){
            System.out.println("Invalid item id");
            return;
        }
        if (catalogController.getCatalogItem(id).getSealeditemsStock() == 0
                && catalogController.getCatalogItem(id).getLooseitemsStock() == 0) {
            System.out.println("Sorry, this item is out of stock");
            return;
        }
        System.out.println("Enter the item quantity");
        if (id % 2 == 0) {
            int units = sc.nextInt();
            while (units > catalogController.getCatalogItem(id).getSealeditemsStock()) {
                System.out.println("Sorry, this quantity is not available");
                System.out.println("Enter the item quantity");
                units = sc.nextInt();
            }
            catalogController.getCatalogItem(id)
                    .setSealedStock(catalogController.getCatalogItem(id).getSealeditemsStock() - units);
            orderController.makeOrderFromCatalog(user.getEmail(), id, units);
        } else {
            double weight = sc.nextDouble();
            while (weight > catalogController.getCatalogItem(id).getLooseitemsStock()) {
                System.out.println("Sorry, this quantity is not available");
                System.out.println("Enter the item quantity");
                weight = sc.nextDouble();
            }
            catalogController.getCatalogItem(id)
                    .setLooseStock(catalogController.getCatalogItem(id).getLooseitemsStock() - weight);
            orderController.makeOrderFromCatalog(user.getEmail(), id, weight);
        }
    }
    /**
     * This method is responsible for displaying the orders
     */
    public void displayOrders(){

        orderController.displayUserorder();
    }
    /**
     * This method is responsible for displaying the order state
     */
    public void ShowOrderstate(){
        orderController.ViewOrderstatues();
    }
    /**
     *This method is responsible for make the user logout
     */
    public void logout(){
        user.logout();
    }
    
    /**
     * @param email the email of the user
     *  This method is responsible for loading the user voucher code
     */
    public static void loadUserVoucherCode(String email)
    {
        File file = new File("UserVoucherCode_loyaltyPoints.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                if (tokens[0].equals(email) && tokens.length == 2) {
                    User.setLoyaltyPoints(Integer.parseInt(tokens[1]));
                }
                else if(tokens[0].equals(email) && tokens.length > 2)
                {
                    User.setLoyaltyPoints(Integer.parseInt(tokens[1]));
                    User.setVoucherCodes(new ArrayList<>(Arrays.asList(tokens).subList(2, tokens.length)));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * @param email the email of the user
     * This method is responsible for saving the user voucher code and loyalty points
     */
    public static void saveUserLoyaltyPointsAndCodes(String email)
    {
        List<String> fileContent = new ArrayList<>();
        File file = new File("UserVoucherCode_loyaltyPoints.txt");
        try (Scanner sc = new Scanner(file)) {
            //check if the file is empty
            if (!sc.hasNextLine()) {
                String line = email + "," + User.getLoyaltyPoints();
                if (User.getVoucherCodes().size() > 0) {
                    for (String code : User.getVoucherCodes()) {
                        line += "," + code;
                    }
                }
                fileContent.add(line);
            } else {
                // read the file line by line before writing into it
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    // split the line by comma
                    String[] lineArray = line.split(",");
                    if (lineArray[0].equals(email) && lineArray.length == 2) {
                        line = email + "," + User.getLoyaltyPoints();
                        fileContent.add(line);
                    } else if (lineArray[0].equals(email) && lineArray.length > 2) {
                        if (User.getVoucherCodes().size() == 0) {
                            line = email + "," + User.getLoyaltyPoints();
                            fileContent.add(line);
                        } else {
                            line = email + "," + User.getLoyaltyPoints();
                            for (String code : User.getVoucherCodes()) {
                                line += "," + code;
                            }
                            fileContent.add(line);
                        }

                    } else {
                        fileContent.add(line);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("UserVoucherCode_loyaltyPoints.txt");
            for (String line : fileContent) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        User.setVoucherCodes(new ArrayList<>());
        loadUserVoucherCode(email);

    }
   
    /**
     * @param code the voucher code
     * This method is responsible for updating the voucher code
     */
    public static void updateVoucherCodes(String code)
    {
        //delete the voucher code from the user voucher code list
        User.getVoucherCodes().remove(code);
    }
    /**
     *  this method is responsible for adding the item to the cart
     */
    public void addToCart()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item id");
        int id = sc.nextInt();
        if (catalogController.getCatalogItem(id).getSealeditemsStock() == 0
                && catalogController.getCatalogItem(id).getLooseitemsStock() == 0)
        {
            System.out.println("Sorry, this item is out of stock");
            return;
        }
        System.out.println("Enter the item quantity");
        if(id%2==0)
        {
            int quantity = sc.nextInt();
            while (quantity > catalogController.getCatalogItem(id).getSealeditemsStock()) {
                System.out.println("Sorry, this quantity is not available");
                System.out.println("Enter the item quantity");
                quantity = sc.nextInt();
            }
            catalogController.getCatalogItem(id).setSealedStock(catalogController.getCatalogItem(id).getSealeditemsStock()-quantity);
            cartController.addToCart(catalogController.getCatalogItem(id), quantity, user.getEmail());
        }
        else
        {
            double quantity = sc.nextDouble();
            while (quantity > catalogController.getCatalogItem(id).getLooseitemsStock()) {
                System.out.println("Sorry, this quantity is not available");
                System.out.println("Enter the item quantity");
                quantity = sc.nextDouble();
            }
            catalogController.getCatalogItem(id).setLooseStock(catalogController.getCatalogItem(id).getLooseitemsStock()-quantity);
            cartController.addToCart(catalogController.getCatalogItem(id), quantity, user.getEmail());
        }

    }
    /**
     * This method is responsible for displaying the cart items
     */
    public void viewCatalog()
    {
        catalogController.displayCatalogItems();
    }
    /**
     * @return if the user is logged in or not
     */
    public boolean checkIfUserIsLoggedIn()
    {
        return user.isLoggedIn();
    }
    /**
     * @param code the voucher code
     * @return if the voucher code is valid or not
     */
    public static boolean checkVoucherCode(String code)
    {
        return User.getVoucherCodes().contains(code);
    }


}



