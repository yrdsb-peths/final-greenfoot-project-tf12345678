import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test extends Enemy
{
    GreenfootImage apple = new GreenfootImage("images/apple1.png");
    GreenfootImage[] appleList = {new GreenfootImage("images/apple1.png"), new GreenfootImage("images/apple1.png")};
    int[] appleDice = {20, 21, 20, 21};
    int[] appleDiceType = {1, 1};
    
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setHPLabel();
        enemy = MyWorld.getRoland();
        if(enemy.endTurn == true && currentAttack != null)
        {
            turnTowards();
            if(attacking == false)
            {
                move(apple);
            }
            if(attackIndex == currentAttack.length && timer.millisElapsed() >= 1000)
            {
                reset();
            }
            else if(intersects(enemy))
            {
                attacking = true;
            }
            if(attacking == true)
            {
                attack(currentAttack, apple, currentDice, currentDiceType);
            }
        }
        else
        {
            currentImage = apple;
            if(Greenfoot.isKeyDown("enter") && endTurn == false)
            {
                currentAttack = appleList;
                currentDice = appleDice;
                currentDiceType = appleDiceType;
            }
        }
    }
    
    public void reset()
    {
        setLocation(250, 375);
        entityReset();
    }
}
