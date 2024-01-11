import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class entity here.
 * 
 * @author Timothy Fung 
 * @version 2024-01-1
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
    int[] currentDamage;
    int[] currentDice;
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
        HPLabel.setFillColor(Color.RED);
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
    
    int diceRoll;
    int upper;
    int lower;
    Random random = new Random();
    /**
     * Animates the attack inputted
     */
    public void attack(GreenfootImage[] animationFrames, int[] damage, int[] dice)
    {
        //1000 millis delay
        if(timer.millisElapsed() >= 1000)
        {
            lower = dice[attackIndex * 2];
            upper = dice[attackIndex * 2 + 1];
            //Roll a random number between lower(inclusive) and upper(exclusive)
            diceRoll = random.nextInt(upper - lower) + lower;
            currentImage = animationFrames[attackIndex];
            enemy.dealDamage(damage[attackIndex] + diceRoll);
            
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
