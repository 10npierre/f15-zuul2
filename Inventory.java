
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Inventory
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> inventory;

    /**
     * Constructor for objects of class INventory
     */
    public Inventory()
    {
        inventory = new ArrayList<Item>();
    }

    /**
     * Add item to inventory
     * 
     * @param  item   item to be added
     *
     */
    public void addItem(Item item)
    {
        inventory.add(item);
    }
    
     /**
     * Add item to inventory
     * 
     * @param  item   item to be added
     *
     */
    public boolean hasItem(String itemName)
    {
        boolean found = false;
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                found = true;
            }
            else {
                found = false;
            }
        }
        return found;
    }   
    
     public Item getItem(String itemName)
    {
       Item found = null;
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                found = item;
            }
            else {
                found = null;
            }
        }
        return found;
    } 
}
