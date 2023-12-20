import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main character, controlled by the player.
 * 
 * @author Timothy Fung
 * @version 2023-12-12
 */
public class Roland extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    GreenfootImage idle = new GreenfootImage("images/Roland_Idle.png");
    GreenfootImage move = new GreenfootImage("images/Roland_Move.png");
    GreenfootImage zelkova1 = new GreenfootImage("images/Zelkova_1.png");
    GreenfootImage zelkova2 = new GreenfootImage("images/Zelkova_2.png");
    GreenfootImage currentImage = idle;
    
    boolean facingWest = true;
    Enemy enemy = MyWorld.getEnemy();
    private SimpleTimer timer = new SimpleTimer();
    GreenfootImage[] zelkova = {zelkova1, zelkova2};
    /**
     * Act - do whatever the person1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(currentImage);
        move();
        if(intersects(enemy))
        {
            attack(zelkova);
        }
    }
    
    public void move()
    {
        if(!intersects(enemy))
        {
            currentImage = move;
            turnTowards();
            if(facingWest == true)
            {
                move(-5);
            }
            else
            {
                move(5);
            }
        }
    }
    
    public void turnTowards()
    {
        if(enemy.getX() >= getX() && facingWest == true)
        {
            currentImage.mirrorHorizontally();
            facingWest = false;
        }else if(enemy.getX() <= getX() && facingWest == false)
        {
            currentImage.mirrorHorizontally();
            facingWest = true;
        }
    }
    
    public void attack(GreenfootImage animationFrames[])
    {
        for(int i = 0; i < animationFrames.length; i++)
        {
            timer.mark();
            while(timer.millisElapsed() < 500);
            Log.info(i);
            currentImage = animationFrames[i];
            setImage(currentImage);
            Log.info(currentImage.toString());
        }
    }
}
