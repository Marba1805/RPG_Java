/**
 * This is the class that
 * is going to be the framework
 * for our items
 * 
 * @PARAM name - name of item
 * @PARAM description - description of object
 * @PARAM weight - weight of object
 * 
 */
public class Item
{
    private String names;
    private String descriptions;
    private double weights;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, double weight)
    {
        names = name;
        descriptions = description;
        weights = weight;
    }
    
    /**
     * returns the name of a specified Item 
     */
    public String getName()
    {
        return names;
    }
    
    /**
     * returns the description of a specified Item
     */
    public String getDescription()
    {
        return descriptions;
    }
    
     /**
     * returns the weight of a specified Item
     */
    public double getWeight()
    {
        return weights  ;
    }
}