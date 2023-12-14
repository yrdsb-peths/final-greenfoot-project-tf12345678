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
    public void act()
    {
        setImage(idle);
        move();
    }
    
    public void move()
    {
        if(!intersects(MyWorld.getEnemy()))
        {
            turnTowards(MyWorld.getEnemy());
            move(-1);
        }
    }
    
    public void turnTowards(Actor enemy)
    {
        if(enemy.getX() >= getX())
        {
            idle.mirrorHorizontally();
        }
    }
}
