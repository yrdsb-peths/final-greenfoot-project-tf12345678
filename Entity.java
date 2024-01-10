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
    Enemy enemy;
    SimpleTimer timer = new SimpleTimer();
    int attackIndex = 0;
    
    GreenfootImage currentImage;
    GreenfootImage[] currentAttack;
    GreenfootImage[] attackReset;
    
    int HP;
    Label HPLabel = new Label(0, 50);
    
    public void act()
    {
        
    }
    
    public void setHP(int hp)
    {
        HP = hp;
    }
    
    public int getHP()
    {
        return HP;
    }
    
    public void setEnemy(Enemy enemy)
    {
        this.enemy = enemy;
    }
    
    public void setHPLabel()
    {
        HPLabel.setValue(HP);
    }
    
    public Label getHPLabel()
    {
        return HPLabel;
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
                move(-10);
            }
            else
            {
                move(10);
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
    public void attack(GreenfootImage[] animationFrames)
    {
        //1000 millis delay
        if(timer.millisElapsed() >= 1000)
        {
            currentImage = animationFrames[attackIndex];
            attackIndex ++;
            setImage(currentImage);
            timer.mark();
        }
    }
    
    public void dealDamage(int damage)
    {
        HP -= damage;
    }
}
