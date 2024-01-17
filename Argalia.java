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
    
    GreenfootImage[] scout = {evade, blunt, block, block};
    GreenfootImage[] preludio = {slash, blunt, block, block};
    GreenfootImage[] trails = {slash, block, block, block};
    
    int[] scoutDice = {5, 9, 4 ,7, 5, 9, 5, 8};
    int[] preludioDice = {4, 7, 3, 6, 5, 8, 5, 8};
    int[] trailsDice = {8, 14, 5, 8, 5, 8, 5, 8};
    
    int[] scoutDiceType = {3, 1, 2, 2};
    int[] preludioDiceType = {1, 1, 2, 2};
    int[] trailsDiceType = {1, 2, 2, 2};
    
    int randomAttack;
    
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
            if(attackIndex == currentAttack.length && enemy.attackIndex == enemy.currentAttack.length && timer.millisElapsed() >= 1000)
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
            if(Greenfoot.isKeyDown("enter") && endTurn == false)
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
    
    public void reset()
    {
        endTurn = false;
        facingWest = false;
        setLocation(250, 375);
        entityReset();
    }
}
