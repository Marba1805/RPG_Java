import java.util.ArrayList; 
import java.util.Random;
/**
 * Write a description of class Thief here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thief extends Player
{
    

    /**
     * Constructor for objects of class Thief
     */
    public Thief(Room initialRoom, String name)
    {
       super (initialRoom, name);
       addWeight(1000);
    }
    
    
    /**
     *gives a random nuber between 1 and 6 
     */
    public int throwDice()
    {
       Random randomGen = new Random();
       return randomGen.nextInt(6) + 1;
    }
    
    /**
     * find items
     */
    public Item itemFinder()
    {
        if(currentRoom.getItems() == null){
            return null;
        }
        
        else{
            Random randomGen = new Random();
            ArrayList<Item> roomItems = new ArrayList<>();
            roomItems = currentRoom.getItems();
            int i = randomGen.nextInt(roomItems.size());
            return(roomItems.get(i));
        }
    }
    
    /**
     * gets a random item
     */
    public Item randomItem()
    {   
        ArrayList<Item> thiefBag = new ArrayList<>();
        for(Item item : stuff){
            thiefBag.add(item);
        }
        Random randomGen = new Random();
        int i = randomGen.nextInt(thiefBag.size());
        Item itemR = thiefBag.get(i);
        return itemR;
    }
    
    
    
    /**
     * mkaes the actions
     */
    public void act()
    {
        Random randomGen = new Random();
        int x = throwDice(); 
        if(x == 5 || x == 6){
            if(itemFinder() != null){
           Item ourItem = itemFinder(); 
           stuff.add(ourItem);
           currentRoom.removeItem(ourItem);
          } 
        }
        
         if(x == 1){
           if(!stuff.isEmpty())
           {
         
             Item ourItem = randomItem();
             stuff.remove(ourItem);
             currentRoom.addItem(ourItem);
         }
        }
        
         if(x == 3){
             ArrayList<String> exits = new ArrayList<>(currentRoom.getExitList());
             int y = exits.size(); 
             Room nextRoom = currentRoom.getExit(exits.get(randomGen.nextInt(y)));
             currentRoom = nextRoom;
 
         }
       
       
       }
    }
   


