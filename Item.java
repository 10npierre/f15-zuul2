
/**
 * Inventory items class
 * 
 * @author Dean 
 * @version 2015.10.28
 */
public class Item
{
    private String name;
    private String description;
    private String useText;
    private String useDirection;
    
    
    /**
     * Constructor for objects of Item Class
     */
   
    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
        
    }
    
    /**
     * Return the item's name
     * 
     * @return the name of the item
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
