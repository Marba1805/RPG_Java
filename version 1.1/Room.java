import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
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
    private HashMap<String, Room> exits; // stores exits of this room.
    private HashSet<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. 
     * "description" is something like "a gym" or
     * "a class room".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashSet<Item>(); 
    }
    
    /**
     * Define an exit from this room to another room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
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
     *     You are in the gym.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
        {
            if(items.size() > 0){
           String itemsInHere = getItemString();
        return "You are " + description
        + ".\n" + getExitString() + ".\n" + itemsInHere;
      }
         else{
            return "You are " + description
        + ".\n" + getExitString();
        }
    }

    /**
     * Return a string sating the exits for the current room the player is located
     * such as:
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
    
     /**
     * Adds the items to each room
     */
    public void addItem(Item item)
    {
       items.add(item);
    }
    
    /**
     * removes item from the "List" of items
     * @Param item - for removing 
     */
    public void removeItem(Item item)
    {
     items.remove(item);
    }
    
    /**
     * Searches for items in the room's arraylist and then returns Item's name
     * @PARAM string 
     */
    public Item searchItems(String name)
    {
        for(Item item : items)
        {
            if(item.getName().contains(name))
                return(item);
           }
            return null;
        }
    
    /**
     * gets the item fromm the item array list, => <Item>
     */public ArrayList getItems()
    {
        if(items.isEmpty()){
            return null;
        } 
        else{
        ArrayList<Item> roomItems = new ArrayList<>();
        for(Item item : items)
        {
            roomItems.add(item);
        }
        return roomItems;
        }
    }
       
    /**
     * Returns the items that are in the current room
     */
    public String getItemString()
    {
        String returnString = "";
        if(items.size() > 0){
        for(Item item : items){
            returnString += (item.getName() + ": " + item.getDescription()  + ".\n");
        }
        }
        if(returnString == null)
        {
            return("");
        }
        else{
            return("Items in room: " + "\n" + returnString);
        }
       }
       
    /**
     * returns the exits of the current room
     */
    public ArrayList getExitList()
    {
      ArrayList<String> exitString = new ArrayList<>();
      Set<String> keys = exits.keySet();
       for(String exit : keys) {
           exitString.add(exit);
        }
       return exitString;
    }
 
}


