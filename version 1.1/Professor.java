import java.util.Random;
import java.util.*;
import java.util.ArrayList;
/**
 * Write a description of class Proffessor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Professor extends Player
{
    String pointingDirection;
    /**
     * Constructor for objects of class Proffessor
     */
    public Professor(Room initalRoom, String names)
    {
        super (initalRoom, names); 
        Random randomGen = new Random();
        pointingDirection = randomDirection();
    }
    
    @Override
    public String toString()
    {
        Player ourActor = actors.get(actors.indexOf(this));
        String name = ourActor.getName();
        return("There is a " + name + " here." + " The " + name + " is pointing " + pointingDirection);
    }
    
    /**
     * this method g7enerates a arndom nuber between 1 and 6, guiving the same
     * functionalitie as a die
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int throwDice()
    {
       Random randomGen = new Random();
       return randomGen.nextInt(6) + 1;
    }
    
    /**
     * gives a random direction in whitch to go
     */
    public String randomDirection(){
       ArrayList<String> direction = new ArrayList<>();
       Random randomGen = new Random();
       direction.add("north");
       direction.add("east");
       direction.add("south");
       direction.add("west");
       return(direction.get(randomGen.nextInt(direction.size())));
       }
       
    public void act()
    {
        Random randomGen = new Random();
       ArrayList<String> exit = new ArrayList<>(currentRoom.getExitList());
      if(throwDice() == 6){
         int x = exit.size(); 
         Room nextRoom = currentRoom.getExit(exit.get(randomGen.nextInt(x)));
         currentRoom = nextRoom;
         pointingDirection = randomDirection();
       }
    }
}