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
    
    //Attack frames
    GreenfootImage[] zelkova = {new GreenfootImage("images/Zelkova_1.png"), new GreenfootImage("images/Zelkova_2.png")};
    GreenfootImage[] ranga = {new GreenfootImage("images/Ranga_1.png"), new GreenfootImage("images/Ranga_2.png"), new GreenfootImage("images/Ranga_3.png")};
    GreenfootImage[] oldBoys = {new GreenfootImage("images/Roland_Block.png"), new GreenfootImage("images/Roland_Blunt.png")};
    GreenfootImage[] allas = {new GreenfootImage("images/Allas_1.png"), new GreenfootImage("images/Allas_1.png")};
    GreenfootImage[] mook = {new GreenfootImage("images/Mook_1.png")};
    GreenfootImage[] atelier = {new GreenfootImage("images/Atelier_1.png"), new GreenfootImage("images/Atelier_2.png"), new GreenfootImage("images/Atelier_3.png")};
    GreenfootImage[] durandal = {new GreenfootImage("images/Durandal_1.png"), new GreenfootImage("images/Durandal_1.png")};
    GreenfootImage[] crystal = {new GreenfootImage("images/Roland_Evade.png"), new GreenfootImage("images/Crystal_2.png"), new GreenfootImage("images/Crystal_2.png"), new GreenfootImage("images/Crystal_2.png")};
    GreenfootImage[] wheels = {new GreenfootImage("images/Wheels_1.png"), new GreenfootImage("images/Roland_Block.png")};
    
    GreenfootImage zelkovaCard = new GreenfootImage("images/Zelkova_Workshop.png");
    GreenfootImage rangaCard = new GreenfootImage("images/Ranga_Workshop.png");
    GreenfootImage oldBoysCard = new GreenfootImage("images/Old_Boys_Workshop.png");
    GreenfootImage allasCard = new GreenfootImage("images/Allas_Workshop.png");
    GreenfootImage mookCard = new GreenfootImage("images/Mook_Workshop.png");
    GreenfootImage atelierCard = new GreenfootImage("images/Atelier_Logic.png");
    GreenfootImage durandalCard = new GreenfootImage("images/Durandal.png");
    GreenfootImage crystalCard = new GreenfootImage("images/Crystal_Atelier.png");
    GreenfootImage wheelsCard = new GreenfootImage("images/Wheels_Industry.png");
    
    //Attack dice (int 1 and 2 are pairs, int 3 and 4 are pairs, etc.)
    //Each attack chooses a random number 
    //between its respective pair (ex. attack 1 rolls between int 1 and int 2)
    int[] zelkovaDice = {5, 11, 5 ,11};
    int[] rangaDice = {3, 8, 3, 8, 3, 8};
    int[] oldBoysDice = {5, 10, 4, 9};
    int[] allasDice = {5, 10, 5, 9};
    int[] mookDice = {8, 16};
    int[] atelierDice = {4, 9, 5, 9, 7, 13};
    int[] durandalDice = {5, 10, 5, 10};
    int[] crystalDice = {8, 12, 7, 12, 7 ,12, 4, 9};
    int[] wheelsDice = {14, 25, 5, 9};
    
    //Attack dice types (1 is attack, 2 is block, 3 is evade)
    int[] zelkovaDiceType = {1, 1};
    int[] rangaDiceType = {1, 1, 1};
    int[] oldBoysDiceType = {2, 1};
    int[] allasDiceType = {1, 1};
    int[] mookDiceType = {1};
    int[] atelierDiceType = {1, 1, 1};
    int[] durandalDiceType = {1, 1};
    int[] crystalDiceType = {3, 1, 1, 1};
    int[] wheelsDiceType = {1, 2};
    
    public void act()
    {
        setHPLabel();
        setImage(currentImage);
        enemy = world.getEnemy();
        // calculateAttack(currentDice, 1);
        if(currentAttack != null)
        {
            calculateAttack(currentDice, 1);
        }
        if(endTurn == true && currentAttack != null)
        {
            turnTowards();
            if(attacking == false)
            {
                move(move);
                // if(diceRoll == 0)
                // {
                    // calculateAttack(currentDice, 1);
                // }
            }
            if(attackIndex == currentAttack.length)
            {
                // diceLabel.setLocation(0, 1000);
                diceRoll = 0;
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
                // calculateAttack(currentDice, 1);
                attack(currentAttack, damaged, currentCard, currentDiceType);

            }
        }
        else if(endTurn == false)
        {
            if(HP <= 0)
            {
                world.gameLose();
            }
            currentImage = idle;
            selectedCard();
            if(Greenfoot.isKeyDown("enter") && currentAttack != null)
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
            currentCard = zelkovaCard;
            currentDice = zelkovaDice;
            currentDiceType = zelkovaDiceType;
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            currentAttack = ranga;
            currentDice = rangaDice;
            currentCard = rangaCard;
            currentDiceType = rangaDiceType;
        }
        else if(Greenfoot.isKeyDown("3"))
        {
            currentAttack = oldBoys;
            currentCard = oldBoysCard;
            currentDice = oldBoysDice;
            currentDiceType = oldBoysDiceType;
        }
        else if(Greenfoot.isKeyDown("4"))
        {
            currentAttack = allas;
            currentCard = allasCard;
            currentDice = allasDice;
            currentDiceType = allasDiceType;
        }
        else if(Greenfoot.isKeyDown("5"))
        {
            currentAttack = mook;
            currentCard = mookCard;
            currentDice = mookDice;
            currentDiceType = mookDiceType;
        }
        else if(Greenfoot.isKeyDown("6"))
        {
            currentAttack = atelier;
            currentCard = atelierCard;
            currentDice = atelierDice;
            currentDiceType = atelierDiceType;
        }
        else if(Greenfoot.isKeyDown("7"))
        {
            currentAttack = durandal;
            currentCard = durandalCard;
            currentDice = durandalDice;
            currentDiceType = durandalDiceType;
        }
        else if(Greenfoot.isKeyDown("8"))
        {
            currentAttack = crystal;
            currentCard = crystalCard;
            currentDice = crystalDice;
            currentDiceType = crystalDiceType;
        }
        else if(Greenfoot.isKeyDown("9"))
        {
            currentAttack = wheels;
            currentCard = wheelsCard;
            currentDice = wheelsDice;
            currentDiceType = wheelsDiceType;
        }
        if(currentCard != null)
        {
            attackCard.setImage(currentCard);
            attackCard.setLocation(1110, 145);
        }
    }
    
    /**
     * Resets instance variables
     */
    public void reset()
    {
        endTurn = false;
        currentImage = idle;
        setLocation(950, 375);
        entityReset();
    }
}
