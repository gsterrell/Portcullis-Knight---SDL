package com.worstgamestudio.portcullisknight;

import com.worstgamestudio.framework.DynamicGameObject; 

import java.util.Random;

class Pillar extends DynamicGameObject 
{	 
	public static final float PILLAR_WIDTH = Values.PILLAR_WIDTH;
	public static final float PILLAR_HEIGHT = Values.PILLAR_HEIGHT;
	public static final int PILLAR_TYPE_SOLID = 0;
	public static final int PILLAR_TYPE_NONSOLID = 1;
	public static final int PILLAR_TYPE_DAMAGING = 2;
	
	int type;
	float stateTime;
	static Random rand;
		
	public Pillar(int type, float x, float y) 
	{
		super(x, y, PILLAR_WIDTH, PILLAR_HEIGHT);
		this.type = type;
		stateTime = 0;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY) 
	{
		stateTime += deltaTime;		
	}
}