import java.util.HashSet;
import java.util.ArrayList;
/**
 * Player class, our player in the game 
 */
public class Player
{
    protected Room currentRoom; 
    protected HashSet<Item> stuff;
    private double weight = 0.0;
    private double weightLimit = 100.0;
    protected static ArrayList<Player> actors = new ArrayList<>();
    private String name;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room initialRoom, String names)
    {
     currentRoom = initialRoom;
     stuff = new HashSet<Item>(); 
     actors.add(this);
     name = names;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void addWeight(int weight){
        weightLimit = weight;
    }
    
    /**
     * Accessor for current room
     */
    public Room getRoom()
    {
     return(currentRoom);
    }
    
    /**
     * Adds to our bag array list
     * @PARAM item 
     */
    public boolean addToBag(Item item)
    {        
        return(stuff.add(item));    
    }
    
    /**
     * Removes from the bag
     * 
     * 
     */
    
    public void removeFromBag(Item item)
    {
       stuff.remove(item);
    }
    
    /**
     * Returns all items in bag
     * 
     */
    
    public String getInventory()
    {
        if(stuff.isEmpty() == true || stuff == null)
        {
            return ("you are not carrying anything");
        }
    
        else {
            String returnString = "";
            for(Item item : stuff){
                returnString += item.getName() + " : " + item.getDescription() + ".\n";
            }
                return("You are carrying: " + "\n" + returnString);
        }
        }    
        
    /**
     * Searches bag for certain items by string
     * 
     * @PARAM string 
     * 
     */
    public Item searchBag(String name)
    {
           for(Item item : stuff){
               if(item.getName().contains(name))
               {
                   return(item);
                }
        }
        return null;
    }
    
    /**
    * sets current room
    * @PARAM room
    */
    public void setRoom(Room setter)
    {
        currentRoom = setter;
    }
    
    /**
    * Will return players situation
    */ 
    public String look()
    {
        return currentRoom.getLongDescription();
    }
    
    /**
     * Method used for going to the next room 
     * @PARAM direction
     */
    public String goRoom(String direction)
    {
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
        return "There is no door!";
        }
        else {
         currentRoom = nextRoom;
         return currentRoom.getLongDescription();
        }
    }   
    
    /**
     * 
     * Will add item to bag unless it isnt there
     * if there is no object in room, it will return that there isnt one
     * also makes sure everything is within bag limit
     * @PARAM name
     */
    public String takeItem(String name)
    {
        if(currentRoom.searchItems(name) == null){
            return("There is no " + name + " here");
        }
        else if(currentRoom.searchItems(name).getWeight() + weight > weightLimit){
        return("You cannot pick this up, your bag is overweight");
       }
        else{
            Item temp = currentRoom.searchItems(name);
            addToBag(temp);
            weight += temp.getWeight(); 
            currentRoom.removeItem(temp);
            return(temp.getName() + " was added to bag");
         }
        }
        
        /**
         * Removes item from bag and places it in current room 
         * Removes bag's weight
         * 
         * @PARAM name
         */
    public String dropItem(String name)
    {  
      if( addToBag(searchBag(name)) == true){
          return("You are not carrying a " + name);
       }
       else{
          Item temp = searchBag(name);
          removeFromBag(temp);
          weight -= temp.getWeight();
          currentRoom.addItem(temp);
          return(temp.getName() + " has been dropped");
        }
    
    }
    
        public String eatGarlicBread(String garlicBread)
    {
        if(searchBag(garlicBread) == null){
            return("You are not carrying any Garlic Bread");
        }
        else{
            Item food = searchBag(garlicBread);
            weight -= food.getWeight();
            weightLimit += 150.0;
            removeFromBag(food);
            return("THAT'S THE STUFF!!!!!!!!!!");
        }
    }
    
    /**
     * Method for eating cookie
     * adds 50ibs to weight limit  
     *
     * @PARAM cookie
     */
    public String eatCookie(String cookie)
    {
        if(searchBag(cookie) == null){
            return("You are not carrying any cookies");
        }
        else{
            Item food = searchBag(cookie);
            weight -= food.getWeight();
            weightLimit += 50.0;
            removeFromBag(food);
            return("yummy cookie");
        }
    }
 
    
     //public String readBook(String book)
     //   {
         //       if(searchBag(book) == null){
             //        return("You are not carryin any books");  
             //       }
             //       else{
                 //           Item = searchBag(book);
                 //           return("much knowledge such wow");
                 //       }
                //   }

  
    }
    
