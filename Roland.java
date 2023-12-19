import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class person1 here.
 * 
 * @author Timothy Fung
 * @version 2023-12-12
 */
public class Roland extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    GreenfootImage idle = new GreenfootImage("images/Roland_Idle.png");
    GreenfootImage move = new GreenfootImage("images/Roland_Move.png");
    GreenfootImage zelkova1 = new GreenfootImage("images/Zelkova_1.png");
    GreenfootImage zelkova2 = new GreenfootImage("images/Zelkova_2.png");
    GreenfootImage currentImage = idle;
    
    boolean facingWest = true;
    Enemy enemy = MyWorld.getEnemy();
    int[] attackList = new int[3];
    int attackCount = 0;
    boolean performingAttack = false;
    private SimpleTimer timer = new SimpleTimer();
    GreenfootImage[] zelkova = {zelkova1, zelkova2};
    /**
     * Act - do whatever the person1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(currentImage);
        move();
        if(intersects(enemy))
        {
            attack(attackList);
        }
    }
    
    public void move()
    {
        if(!intersects(enemy))
        {
            currentImage = move;
            turnTowards();
            if(facingWest == true)
            {
                move(-5);
            }
            else
            {
                move(5);
            }
        }
        else
        {
            currentImage = idle;
            if(attackCount != 1)
            {
                attackList[attackCount] += 1;
                attackCount += 1;
            }
        }
    }
    
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
    
    public void attack(int atkList[])
    {
        for(int i = 0; i < atkList.length; i++)
        {
            performingAttack = true;
            if(atkList[i] == 1)
            {
                zelkovaWorkshop();
            }
        }
    }
    
    public void zelkovaWorkshop()
    {
        for(int i = 0; i < 2; i++)
        {
            timer.mark();
            while(timer.millisElapsed() < 500);
            Log.info(i);
            setImage(zelkova[i]);
        }
        
        // timer.mark();
        // while(timer.millisElapsed() < 500)
        // {
            
        // }
        // setImage(zelkova1);
        // enemy.setHP(-30);
        // timer.mark();
        // while(timer.millisElapsed() < 500)
        // {
            // Log.info(timer.millisElapsed());
        // }
        // setImage(zelkova2);
        // enemy.setHP(-30);
        
        // while(timer.millisElapsed() < 1000)
        // {
            
            // if(timer.millisElapsed() == 1000)
            // {
                // currentImage = zelkova2;
                // setImage(currentImage);
                // Log.info(currentImage.toString());
                // enemy.setHP(-30);
                // performingAttack = false;
            // }
            // if(timer.millisElapsed() == 500)
            // {
                // currentImage = zelkova1;
                // setImage(currentImage);
                // Log.info(currentImage.toString());
                // enemy.setHP(-30);
            // }
        // }
    }
}
