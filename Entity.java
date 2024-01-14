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
    Entity enemy;
    SimpleTimer timer = new SimpleTimer();
    int attackIndex;
    boolean endTurn;
    boolean attacking;
    
    GreenfootImage currentImage;
    GreenfootImage[] currentAttack;
    GreenfootImage[] attackReset;
    int[] currentDice;
    int[] currentDiceType;
    int[] diceReset;
    int diceRoll;
    boolean clashLost;
    int upper;
    int lower;
    Random random = new Random();
    
    int HP;
    Label HPLabel = new Label(0, 50);
    
    public void act()
    {
        
    }
    
    public void entityReset()
    {
        attackIndex = 0;
        currentAttack = attackReset;
        currentDice = diceReset;
        currentDiceType = diceReset;
        diceRoll = 0;
        clashLost = false;
        attacking = false;
    }
    
    public void setHP(int hp)
    {
        HP = hp;
    }
    
    public int getHP()
    {
        return HP;
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
    
    /**
     * Animates the attack inputted
     */
    public void attack(GreenfootImage[] animationFrames, GreenfootImage damageSprite, int[] dice, int[] diceType)
    {
        //1000 millis delay
        if(timer.millisElapsed() >= 1000)
        {
            lower = dice[attackIndex * 2];
            // Log.info(enemy + "attack index is" + enemy.attackIndex);
            // Log.info("");
            // Log.info(enemy + "lower is" + enemy.lower);
            // Log.info("");
            upper = dice[attackIndex * 2 + 1];
            // Log.info("");
            // Log.info(enemy + "upper is" + enemy.upper);
            // Log.info("");
            //Roll a random number between lower(inclusive) and upper(exclusive)
            // Log.info(enemy + "pre-roll is" + enemy.diceRoll);
            // Log.info("");
            diceRoll = random.nextInt(upper - lower) + lower;
            // Log.info(enemy + "post-roll is" + enemy.diceRoll);
            // Log.info("");
            currentImage = animationFrames[attackIndex];
            
            if(enemy.currentAttack != null)
            {
                clash(damageSprite);
            }
            
            if(diceType[attackIndex] == 1 && clashLost == false)
            {
                enemy.dealDamage(diceRoll);
            }
            else if(diceType[attackIndex] == 2 && enemy.currentAttack != null)
            {
                if(diceRoll > enemy.diceRoll)
                {
                    heal(enemy.diceRoll);
                }
                else
                {
                    heal(diceRoll);
                }
            }
            else if(clashLost == false)
            {
                enemy.diceRoll = 0;
            }
            // Log.info(enemy + "clash status is" + enemy.clashLost);
            // Log.info("");
            attackIndex ++;
            setImage(currentImage);
            timer.mark();
        }
    }
    
    /**
     * Makes Entity deal zero damage if it rolls the same as the enemy Entity, 
     * Entity deals zero damage and switches to its damaged sprite
     */
    public void clash(GreenfootImage damageSprite)
    {
        if(enemy.diceRoll == diceRoll)
        {
            clashLost = true;
        }else if(enemy.diceRoll > diceRoll)
        {
            clashLost = true;
            currentImage = damageSprite;
        }else
        {
            clashLost = false;
        }
    }
    
    public void dealDamage(int damage)
    {
        HP -= damage;
    }
    
    public void heal(int hp)
    {
        HP += hp;
    }
}
