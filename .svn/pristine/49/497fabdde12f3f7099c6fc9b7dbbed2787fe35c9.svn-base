package com.worstgamestudio.portcullisknight;

import com.worstgamestudio.framework.DynamicGameObject; 

import java.util.Random;

class Ground extends DynamicGameObject 
{	 
	public static final float GRASS_WIDTH = Values.GRASS_WIDTH;
	public static final float GRASS_HEIGHT = Values.GRASS_HEIGHT;
	public static final int GROUND_TYPE_SOLID = 0;
	public static final int GROUND_TYPE_NONSOLID = 1;
	public static final int GROUND_TYPE_DAMAGING = 2;
	
	int type;
	float stateTime;
	static Random rand;
		
	public Ground(int type, float x, float y) 
	{
		super(x, y, GRASS_WIDTH, GRASS_HEIGHT);
		this.type = type;
		stateTime = 0;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY) 
	{
		stateTime += deltaTime;		
	}
}