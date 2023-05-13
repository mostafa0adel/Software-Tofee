package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class responsible for the order process
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/4
 */
public class order {

    /**
     * orderItems This is the items of the order
     */
    private HashMap<String, ArrayList<CatalogItem>> orderItems;
    /**
     * orderItemQuantity This is the quantity of the items
     */
    private HashMap<Integer, Double> orderItemQuantity;
    /**
     *  This is the total price of the order
     */
    private double totalPrice;
    /**
     * orderstat This is the order statue
     */
    private OrderStatue orderstat;
    /**
     * email This is the email of the user
     */
    private String email;
    /**
     * phoneNumber This is the phone number of the user
     */
    private String phoneNumber;
    /**
     * shippingAddress This is the shipping address of the user
     */
    private String shippingAddress;
    /**
     * paymentMethod This is the payment method of the user
     */
    private Payment paymentMethod;
    /**
     * orderOwnerName This is the name of the user
     */
    private String orderOwnerName;
    /**
     * orderID This is the order ID
     */
    private String orderID;
    
    /**
     * @param email This is the email of the user
     */
    public order(String email) {
        orderItems = new HashMap<>();
        orderItemQuantity = new HashMap<>();
        totalPrice = 0;
        this.email = email;
    }

    /**
     * @param items                   This is the items of the order
     * @param itemsQuantity           This is the quantity of the items
     * @param name                    This is the name of the user
     * @param phone                   This is the phone number of the user
     * @param shippingaddress-address This is the shipping address of the user
     * @param email                   This is the email of the user
     * @param orderID                 This is the order ID
     *                                This is the constructor of the order class
     */
    public order(ArrayList<CatalogItem> items,
            HashMap<Integer, Double> itemsQuantity, String name, String phone, String shippingaddress, String email,
            String orderID) {
        this.email = email;
        orderItems = new HashMap<>();
        orderItems.put(orderID, items);
        this.orderItemQuantity = itemsQuantity;
        this.phoneNumber = phone;
        this.shippingAddress = shippingaddress;
        this.orderOwnerName = name;
        this.orderstat = new created();
        orderstat.activateStatue();
        this.totalPrice = 0;
        this.orderID = orderID;
        ArrayList<CatalogItem> OrderItem = orderItems.get(orderID);
        for (CatalogItem item : OrderItem) {
            totalPrice += (item.getPrice() - (item.getPrice() * item.getDiscountPercentage() / 100))
                    * orderItemQuantity.get(item.getId());
        }
    }

    /**
     * @return order items 
     * This method is used to get the order items
     */
    public HashMap<String, ArrayList<CatalogItem>> getOrderItems() {
        return orderItems;
    }

  /**
   * @param orderItems The order items
   * This method is used to set the order items
   */
      public void setOrderItems(HashMap<String, ArrayList<CatalogItem>> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * @return order item quantity
     * This method is used to get the order item quantity
     */
    public HashMap<Integer, Double> getOrderItemQuantity() {
        return orderItemQuantity;
    }

    /**
     * @param orderItemQuantity The order item quantity
     * This method is used to set the order item quantity
     */
    public void setOrderItemQuantity(HashMap<Integer, Double> orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    /**
     * @return total price
     * This method is used to get the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice The total price
     * This method is used to set the total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return order statue
     * This method is used to get the order statue
     */
    public OrderStatue getOrderstat() {
        return orderstat;
    }

    /**
     * @param orderstat The order statue
     * This method is used to set the order statue
     */
    public void setOrderstat(OrderStatue orderstat) {
        this.orderstat = orderstat;
    }

    /**
     * @return email
     * This method is used to get the email
     */
    public String getEmail() {
        return email;
    }


    /**
     * @return phone number
     * This method is used to get the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber The phone number
     * This method is used to set the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return shipping address
     * This method is used to get the shipping address
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress The shipping address
     * This method is used to set the shipping address
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return payment method
     * This method is used to get the payment method
     */
    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod The payment method
     * This method is used to set the payment method
     */
    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return order owner name
     * This method is used to get the order owner name
     */
    public String getOrderOwnerName() {
        return orderOwnerName;
    }

    /**
     * @param orderOwnerName The order owner name
     * This method is used to set the order owner name
     */
    public void setOrderOwnerName(String orderOwnerName) {
        this.orderOwnerName = orderOwnerName;
    }

    /**
     * @return order ID
     * This method is used to get the order ID
     */
    public String getOrderID() {
        return orderID;
    }
    
    /**
     * @param orderID The order ID
     * This method is used to set the order ID
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }


}


