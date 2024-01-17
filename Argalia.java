import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author Timothy Fung
 * @version 2024-01-16
 */
public class Argalia extends Enemy
{
    GreenfootImage idle = new GreenfootImage("images/Argalia_Idle.png");
    GreenfootImage move = new GreenfootImage("images/Argalia_Move.png");
    GreenfootImage damaged = new GreenfootImage("images/Argalia_Damaged.png");
    GreenfootImage evade = new GreenfootImage("images/Argalia_Evade.png");
    GreenfootImage block = new GreenfootImage("images/Argalia_Block.png");
    GreenfootImage blunt = new GreenfootImage("images/Argalia_Blunt.png");
    GreenfootImage slash = new GreenfootImage("images/Argalia_Slash.png");
    
    //Attack frames
    GreenfootImage[] scout = {evade, blunt, block, block};
    GreenfootImage[] preludio = {block, blunt, block, slash};
    GreenfootImage[] trails = {slash, block, blunt, block};
    
    //Attack dice (int 1 and 2 are pairs, int 3 and 4 are pairs, etc.)
    //Each attack chooses a random number 
    //between its respective pair (ex. attack 1 rolls between int 1 and int 2)
    int[] scoutDice = {5, 9, 4 ,7, 5, 9, 5, 8};
    int[] preludioDice = {4, 7, 3, 6, 5, 8, 5, 8};
    int[] trailsDice = {8, 14, 5, 8, 5, 8, 5, 8};
    
    //Attack dice types (1 is attack, 2 is block, 3 is evade)
    int[] scoutDiceType = {3, 1, 2, 2};
    int[] preludioDiceType = {2, 1, 2, 1};
    int[] trailsDiceType = {1, 2, 1, 2};
    
    //Variable for deciding which attack is chosen
    int randomAttack;
    
    public void act()
    {
        setHPLabel();
        setImage(currentImage);
        enemy = MyWorld.getRoland();
        if(endTurn == true && currentAttack != null)
        {
            turnTowards();
            if(attacking == false)
            {
                move(move);
            }
            if(enemy.currentAttack != null && attackIndex == currentAttack.length && enemy.attackIndex == enemy.currentAttack.length && timer.millisElapsed() >= 1000)
            {
                MyWorld.resetAll();
            }
            else if(intersects(enemy))
            {
                attacking = true;
            }
            if(attacking == true)
            {
                attack(currentAttack, damaged, currentDice, currentDiceType);
            }
        }
        else
        {
            currentImage = idle;
            if(Greenfoot.isKeyDown("enter") && enemy.currentAttack != null)
            {
                endTurn = true;
                randomAttack = random.nextInt(4 - 1) + 1;
                if(randomAttack == 1)
                {
                    currentAttack = scout;
                    currentDice = scoutDice;
                    currentDiceType = scoutDiceType;
                }
                else if(randomAttack == 2)
                {
                    currentAttack = preludio;
                    currentDice = preludioDice;
                    currentDiceType = preludioDiceType;
                }
                else
                {
                    currentAttack = trails;
                    currentDice = trailsDice;
                    currentDiceType = trailsDiceType;
                }
            }
        }
    }
    
    /**
     * Resets instance variables
     */
    public void reset()
    {
        endTurn = false;
        facingWest = false;
        currentImage = idle;
        setLocation(250, 375);
        entityReset();
    }
}
