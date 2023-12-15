import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class person1 here.
 * 
 * @author Timothy Fung
 * @version 2023-12-12
 */
public class Roland extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    /**
     * Act - do whatever the person1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage idle = new GreenfootImage("images/Roland_Idle.png");
    GreenfootImage move = new GreenfootImage("images/Roland_Move.png");
    GreenfootImage currentImage = idle;
    boolean facingWest = true;
    Enemy enemy = MyWorld.getEnemy();
    public void act()
    {
        setImage(idle);
        move();
    }
    
    public void move()
    {
        if(!intersects(enemy))
        {
            turnTowards();
            if(facingWest == true)
            {
                move(-1);
            }
            else
            {
                move(1);
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
}
