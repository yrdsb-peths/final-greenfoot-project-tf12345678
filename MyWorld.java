import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world the player and enemy fight in.
 * 
 * @author Timothy Fung
 * @version 2023-12-11
 */
public class MyWorld extends World
{
    GreenfootSound game = new GreenfootSound("sounds/game.wav");
    
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
        argalia.setRound();
        argalia.reset();
        TitleScreen.title.stop();
        game.playLoop();
        prepare();
    }
    
    /**
     * Creates and prints the game over text
     */
    public void gameOver()
    {
        Label gameOverLabel = new Label("Game Over", 300);
        addObject(gameOverLabel, 600, 200);
        Label tryAgainLabel = new Label("You beat Argalia " + (argalia.getRound() - 1) + " times", 100);
        addObject(tryAgainLabel, 600, 400);
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
        addObject(roland.getDiceLabel(), 0, 1000);
        addObject(argalia.getDiceLabel(), 0, 1000);
        addObject(roland.getAttackCard(), 0, 0);
        addObject(argalia.getAttackCard(), 0, 0);
    }
    
    /**
     * Returns argalia
     */
    public static Argalia getEnemy()
    {
        return argalia;
    }
    
    /**
     * Returns roland
     */
    public static Roland getRoland()
    {
        return roland;
    }
    
    /**
     * Resets instance variables of roland and argalia
     */
    public static void resetAll()
    {
        roland.reset();
        argalia.reset();
    }
}
