package com.worstgamestudio.portcullisknight;

import com.worstgamestudio.framework.DynamicGameObject; 
import com.worstgamestudio.framework.gl.Animation;

import java.util.Random;

class Knight extends DynamicGameObject 
{	 
	public static final float KNIGHT_WIDTH = Values.KNIGHT_WIDTH;
	public static final float KNIGHT_HEIGHT = Values.KNIGHT_HEIGHT;
	public static final int KNIGHT_STATE_STAND = 0;
	public static final int KNIGHT_STATE_RUN = 1;
	public static final int KNIGHT_STATE_JUMP = 2;
	public static final int KNIGHT_STATE_ATTACK = 3;
	public static final int KNIGHT_STATE_FALLING = 4;
	public static final int KNIGHT_STATE_HITLEFT = 5;
	public static final int KNIGHT_STATE_HITRIGHT = 6;
	public static final int KNIGHT_STATE_DEAD = 7;
	
	public static final float KNIGHT_RUN_VELOCITY = 100;
	public static final float KNIGHT_JUMP_VELOCITY = 200;
	public static final float KNIGHT_MAX_RUN_VELOCITY = 100;
	public static final float KNIGHT_MAX_JUMP_VELOCITY = 200;
	public static final float KNIGHT_MAX_FALL_VELOCITY = -300;
	
	int state;
	public int health;
	public static boolean left;
	public static boolean right;
	public static boolean faceLeft;
	public static boolean faceRight;
	public static boolean collided;
	public static boolean jump;
	public static boolean attack;
	public static boolean hit;
	public static boolean run;
	public static boolean stand;
	public static boolean loopEnded;
	float stateTime;
	static Random rand;
		
	public Knight(float x, float y) 
	{
		super(x, y, KNIGHT_WIDTH, KNIGHT_HEIGHT);
		health = 5;
		state = KNIGHT_STATE_FALLING;
		stateTime = 0;
		
		left = false;
		right = false;
		attack = false;
		hit = false;
		
		faceRight = true;
		collided = false;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY) 
	{
		if(loopEnded == true && state == KNIGHT_STATE_ATTACK)
		{
			state = KNIGHT_STATE_STAND;
			stateTime = 0;
		}
		
		if(state == KNIGHT_STATE_RUN)
		{
			if(right == true)
			{
				velocity.x = KNIGHT_RUN_VELOCITY;
			}
			
			else
			{
				velocity.x = -KNIGHT_RUN_VELOCITY;
			}
			
			attack = false;
		}
			
		if(state == KNIGHT_STATE_JUMP && collided == true)
		{
			velocity.y = KNIGHT_JUMP_VELOCITY;
			collided = false;
		}	
		
		if(state == KNIGHT_STATE_ATTACK)
		{
			attack = true;
			if(velocity.x > 0 || velocity.y < 0)
				velocity.x = 0;
		}
		
		if(state == KNIGHT_STATE_HITLEFT)
		{
				hit = true;
				collided = false;
				position.y -= 40;
				position.x += 40;
				
		}
		
		if(state == KNIGHT_STATE_HITRIGHT)
		{
				hit = true;
				collided = false;
				position.y -= 40;
				position.x -= 40;
		}
			
		if(right == false && left == false && jump == false && attack == false && hit == false)
			state = KNIGHT_STATE_STAND;
		
		if(state == KNIGHT_STATE_STAND)
			velocity.x = 0;
				
			/*
			if(velocity.y < KNIGHT_MAX_FALL_VELOCITY)
			{
				velocity.set(velocity.x, KNIGHT_MAX_FALL_VELOCITY);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			}
			
			if(velocity.x > KNIGHT_MAX_RUN_VELOCITY)
			{
				velocity.set(KNIGHT_MAX_RUN_VELOCITY, velocity.y);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			}
			*/
			//else
			//{
		if(collided == false)
			velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
		else
			velocity.add(World.gravity.x * deltaTime, 0);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			//}
		//}
		/*else
		{
			velocity.set(velocityX, velocityY);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			//state = KNIGHT_STATE_STAND;
		}
		 */
		//velocity.set(velocityX, velocityY);
		//position.add(velocity.x, velocity.y);
		if(position.x < 0)
            position.x = 0;
        if(position.x > Values.WORLD_WIDTH)
            position.x = Values.WORLD_WIDTH;
        
        if(velocity.x > KNIGHT_MAX_RUN_VELOCITY)
        	velocity.x = KNIGHT_MAX_RUN_VELOCITY;
        if(velocity.x < -KNIGHT_MAX_RUN_VELOCITY)
        	velocity.x = -KNIGHT_MAX_RUN_VELOCITY;
        
        if(velocity.x > 0)
		{
			faceRight = true;
			faceLeft = false;
		}
		if(velocity.x < 0)
		{
			faceRight = false;
			faceLeft = true;
		}
            
        bounds.lowerLeft.set(position).sub(KNIGHT_WIDTH/2, KNIGHT_HEIGHT/ 2);
    		
		stateTime += deltaTime;
		
		left = false;
		right = false;
		//collided = false;
		jump = false;
		attack = false;
		run = false;
		hit = false;
		stand = true;
		
		//Controls work great with this, but no run animation!
		//state = KNIGHT_STATE_STAND;
	}
	
	public void stopFalling()
	{
		if(velocity.y != 0)
		{
			velocity.y = 0;
			collided = true;
			state = KNIGHT_STATE_STAND;
		}
	}
}
