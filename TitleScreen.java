import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author Timothy Fung
 * @version 2024-01-16
 */
public class TitleScreen extends World
{
    static GreenfootSound title = new GreenfootSound("sounds/title.wav");
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1);
        setBackground("images/Library.png");
        title.play();
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Label label = new Label("title", 100);
        addObject(label,600,337);
        Label label2 = new Label("Press 'Enter' to play", 50);
        addObject(label2,600,430);
        Label label3 = new Label("Use number keys 1 to 9 to select cards.", 50);
        addObject(label3,600,490);
        Label label4 = new Label("Defeat the enemy taking as little damage as possible.", 50);
        addObject(label4,600,560);
    }
}
