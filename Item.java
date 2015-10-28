/**
 * Item class for text adventure.
 * 
 * @author Dean Erik Nate
 */

public class Item
{
    private String name;
    private String description;
    private String useText;
    private String useDirection;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, String useText)
    {
        this.name = name;
        this.description = description;
        this.useText = useText;
    }

    /**
     * Return the name of the item
     * 
     * @return     the name of the item
     */
    public String getName()
    {
       return name;
    }
    
    /**
     * Return the description of the item
     * 
     * @return     the description of the item
     */
    public String getDescription()
    {
       return description;
    }
    
    /**
     * Set the name of the item
     * param     the name of the item
     */
    public void setName(String name)
    {
       this.name = name;
    }
    
    /**
     * set the description of the item
     * 
     * @param     the description of the item
     */
    public void setDescription(String description)
    {
       this.description = description;
    }
    
    /**
     * set the use text of the item
     * 
     * @param     the use text of the item
     */
    public void setUseText(String useText)
    {
       this.useText = useText;
    }
    
    /**
     * Return the use text of the item
     * 
     * @return     the use text of the item
     */
    public String getUseText()
    {
       return useText;
    }
    
    public String getUseDirection() {
        return useDirection;
    }
    
    public void setUseDirection(String useDirection) {
        this.useDirection = useDirection;
    }
}