import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private boolean hasItem;
    private Item item;
    private boolean conditionsMet = true;
    private String needs;
    private String conditionalDirection;
    public Room conditionalNeighbor;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define a conditional exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setConditionalExit(String direction, Room neighbor) 
    {
        conditionalDirection = direction;
        conditionalNeighbor = neighbor;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return ">>> " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Item getItem() 
    {
        if (item != null) {
           return item;
        }
        else {
           return null; 
        }
    }
    
    public void setItem(Item item) 
    {
        this.item = item;
        hasItem = true;
    }
    
    public boolean hasItem() 
    {
        if (item != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Item takeItem() {
        if (item != null) {
           Item tempItem = item;
           item = null;
           hasItem = false;
           return tempItem;
        }
        else {
           return null; 
        }
    }
    
    public void conditionsMet(Boolean bool) {
        conditionsMet = bool;
        setExit(conditionalDirection, conditionalNeighbor);
    }
    
    public boolean canGo() {
        if(conditionsMet)
            return true;
        else 
            return false;
    }
    
    public String onConditionsMet() {
        return item.getUseText();
    }
    
    public void setNeeds (String needs) {
        this.needs = needs.toUpperCase();        
    }
    
    public boolean needs(String itemName) {
        if(needs.equalsIgnoreCase(itemName)) {
            return true;
        }
        else {
            return false;
        }
    }

}