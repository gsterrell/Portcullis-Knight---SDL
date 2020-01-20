package com.worstgamestudio.portcullisknight;

import com.worstgamestudio.framework.DynamicGameObject; 

import java.util.Random;

class Enemy extends DynamicGameObject 
{	
	public static final int BASE_WIDTH = 32;
	public static final int BASE_HEIGHT = 32;
	
	public static final int ENEMY_TYPE_BAT = 0;
	public static final int ENEMY_TYPE_WOLF = 1;

	static final float DEATH_FLAME_TIME = 1.6f;

	public static final String ENEMY_TYPE_NAME[] = {"BAT", "WOLF"};
	public static final int ENEMY_TYPE_WIDTH[] = {44, 64};
	public static final int ENEMY_TYPE_HEIGHT[] = {32, 32};
	
	public static final float ENEMY_WIDTH = BASE_WIDTH;
	public static final float ENEMY_HEIGHT = BASE_WIDTH;
	public static final int ENEMY_STATE_STAND = 0;
	public static final int ENEMY_STATE_RUN = 1;
	public static final int ENEMY_STATE_JUMP = 2;
	public static final int ENEMY_STATE_STRIKE = 3;
	public static final int ENEMY_STATE_HIT = 4;
	public static final int ENEMY_STATE_FALLING = 5;
	public static final int ENEMY_STATE_DEAD = 6;
	
	public static final float ENEMY_RUN_VELOCITY = 100;
	public static final float ENEMY_JUMP_VELOCITY = 200;
	public static final float ENEMY_MAX_RUN_VELOCITY = 100;
	public static final float ENEMY_MAX_JUMP_VELOCITY = 150;
	public static final float ENEMY_MAX_FALL_VELOCITY = -300;
	
	int state;
	public int health;
	public static boolean left;
	public static boolean right;
	public static boolean collided;
	public static boolean jump;
	public static boolean hit;
	public static boolean run;
	public static boolean stand;
	public static int type;
	float stateTime;
	static Random rand;
		
	public Enemy(float x, float y, int type) 
	{
		super(x, y, ENEMY_TYPE_WIDTH[type], ENEMY_TYPE_HEIGHT[type]);
		this.type = type;
		state = ENEMY_STATE_RUN;
		stateTime = 0;
		
		left = false;
		right = false;
		collided = false;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY) 
	{
		if(state == ENEMY_STATE_RUN)
		{
			if(right == true)
				velocity.x = ENEMY_RUN_VELOCITY;
			
			else
				velocity.x = -ENEMY_RUN_VELOCITY;
		}
			
		if(state == ENEMY_STATE_JUMP && collided == true)
		{
			velocity.y = ENEMY_JUMP_VELOCITY;
			collided = false;
		}	
			
		if(right == false && left == false && jump == false)
			state = ENEMY_STATE_STAND;
		
		if(state == ENEMY_STATE_STAND)
			velocity.x = 0;
				
			/*
			if(velocity.y < ENEMY_MAX_FALL_VELOCITY)
			{
				velocity.set(velocity.x, ENEMY_MAX_FALL_VELOCITY);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			}
			
			if(velocity.x > ENEMY_MAX_RUN_VELOCITY)
			{
				velocity.set(ENEMY_MAX_RUN_VELOCITY, velocity.y);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);  
			}
			*/
			//else
			//{
		if(collided == false)
			velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
		else
			velocity.add(World.gravity.x * deltaTime, 0);
		
		if(type == Enemy.ENEMY_TYPE_BAT)
		{
			velocity.y = 0;
		}
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		
		bounds.lowerLeft.set(position).sub(ENEMY_WIDTH/2, ENEMY_HEIGHT/ 2);
    		
		stateTime += deltaTime;
		
		//collided = false;
		//jump = false;
		//hit = false;
		//run = false;
		//stand = true;
		
		//Controls work great with this, but no run animation!
		//state = ENEMY_STATE_STAND;
	}
	
	public void stopFalling()
	{
		if(velocity.y != 0)
		{
			velocity.y = 0;
			collided = true;
			state = ENEMY_STATE_STAND;
		}
	}

	public void kill()
	{
		state = ENEMY_STATE_DEAD;
		stateTime = 0;
	}
}