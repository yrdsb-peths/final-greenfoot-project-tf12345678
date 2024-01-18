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
    GreenfootImage[] dissonance = {evade, blunt, block, block};
    GreenfootImage[] largo = {block, blunt, slash, slash};
    GreenfootImage[] trails = {slash, block, blunt, block};
    
    GreenfootImage dissonanceCard = new GreenfootImage("images/Dissonance.png");
    GreenfootImage largoCard = new GreenfootImage("images/Largo.png");
    GreenfootImage trailsCard = new GreenfootImage("images/Trails_of_Blue.png");
    
    //Attack dice (int 1 and 2 are pairs, int 3 and 4 are pairs, etc.)
    //Each attack chooses a random number 
    //between its respective pair (ex. attack 1 rolls between int 1 and int 2)
    int[] dissonanceDice = {5, 9, 4 ,7, 5, 9, 5, 8};
    int[] largoDice = {4, 7, 3, 6, 5, 8, 5, 8};
    int[] trailsDice = {8, 14, 5, 8, 5, 8, 5, 8};
    
    //Attack dice types (1 is attack, 2 is block, 3 is evade)
    int[] dissonanceDiceType = {3, 1, 2, 2};
    int[] largoDiceType = {2, 1, 1, 1};
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
            if(attackIndex == currentAttack.length)
            {
                diceLabel.setLocation(0, 1000);
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
                attack(currentAttack, damaged, currentCard, currentDice, currentDiceType);
            }
        }
        else
        {
            currentImage = idle;
            if(randomAttack == 0)
            {
                randomAttack = random.nextInt(4 - 1) + 1;
                if(randomAttack == 1)
                {
                    currentAttack = dissonance;
                    currentCard = dissonanceCard;
                    currentDice = dissonanceDice;
                    currentDiceType = dissonanceDiceType;
                }
                else if(randomAttack == 2)
                {
                    currentAttack = largo;
                    currentCard = largoCard;
                    currentDice = largoDice;
                    currentDiceType = largoDiceType;
                }
                else
                {
                    currentAttack = trails;
                    currentCard = trailsCard;
                    currentDice = trailsDice;
                    currentDiceType = trailsDiceType;
                }
                attackCard.setImage(currentCard);
                attackCard.setLocation(90, 145);
            }
            if(Greenfoot.isKeyDown("enter") && enemy.currentAttack != null)
            {
                endTurn = true;
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
        randomAttack = 0;
        currentImage = idle;
        setLocation(250, 375);
        entityReset();
    }
}
