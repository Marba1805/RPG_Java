/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Professor professor;
    private Thief thief;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, labyrinth, dorms, library;
        Item book, bottle, axe, sword, cookie, amulet, backpack, garlicBread;
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        labyrinth = new Room("in the University Labyrinth");
        dorms = new Room("in the University's Dorms");
        library = new Room("in the Library of the campus");
        
        //Creates Items
        book = new Item("book", "a book written in an icomprehencible language", 30.2);
        bottle = new Item("bottle", "a glass bottle containing an unknown liquid", 80.2);
        axe = new Item("axe", "a heavy bronze weapon for attacking enemies", 67.5);
        sword = new Item("sword", "a heavy bronze weapon for slicing through enemies", 75.2);
        cookie = new Item("cookie", "a magic cookie", 0.1);
        amulet = new Item("amulet", "Amulet of Wisdom", 1.0);
        backpack = new Item("backpack", "it has all you need fot class inside", 110.0);
        garlicBread = new Item("garlicBread", "Imagine if God Himself created the perfect food,\n"+
        " better than the Tree of Life and the Tree of Wisdom combined,\n" + 
        "that tastes like pure happiness. Now multiply that by one billion.\n" +
        "Now multiply THAT by infnity. This is but one thousandth of the greatness of garlic bread."
        , 10.0);
        
        //initialse the item locations
        pub.addItem(sword);
        lab.addItem(book);
        office.addItem(bottle);
        theater.addItem(axe);
        office.addItem(book);
        outside.addItem(book);
        outside.addItem(cookie);
        office.addItem(cookie);
        theater.addItem(cookie);
        pub.addItem(axe);
        lab.addItem(sword);
        outside.addItem(amulet);
        dorms.addItem(backpack);
        library.addItem(book);
        dorms.addItem(garlicBread);
        
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", labyrinth);

        theater.setExit("west", outside);
        theater.setExit("east",library);

        pub.setExit("east", outside);
        pub.setExit("west", labyrinth);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("south", dorms);
        
        labyrinth.setExit("south", pub);
        labyrinth.setExit("east", outside);
        
        dorms.setExit("north", office);
        dorms.setExit("east", lab);
        
        library.setExit("west", theater);
        
        player = new Player(outside, "Player"); // start game outside
        professor = new Professor(labyrinth, "Professor"); // adds the professor to the labyrinth
        thief = new Thief(pub, "Thief"); // adds a thief to the pub
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     * listing some of ptions for the player to follow
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.look());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                professorCommand();
                thiefCommand();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                System.out.println(player.look());
                professorCommand();
                thiefCommand();
                break;
                
            case TAKE:
                takeItem(command);
                professorCommand();
                thiefCommand();
                break;
                
            case INVENTORY:
                inventory(command);
                professorCommand();
                thiefCommand();
                break;
                
            case DROP:
                 wantToQuit = (dropItem(command));
                 professorCommand();
                 thiefCommand();
                 break;
                 
            case EAT:
                 eatItem(command);
                 professorCommand();
                 thiefCommand();
                 break;
                 
            case READ:
                 // readBook(command);
                 //professorCommand();
                 //thiefCommand();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some messages for the player and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * the command "Quit" has been entered, and so the game will shut down
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    public static void main( String [] args) {
        (new Game()).play();
    }
    
    /**
     * This is the commsnd for the player to change rooms, 
     * the player needs to imput the direction in whitch to go
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word,
            // we don't know where to go...
            System.out.println("Go where?");
        }
        else {
            String direction = command.getSecondWord();
            System.out.println(player.goRoom(direction));
        }
       }
       
    private void takeItem(Command command)
    {   
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
        }
        else {
            String name = command.getSecondWord();
            System.out.println(player.takeItem(name));
            }
        }
       
    private boolean dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return false;
        }
        else {
              String name = command.getSecondWord();
              if(name.contains("amulet") && player.searchBag(name) != null && player.getRoom() == professor.getRoom()){
                  System.out.println("YOU WON !");
                  return true;
                }
              else{
                System.out.println(player.dropItem(name));
              return false;
            }
            }
       
        }
       
    /**
     * returns the information of the inventory
     */
    private void inventory(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("huh?");
        }
        
       else{
           System.out.println(player.getInventory());
        }
    }
    
    /**
     * this command is to eat the etible items 
     */
    private void eatItem(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Eat What?");
        }
        
        else if(!command.getSecondWord().contains("cookie")&& command.getSecondWord().contains("garlib bread")){
            System.out.println("You cannot eat that");
        }
        else if(command.getSecondWord().contains("garlic bread")){
            System.out.println(player.eatGarlicBread(command.getSecondWord()));
        }
        else{
          System.out.println(player.eatCookie(command.getSecondWord()));
        }
    }
    
    //private void readBook(Command command)
    // {
    //     if(!command.hasSecondWord()){
    //         System.out.println("Read what?");
    //   }
    //  else if(!command.getSecondWord().contains("book")){
    //    System.out.println("reading the book might be nice");
    //}
    //else{
    //   System.out.println(player.readBook(command.getSecondWord()));
    //}
    //}
    
    
    /**
     * makes professor move
     */
    
    private void professorCommand(){
        professor.act();
        if(professor.getRoom().equals(player.getRoom())){
            System.out.println(professor.toString());
        }
        }
     
    /**
     * Makes thief move
     */
    private void thiefCommand()
    {
        thief.act();
    }
    }
