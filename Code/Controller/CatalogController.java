package Controller;

import Model.CatalogItem;
import Model.Looseitems;
import Model.Sealeditems;
import View.ToffeeStoreViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is responsible for loading the catalog items from the catalog file
 * It also displays the catalog items
 * It also returns the catalog item based on the id
 */
public class CatalogController {
    /**
     * This is the catalog item
     */
   private CatalogItem catalogItm=new CatalogItem();
    /**
     * This is the toffee store viewer
     */
    private ToffeeStoreViewer toffeStoreViewer = new ToffeeStoreViewer();
    /**
     * default constructor
     */
    public CatalogController()
    {
        loadCatalogItems();
    }
    
    /**
     *  This method loads the catalog items from the catalog file
     */
    public void loadCatalogItems() {

        CatalogItem[] catalogItems = new CatalogItem[100];
        CatalogItem catalogItem;
        try {
            File catalogFile = new File("Catalog.txt");
            Scanner catalogScanner = new Scanner(catalogFile);
            int i = 0;
            while (catalogScanner.hasNextLine()) {
                String catalogItemString = catalogScanner.nextLine();
                String[] catalogItemStringArray = catalogItemString.split(",");
                if (Integer.parseInt(catalogItemStringArray[3]) % 2 == 0) {
                    catalogItem = new Sealeditems(catalogItemStringArray[0], catalogItemStringArray[1],
                            catalogItemStringArray[2], Integer.parseInt(catalogItemStringArray[3]),
                            catalogItemStringArray[5], Double.parseDouble(catalogItemStringArray[6]),
                            Double.parseDouble(catalogItemStringArray[7]), Integer.parseInt(catalogItemStringArray[4]),
                            Integer.parseInt(catalogItemStringArray[8]));
                } else {
                    catalogItem = new Looseitems(catalogItemStringArray[0], catalogItemStringArray[1],
                            catalogItemStringArray[2], Integer.parseInt(catalogItemStringArray[3]),
                            catalogItemStringArray[5], Double.parseDouble(catalogItemStringArray[6]),
                            Double.parseDouble(catalogItemStringArray[7]),
                            Double.parseDouble(catalogItemStringArray[4]),
                            Integer.parseInt(catalogItemStringArray[8]));

                }
                catalogItems[i] = catalogItem;
                i++;
            }
            catalogScanner.close();
            catalogItm.setCatalogItems(catalogItems);
        } catch (FileNotFoundException e) {
            System.out.println("Catalog file not found!");
        }
    }
   
    /**
     * @param id catalog item id
     * @return catalog item
     */
    public  CatalogItem getCatalogItem(int id) {
        for (CatalogItem catalogItem : catalogItm.getCatalogItems()) {
            if (catalogItem.getId() == id) {
                return catalogItem;
            }
        }
        return null;
    }
   /**
    *  This method displays the catalog items
    */
public void displayCatalogItems()
    {
        toffeStoreViewer.viewCatalog(catalogItm.getCatalogItems());
    }
}
