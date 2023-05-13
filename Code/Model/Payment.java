package Model;

import Controller.UserController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents the payment process
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/12
 */
public class Payment {
    /**
     * paymentMethod This is the payment method
    */
    private String paymentMethod;
    /**
    * totalPRICE This is the total price of the order
     */
    double totalPRICE;
    /**
     * coupon This is the coupon of the user
     */
    private final HashMap<String, Double> coupon = new HashMap<>();

    /**
     * @param totalPRICE This is the total price of the order
     * This is the constructor of the payment class
     */
    public Payment(double totalPRICE)
    {
        this.totalPRICE = totalPRICE;
        paymentMethod = "upon delivery";
        loadVoucher();
    }
   
    /**
     * @param code This is the code of the coupon
     * @return if the coupon is redeemed or not
     * This method is used to redeem a coupon
     */
    public boolean redeemCoupon(String code)
    {
        if (coupon.containsKey(code) && UserController.checkVoucherCode(code))
        {
            if(coupon.get(code) > totalPRICE)
            {
                double reminder = coupon.get(code) - totalPRICE;
                totalPRICE = 0;
                coupon.put(code, reminder);
                System.out.println("You have redeemed " + totalPRICE + " points");
            }
            else{
                totalPRICE -= coupon.get(code);
                System.out.println("You have redeemed " + coupon.get(code) + " points");
                coupon.remove(code);
                UserController.updateVoucherCodes(code);
                saveVoucher();
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @return the total price of the order
     * This method is used to get the total price of the order
     */
    public double getTotalPRICE() {
        return totalPRICE;
    }
    
    /**
     * @param points This is the points of the user
     * @return if the points are redeemed or not
     * This method is used to redeem points
     */
    public boolean redeemPoints(int points)
    {
        if (points > 0 && points <= User.getLoyaltyPoints())
        {
            if(points > totalPRICE)
            {
                System.out.println("You can't redeem more than the total amount");
                System.out.println("So, you can redeem " + totalPRICE + " points");
                System.out.println("Your Reminder amount is " + totalPRICE);
                points -= (int) totalPRICE;
                totalPRICE = 0;
            }
            else
            {
                totalPRICE -= points;
                System.out.println("You have redeemed " + points + " points");
                System.out.println("Your Reminder amount is " + totalPRICE);
            }
            User.setLoyaltyPoints(User.getLoyaltyPoints() - points);
            return true;
        }
        else
        {
            return false;
        }

    }
    
    /**
     * @param email This is the email of the user
     * @return if the payment is done or not
     * This method is used to pay the total price of the order
     */
    public boolean pay(String email)
    {
        boolean paid = false;
        while (!paid){
            System.out.println("1- Redeem Points");
            System.out.println("2- Redeem Coupon");
            System.out.println("3- Pay total price Upon Delivery");
            int choice = new Scanner(System.in).nextInt();
            if(choice == 1)
            {
                System.out.println("Enter the number of points you want to redeem");
                int points = new Scanner(System.in).nextInt();
                if(this.redeemPoints(points))
                {
                    System.out.println("You have redeemed " + points + " points");
                    System.out.println("Your Reminder amount is " + totalPRICE);
                    System.out.println("Your order has been placed successfully");
                    System.out.println("Thank you for using our Store");
                    UserController.saveUserLoyaltyPointsAndCodes(email);
                    paid = true;
                }
                else
                {
                    System.out.println("You don't have enough points");
                }
            }
            else if(choice == 2)
            {
                System.out.println("Enter the coupon code");
                String code = new Scanner(System.in).nextLine();
                if(this.redeemCoupon(code))
                {
                    System.out.println("Your Reminder amount is " + totalPRICE);
                    System.out.println("Your order has been placed successfully");
                    System.out.println("Thank you for using our Store");
                    UserController.saveUserLoyaltyPointsAndCodes(email);
                    paid = true;
                }
                else
                {
                    System.out.println("Invalid Coupon Code");
                }
            }
            else if(choice == 3)
            {
                System.out.println("Your order has been placed successfully");
                System.out.println("Thank you for using our Store");
                UserController.saveUserLoyaltyPointsAndCodes(email);
                paid = true;
            }

        }
        return true;}
    /**
     *  This method is used to load the voucher codes from the file
     */
    public void loadVoucher()
    {
        File file = new File("VoucherCodes.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(",");
                coupon.put(arr[0], Double.parseDouble(arr[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *  This method is used to save the voucher codes in the file
     */
    public void saveVoucher()
    {
        File file = new File("VoucherCodes.txt");
        try{
            FileWriter fw = new FileWriter(file);
            for (String key : coupon.keySet())
            {
                fw.write(key + "," + coupon.get(key) + "\n");
            }
            fw.close();
            coupon.clear();
            loadVoucher();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
