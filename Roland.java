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
    GreenfootImage damaged = new GreenfootImage("images/Roland_Damaged.png");
    
    GreenfootImage[] zelkova = {new GreenfootImage("images/Zelkova_1.png"), new GreenfootImage("images/Zelkova_2.png")};
    GreenfootImage[] ranga = {new GreenfootImage("images/Ranga_1.png"), new GreenfootImage("images/Ranga_2.png"), new GreenfootImage("images/Ranga_3.png")};
    GreenfootImage[] oldBoys = {new GreenfootImage("images/Roland_Block.png"), new GreenfootImage("images/Roland_Blunt.png")};
    GreenfootImage[] allas = {new GreenfootImage("images/Allas_1.png"), new GreenfootImage("images/Allas_1.png")};
    GreenfootImage[] mook = {new GreenfootImage("images/Mook_1.png")};
    GreenfootImage[] ateleier = {new GreenfootImage("images/Atelier_1.png"), new GreenfootImage("images/Atelier_2.png"), new GreenfootImage("images/Atelier_3.png")};
    GreenfootImage[] durandal = {new GreenfootImage("images/Durandal_1.png"), new GreenfootImage("images/Durandal_1.png")};
    GreenfootImage[] crystal = {new GreenfootImage("images/Roland_Evade.png"), new GreenfootImage("images/Crystal_2.png"), new GreenfootImage("images/Crystal_2.png")};
    GreenfootImage[] wheels = {new GreenfootImage("images/Wheels_1.png")};
    
    int[] zelkovaDice = {5, 10, 5 ,10};
    
    //Attack dice types (1 is attack, 2 is block, 3 is evade)
    int[] zelkovaDiceType = {1, 1};
    
    
    
    public void act()
    {
        setHPLabel();
        setImage(currentImage);
        enemy = world.getEnemy();
        if(endTurn == true && currentAttack != null)
        {
            turnTowards();
            move(move);
            if(attackIndex == currentAttack.length && timer.millisElapsed() >= 1000)
            {
                reset();
            }else if(intersects(enemy))
            {
                attack(currentAttack, damaged, currentDice, currentDiceType);
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
            currentDice = zelkovaDice;
            currentDiceType = zelkovaDiceType;
        }
    }
    
    public void reset()
    {
        endTurn = false;
        currentImage = idle;
        entityReset();
    }
}
