

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestPlayer.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestPlayer
{
    private Room room1;
    private Room room2;
    private Room room3;

    
    
    
    
    

    
    
    
    

    
    

    
    

    
    

    /**
     * Default constructor for test class TestPlayer
     */
    public TestPlayer()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        room1 = new Room("p");
        room2 = new Room("pp");
        room3 = new Room("ppp");
        room1.setExit("east", room2);
        room2.setExit("west", room1);
        room2.setExit("east", room3);
        room3.getExitList();
        room2.getExitList();
        room3.getExitList();
        room3.setExit("south", room1);
        room3.setExit("north", room1);
        room3.getExitList();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
