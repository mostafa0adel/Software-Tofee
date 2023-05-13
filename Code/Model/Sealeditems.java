package Model;

/**
 * This class is used to create a sealed catalog item
 * A sealed catalog item is a catalog item that is sold in units
 * The stock of a sealed catalog item is in units
 */

public class Sealeditems extends CatalogItem {
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
     * This is the constructor of the sealed catalog item
     */
    public Sealeditems(String name, String category, String description, int id, String brand, double price,
                       double discountPercentage, int inStock, int LoyaltyPoints) {
        super(name, category, description, id, brand, price, discountPercentage, LoyaltyPoints);
        this.setSealedStock(inStock);
    }

}
