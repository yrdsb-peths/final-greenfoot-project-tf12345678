import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class person1 here.
 * 
 * @author Timothy Fung
 * @version 2023-12-12
 */
public class Person1 extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    /**
     * Act - do whatever the person1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int enemyXPosition = 100;
    public void act()
    {
        move(getOneObjectAtOffset(enemyXPosition, 0, Test.class));
    }
    
    public void move(Actor enemy)
    {
        turnTowards(enemy.getX(), enemy.getY());
        if(!intersects(enemy))
        {
            move(1);
            enemyXPosition --;
        }
    }
}
