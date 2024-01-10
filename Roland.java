import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main character, controlled by the player.
 * 
 * @author Timothy Fung
 * @version 2023-12-12
 */
public class Roland extends Entity
{
    GreenfootImage idle = new GreenfootImage("images/Roland_Idle.png");
    GreenfootImage move = new GreenfootImage("images/Roland_Move.png");
    GreenfootImage[] zelkova = {new GreenfootImage("images/Zelkova_1.png"), new GreenfootImage("images/Zelkova_2.png")};
    
    boolean endTurn = false;
    
    public void act()
    {
        setHPLabel();
        setImage(currentImage);
        setEnemy(world.getEnemy());
        Log.info(endTurn);
        if(endTurn == true && currentAttack != null)
        {
            move(move);
            if(attackIndex == currentAttack.length && timer.millisElapsed() >= 1000)
            {
                reset();
            }else if(intersects(enemy))
            {
                attack(currentAttack);
            }
            
        }
        else
        {
            currentImage = idle;
            selectedCard();
            if(Greenfoot.isKeyDown("enter"))
            {
                endTurn = true;
            }
        }
    }
    
    public void selectedCard()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            currentAttack = zelkova;
        }
    }
    
    public void reset()
    {
        endTurn = false;
        currentImage = idle;
        currentAttack = attackReset;
        attackIndex = 0;
    }
}
