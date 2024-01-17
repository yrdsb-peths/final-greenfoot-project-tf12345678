import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author Timothy Fung
 * @version 2023-12-11
 */
public class MyWorld extends World
{
    static Argalia argalia = new Argalia();
    static Roland roland = new Roland();
    
    
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1);
        setBackground("images/Keter.png");
        argalia.setHP(100);
        roland.setHP(150);
        roland.reset();
        argalia.reset();
        
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(roland,950,375);
        addObject(argalia,250,375);
        addObject(roland.getHPLabel(), 1150, 40);
        addObject(argalia.getHPLabel(), 50, 40);
    }
    
    /**
     * Gets the enemy
     */
    public static Enemy getEnemy()
    {
        return argalia;
    }
    
    public static Roland getRoland()
    {
        return roland;
    }
    
    public static void resetAll()
    {
        roland.reset();
        argalia.reset();
    }
}
