package Model;

/**
 * This class is used to create a loose catalog item
 * A loose catalog item is a catalog item that is sold in kg
 * The stock of a loose catalog item is in kg
 */
public class Looseitems extends CatalogItem {
    /**
     * @param name The name of the catalog item
     * @param category The category of the catalog item
     * @param description The description of the catalog item
     * @param id The id of the catalog item
     * @param brand The brand of the catalog item
     * @param price The price of the catalog item
     * @param discountPercentage The discount percentage of the catalog item
     * @param inStock The stock of the catalog item
     * @param LoyaltyPoints The loyalty points of the catalog item
     * This is the constructor of the loose catalog item
     */
    public Looseitems(String name, String category, String description, int id, String brand, double price,
                      double discountPercentage, double inStock, int LoyaltyPoints) {
        super(name, category, description, id, brand, price, discountPercentage, LoyaltyPoints);
        this.setLooseStock(inStock);
    }


}
