package com.worstgamestudio.framework;

//import files for positioning
import com.worstgamestudio.framework.math.Rectangle;
import com.worstgamestudio.framework.math.Vector2;

//Base game object.  Characters, enemies, etc., created from this.
public class GameObject 
{
    public final Vector2 position;
    public final Rectangle bounds;
    
    //Constructor.  Takes the coordinates and dimensions
    public GameObject(float x, float y, float width, float height) 
    {
        this.position = new Vector2(x,y);
        //Set the bounds to be centered.  Very important to remember
        //when positioning on the screen.
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }
}