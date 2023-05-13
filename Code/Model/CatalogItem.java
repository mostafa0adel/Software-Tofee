package Model;


/**
 * This class represents the catalog item
 * A catalog item has a name, category, description, id, brand, price, discount
 * percentage, sealed items stock and loose items stock
 * The catalog items are loaded from the Catalog.txt file
 * The catalog items are displayed to the user
 * Author: @Candy_Crush Team
 * Version: 1.0
 * Date: 2023/05/4
 *
 */

public class CatalogItem {
    /**
     * This is the array of catalog items
     */
    private  CatalogItem[] catalogItems = new CatalogItem[100];
    /**
     * This is the id of the catalog item
     */
    private  int id;
    /**
     * This is the name of the catalog item
     */
    private  String name;
    /**
     * This is the category of the catalog item
     */
    private  String category;
    /**
     * This is the description of the catalog item
     */
    private  String description;
    /**
     * This is the brand of the catalog item
     */
    private  String brand;
    /**
     * This is the price of the catalog item
     */
    private  double price;
    /**
     * This is the discount percentage of the catalog item
     */
    private  double discountPercentage;
    /**
     * This is the sealed items stock of the catalog item
     */
    private int SealeditemsStock;
    /**
     * This is the loose items stock of the catalog item
     */
    private double LooseitemsStock;
    /**
     * This is the loyalty points of the catalog item
     */
    private  int LoyaltyPoints;

    /**
     * default constructor
     */
    public CatalogItem() {
    }

    /**
     * @param name The name of the catalog item
     * @param category The category of the catalog item
     * @param description The description of the catalog item
     * @param id The id of the catalog item
     * @param brand The brand of the catalog item
     * @param price  The price of the catalog item
     * @param discountPercentage The discount percentage of the catalog item
     * @param LoyaltyPoints The loyalty points of the catalog item
     * This constructor is used to create a new catalog item
     */
    public CatalogItem(String name, String category, String description, int id, String brand, double price,
                       double discountPercentage,int LoyaltyPoints) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.LoyaltyPoints = LoyaltyPoints;
    }
    // getters and setters for the CatalogItem class

    /**
     * @return name of the catalog item
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return category of the catalog item
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * @return description of the catalog item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return id of the catalog item
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return brand of the catalog item
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * @return price of the catalog item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return discount percentage of the catalog item
     */
    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    /**
     * @return stock of the sealed catalog item
     */
    public int getSealeditemsStock() {
        return this.SealeditemsStock;
    }

    /**
     * @return stock of the loose catalog item
     */
    public double getLooseitemsStock() {
        return this.LooseitemsStock;
    }

    /**
     * @param SealedStock
     * This method is used to set the stock of the sealed catalog item
     */

    public void setSealedStock(int SealedStock) {
        this.SealeditemsStock = SealedStock;
    }

    /**
     * @param LooseStock
     * This method is used to set the stock of the loose catalog item
     */
    public void setLooseStock(double LooseStock) {
        this.LooseitemsStock = LooseStock;
    }


    /**
     * @return catalog items
     */
    public CatalogItem[] getCatalogItems() {
        return catalogItems;
    }



    /**
     * @param catalogItems catalog items
     * This method is used to set the catalog items
     */
    public void setCatalogItems(CatalogItem[] catalogItems) {
        this.catalogItems = catalogItems;
    }

    /**
     * @return loyalty points of the catalog item
     * This method is used to get the loyalty points of the catalog item
     */
    public int getLoyaltyPoints() {
        return this.LoyaltyPoints;
    }

}

