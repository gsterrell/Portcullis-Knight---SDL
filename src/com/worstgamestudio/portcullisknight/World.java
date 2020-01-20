package com.worstgamestudio.portcullisknight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.worstgamestudio.framework.math.OverlapTester;
import com.worstgamestudio.framework.math.Vector2;

public class World 
{
    public interface WorldListener 
    {
		public void deathFlame();
		public void stand();
    	public void run();
    }

    public static final float WORLD_WIDTH = Values.SCREEN_WIDTH;
    public static final float WORLD_HEIGHT = Values.SCREEN_HEIGHT;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;
    public static final int startPositionX = (Values.SCREEN_WIDTH /2)-Values.KNIGHT_WIDTH;
    public static final Vector2 gravity = new Vector2(0, -400);

    public final Knight knight;
    public final List<Ground> theGround;
    public final List<Enemy> enemies;
    public final List<Pillar> pillars;
    public final WorldListener listener;
    public final Random rand;

    public int score;
    public int state;

    public World(WorldListener listener) 
    {
    	this.knight = new Knight((Values.SCREEN_WIDTH/ 2)-Values.KNIGHT_WIDTH, (Values.SCREEN_HEIGHT/2));
        this.theGround = new ArrayList<Ground>();
        this.enemies = new ArrayList<Enemy>();
        this.pillars = new ArrayList<Pillar>();
        this.listener = listener;
        rand = new Random();
        generateLevel();
     
        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }
    
    public void generateLevel()
    {
    	//for(int g = 0; g < 30; g++)
    	//for(int g = 0; g < ((Values.SCREEN_WIDTH % Values.GRASS_WIDTH)*10); g++)
    	for(int g = 0; g < /*((Values.SCREEN_WIDTH % Values.GRASS_WIDTH)*10)*/100; g++)
    	{
    		Ground newGround = new Ground(Ground.GROUND_TYPE_SOLID, (g*Values.GRASS_WIDTH)-(knight.position.x), (Values.GRASS_HEIGHT/2));
    		//Ground newGround = new Ground(Ground.GROUND_TYPE_SOLID,(g*Values.BRIDGE_WIDTH), Values.BRIDGE_HEIGHT);
    		//Ground newGround = new Ground(Ground.GROUND_TYPE_SOLID,/*((g*Values.GRASS_WIDTH)-knight.position.x)*/knight.position.x - (Values.GRASS_WIDTH/2), (100 - (Values.GRASS_HEIGHT/2)));
    		theGround.add(newGround);
    	}
    	
    	for(int e = 0; e < 20; e++)
    	{    		
	    	Enemy theEnemy = new Enemy((knight.position.x + 200) * (1+e), 200, Enemy.ENEMY_TYPE_BAT);
	    	if(knight.position.x >= theEnemy.position.x)
	    	{
	    		theEnemy.right = true;
	    		theEnemy.left = false;
	    	}
	    	else
	    	{
	    		theEnemy.left = true;
	    		theEnemy.right = false;
	    	}
	    	enemies.add(theEnemy);
    	}
    	
    	/*Modify later for more enemies
    	for(int e = 0; e < enemies.size(); e++)
    	{
    		Enemy theEnemy = new Enemy(1000, (Values.GRASS_HEIGHT/2), Enemy.ENEMY_TYPE_BAT);
    		enemies.add(theEnemy);
    	}
    	
    	
    	//for(int p = 0; p < 10; p++)
    	for(int p = 0; p < ((Values.SCREEN_WIDTH % Values.PILLAR_WIDTH)*30); p++)
    	{
    		//Pillar pillar = new Pillar(Pillar.PILLAR_TYPE_SOLID,((p*3)*Values.PILLAR_WIDTH), Values.PILLAR_HEIGHT);
    		Pillar pillar = new Pillar(Pillar.PILLAR_TYPE_SOLID,(((p*3)*Values.PILLAR_WIDTH)-knight.position.x), (100 - (Values.PILLAR_HEIGHT/2)));
    		pillars.add(pillar);
    	}
    	*/
    }

	public void update(float deltaTime, float velocityX, float velocityY) 
	{
		updateKnight(deltaTime, velocityX, velocityY);
		updateEnemies(deltaTime, velocityX, velocityY);
		
		//if (knight.state != Knight.KNIGHT_STATE_HITLEFT && knight.state != Knight.KNIGHT_STATE_HITRIGHT)
			checkCollisions();
		//checkGameOver();
	}

	private void updateKnight(float deltaTime, float velocityX, float velocityY) 
	{
		knight.update(deltaTime, velocityX, velocityY);
		//Maybe add some checking here?
		knight.loopEnded = Assets.knightAttack.loopEnd;
	}
	
	private void updateEnemies(float deltaTime, float velocityX, float velocityY) 
	{
		int len = enemies.size();
		for(int e = 0; e < len; e++)
		{
			Enemy enemy = enemies.get(e);
			//enemies.get(e).update(deltaTime, velocityX, velocityY);
			enemy.update(deltaTime, velocityX, velocityY);

			if( enemy.state == Enemy.ENEMY_STATE_DEAD
					&& enemy.stateTime < Enemy.DEATH_FLAME_TIME)
			{
				enemies.remove(e);
				e--;
				len--;
			}
		}
	}
	
	private void checkCollisions() 
	{
		checkObjectCollisions();
		checkEnemyCollisions();
	}
	
	private void checkObjectCollisions()
	{
		int groundLen = theGround.size();
		for(int g = 0; g < groundLen; g++)
		{
			Ground ground = theGround.get(g);
			//if(knight.position.y >= ground.position.y)
			//{
				if(OverlapTester.overlapRectangles(knight.bounds, ground.bounds))
				{
					knight.stopFalling();
				}
			//}
		}
		/*
		int pillarLen = pillars.size();
		for(int p = 0; p < pillarLen; p++)
		{
			Pillar pillar = pillars.get(p);
			if(knight.position.y > pillar.position.y)
			{
				if(OverlapTester.overlapRectangles(knight.bounds, pillar.bounds))
				{
					knight.stopFalling();
				}
			}
		}
		*/
	}
	
	private void checkEnemyCollisions()
	{
		for(int e = 0; e < enemies.size(); e++)
		{
			Enemy theEnemy = enemies.get(e);
			//if(knight.position.y >= ground.position.y)
			//{
				if(OverlapTester.overlapRectangles(knight.bounds, theEnemy.bounds))
				{
					if(knight.state == Knight.KNIGHT_STATE_ATTACK)
					{
						theEnemy.kill();
						//listener.deathFlame();
					}
					
					else
					{
						knight.health -= 1;
						
						if(theEnemy.position.x <= knight.position.x)
							knight.state = Knight.KNIGHT_STATE_HITLEFT;
						else
							knight.state = Knight.KNIGHT_STATE_HITRIGHT;
						
						knight.stateTime = 0;
						
						//knight.position.y -= 20;
					}
				}
			//}
		}
	}
	
    private void checkGameOver() 
    {
        state = WORLD_STATE_GAME_OVER;
    }
}