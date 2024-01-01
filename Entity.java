import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    boolean facingWest = true;
    Enemy enemy = MyWorld.getEnemy();
    private SimpleTimer timer = new SimpleTimer();
    int attackIndex = 0;
    
    GreenfootImage currentImage;
    
    int HP;
    Label HPLabel;
    
    public void act()
    {
        
    }
    
    /**
     * Moves to until touching enemy
     */
    public void move(GreenfootImage image)
    {
        if(!intersects(enemy))
        {
            currentImage = image;
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
        if(timer.millisElapsed() >= 1000)
        {
            currentImage = animationFrames[attackIndex];
            attackIndex = (attackIndex + 1) % animationFrames.length;
            setImage(currentImage);
            timer.mark();
        }
    }
    
    public void setHP(int hp)
    {
        HP = hp;
    }
    
    public void reduceHP(int hp)
    {
        HP -= hp;
    }
}
