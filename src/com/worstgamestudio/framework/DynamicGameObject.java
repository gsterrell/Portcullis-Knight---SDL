package com.worstgamestudio.framework;

import com.worstgamestudio.framework.math.Vector2;

//Extended GameObject.  Adds velocity and acceleration.
public class DynamicGameObject extends GameObject 
{
    public final Vector2 velocity;
    public final Vector2 accel;
    
    public DynamicGameObject(float x, float y, float width, float height) 
    {
    	//Call GameObject constructor to set DynamicGameObject
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
