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
    GreenfootImage[] zelkova = {new GreenfootImage("images/Zelkova_1.png"), new GreenfootImage("images/Zelkova_2.png")};
    GreenfootImage currentImage = idle;
    
    boolean facingWest = true;
    Enemy enemy = MyWorld.getEnemy();
    private SimpleTimer timer = new SimpleTimer();
    int attackIndex = 0;

    public void act()
    {
        setImage(currentImage);
        move();
        if(intersects(enemy))
        {
            attack(zelkova);
        }
    }
    
    /**
     * Moves to until touching enemy
     */
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
    
    /**
     * Turns towards the enemy
     */
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
    
    /**
     * Animates the attack inputted
     */
    public void attack(GreenfootImage animationFrames[])
    {
        //1000 millis delay
        if(timer.millisElapsed() > 1000)
        {
            currentImage = animationFrames[attackIndex];
            attackIndex = (attackIndex + 1) % animationFrames.length;
            setImage(currentImage);
            timer.mark();
        }
    }
}
