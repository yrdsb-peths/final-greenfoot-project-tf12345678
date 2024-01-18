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
    GreenfootImage currentCard;
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
    
    Label diceLabel = new Label(0, 50);
    
    Card attackCard = new Card();
    
    public void act()
    {
        
    }
    
    /**
     * Resets instance variables and moves the diceLabel out of the way
     */
    public void entityReset()
    {
        attackIndex = 0;
        currentAttack = attackReset;
        currentDice = diceReset;
        currentDiceType = diceReset;
        diceRoll = 0;
        clashLost = false;
        attacking = false;
        diceLabel.setLocation(0, 1000);
    }
    
    /**
     * Sets the HP instance variable
     */
    public void setHP(int hp)
    {
        HP = hp;
    }
    
    /**
     * Returns the HP instance variable
     */
    public int getHP()
    {
        return HP;
    }
    
    /**
     * Sets the value of the HPLabel actor and sets its color to red
     */
    public void setHPLabel()
    {
        HPLabel.setValue(HP);
        HPLabel.setFillColor(Color.RED);
    }
    
    /**
     * Returns the HPLabel actor
     */
    public Label getHPLabel()
    {
        return HPLabel;
    }
    
    public void setDiceLabel(int label)
    {
        diceLabel.setValue(label);
        diceLabel.setLocation(getX(), getY() - currentImage.getHeight() - 10);
    }

    /**
     * Returns the diceLabel actor
     */
    public Label getDiceLabel()
    {
        return diceLabel;
    }
    
    public Card getAttackCard()
    {
        return attackCard;
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
     * Animates the attack inputted and deals/negates damage
     */
    public void attack(GreenfootImage[] animationFrames, GreenfootImage damageSprite, GreenfootImage card, int[] dice, int[] diceType)
    {
        //1000 millis delay
        if(timer.millisElapsed() >= 1000 && attackIndex != currentAttack.length)
        {
            lower = dice[attackIndex * 2];
            upper = dice[attackIndex * 2 + 1];
            //Roll a random number between lower (inclusive) and upper (exclusive)
            diceRoll = random.nextInt(upper - lower) + lower;
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
                currentImage = animationFrames[attackIndex];
                if(diceRoll > enemy.diceRoll)
                {
                    enemy.diceRoll = 0;
                }
                else
                {
                    enemy.diceRoll -= diceRoll;
                }
            }
            else if(clashLost == false)
            {
                currentImage = animationFrames[attackIndex];
                enemy.diceRoll = 0;
            }
            else
            {
                currentImage = damageSprite;
            }
            
            setDiceLabel(diceRoll);
            attackIndex ++;
            setImage(currentImage);
            timer.mark();
        }
    }
    
    /**
     * Sets the clashLost boolean depending on whether the entity rolled 
     * >, >= or < than the opposing entity
     */
    public void clash(GreenfootImage damageSprite)
    {
        if(enemy.diceRoll == diceRoll)
        {
            clashLost = true;
        }else if(enemy.diceRoll > diceRoll)
        {
            clashLost = true;
        }else
        {
            clashLost = false;
        }
    }
    
    /**
     * Subtracts the HP variable by the amount of damage dealt
     */
    public void dealDamage(int damage)
    {
        HP -= damage;
    }
}
