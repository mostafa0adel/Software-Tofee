package Model;

import java.io.*;
import java.util.*;

/**
 * This class represents the User of the store
 * A user has an email, password, address, isLoggedIn, shopping cart and order
 * The user can log in, register, logout, add items to cart, display cart, make an order, display orders, show order status
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/4
 */
public class User{
    /**
     * email This is the email of the user
     */
    private String email;
    /**
     * password This is the password of the user
     */
    private String password;
    /**
     * address This is the address of the user
     */
    private String address;
    /**
     * isLoggedIn This is the status of the user
     */
    private boolean isLoggedIn;
    /**
     * ShoppingCart This is the shopping cart of the user
     */
    private cart ShoppingCart;
    /**
     * Order This is the order of the user
     */
    private order Order;
    /**
     * loyaltyPoints This is the loyalty points of the user
     */
    private static int loyaltyPoints;
    /**
     * VoucherCodes This is the voucher codes of the user
     */
    private static  ArrayList<String> VoucherCodes = new ArrayList<>();


    // default constructor
    /**
     * This is the default constructor for the User class
     */
    public User() {
        this.isLoggedIn = false;
    }

    /**
     * @param email The email of the user
     * @param password The password of the user
     * @param address The address of the user
     * This constructor is used to create a new user
     */
    public User(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.isLoggedIn = false;
    }

    /**
     * @param strings The voucher codes
     * This method is used to set the voucher codes
     */
    public static void setVoucherCodes(ArrayList<String> strings) {
        VoucherCodes = strings;
    }

    /**
     * @return the voucher codes
     * This method is used to get the voucher codes
     */
    public static ArrayList<String> getVoucherCodes() {
        return VoucherCodes;
    }

    /**
     * @param email The email of the user
     * @return the user with the given email
     * This method is used to get the user with the given email
     * It reads the user's information from Users.txt file
     */
    public User getUser(String email) {
        // read the users information from Users.txt file
        try {
            File file = new File("Users.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] user = sc.nextLine().split(",");
                if (user[0].equals(email)) {
                    return new User(user[0], user[1], user[2]);
                }
            }
            sc.close();
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    /**
     * This method is used to save the user information in Users.txt file
     */
    public void saveUser() {
        try {
            FileWriter fw = new FileWriter("Users.txt", true);
            fw.write(this.email + "," + this.password + "," + this.address + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }


    /**
     * @param email The email of the user
     * @param password The password of the user
     * @param address The address of the user
     * The user is registered
     */
    public void register(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }


    /**
     * @param email The email of the user
     * @param password The password of the user
     * @return true if the user email and password are correct, false otherwise
     * This method is used to check if the user email and password are correct
     */
    public boolean checkUserPassword(String email, String password) {

        User retrievedUser = getUser(email);
        if (retrievedUser != null) {
            while (!retrievedUser.password.equals(password)) {
                System.out.println("Wrong password, try again!");
                System.out.println("Enter your password: ");
                password = new Scanner(System.in).nextLine();
            }
            return true;
        }
        return false;
    }

    /**
     * @param userEmailAddress The email of the user
     * @param userPassword The password of the user
     * This method is used to log in the user if the email and password are correct
     * The user is logged in
     */
    public void login(String userEmailAddress, String userPassword) {
        this.isLoggedIn = true;
        System.out.println("Login successful");
        this.email = userEmailAddress;
        this.password = userPassword;
        ShoppingCart = new cart(this.email);
        Order = new order(this.email);
    }

    /**
     * This method is used to log out the user
     */
    public void logout() {
        // set isLoggedIn to false
        if (!this.isLoggedIn) {
            System.out.println("You are already logged out!");
        }
        else{
            this.isLoggedIn = false;
            System.out.println("You are logged out!");
        }
    }


    /**
     * @return the loyalty points of the user
     * This method is used to get the loyalty points of the user
     */
    public static int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    
    /**
     * @param lp The loyalty points of the user
     * This method is used to set the loyalty points of the user
     */
    public static void setLoyaltyPoints(int lp) {
        loyaltyPoints = lp;
    }

    /**
     * @return the shopping cart of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return if the user is logged in or not
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}


