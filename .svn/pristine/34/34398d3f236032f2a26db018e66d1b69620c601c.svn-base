package com.worstgamestudio.portcullisknight;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.worstgamestudio.framework.Game;
import com.worstgamestudio.framework.Input.TouchEvent;
import com.worstgamestudio.framework.gl.Camera2D;
import com.worstgamestudio.framework.gl.FPSCounter;
import com.worstgamestudio.framework.gl.SpriteBatcher;
import com.worstgamestudio.framework.impl.GLScreen;
import com.worstgamestudio.framework.math.OverlapTester;
import com.worstgamestudio.framework.math.Rectangle;
import com.worstgamestudio.framework.math.Vector2;
import com.worstgamestudio.portcullisknight.World.WorldListener;

public class GameScreen extends GLScreen 
{
    static final int GAME_READY = 0;    
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;
  
    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;    
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;    
    Rectangle leftBounds;
    Rectangle rightBounds;
    Rectangle jumpBounds;
    Rectangle attackBounds;
    
    int lastScore;
    String scoreString;    
    FPSCounter fpsCounter;
    
    public GameScreen(Game game) 
    {
        super(game);
        state = GAME_READY;
        guiCam = new Camera2D(glGraphics, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        worldListener = new WorldListener() 
        {
        	@Override
            public void stand() 
            {            
                //Assets.playSound(Assets.jumpSound);
            }

            @Override
            public void run() 
            {
                //Assets.playSound(Assets.hitSound);
            }

			@Override
			public void deathFlame()
			{
				//Assets.playSound(Assets.deathSound);
			}
        };
        
        world = new World(worldListener);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        rightBounds = new Rectangle(60, 25, 60, 40);
        leftBounds = new Rectangle(0, 25, 60, 40);
        jumpBounds = new Rectangle(Values.SCREEN_WIDTH - 160, 30, 60, 40);
        attackBounds = new Rectangle(Values.SCREEN_WIDTH - 220, 30, 60, 40);
        lastScore = 0;
        scoreString = "score: 0";
        fpsCounter = new FPSCounter();
    }

	@Override
	public void update(float deltaTime) 
	{
	    if(deltaTime > 0.1f)
	        deltaTime = 0.1f;
	    
	    switch(state) 
	    {
	    case GAME_READY:
	        updateReady();
	        break;
	    case GAME_RUNNING:
	        updateRunning(deltaTime);
	        break;
	    case GAME_PAUSED:
	        updatePaused();
	        break;
	    case GAME_LEVEL_END:
	        updateLevelEnd();
	        break;
	    case GAME_OVER:
	        updateGameOver();
	        break;
	    }
	}
	
	private void updateReady() 
	{
	    if(game.getInput().getTouchEvents().size() > 0) 
	    {
	        state = GAME_RUNNING;
	    }
	}

	private void updateRunning(float deltaTime) 
	{
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) 
	    {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	    }
	    
	    //This is what I commented out and screwed everything up
	    world.update(deltaTime, knightLeftInput(), knightRightInput());
	    
	    if(world.score != lastScore) 
	    {
	        lastScore = world.score;
	        scoreString = "" + lastScore;
	    }
	    
	    if(world.state == World.WORLD_STATE_NEXT_LEVEL) 
	    {
	        state = GAME_LEVEL_END;        
	    }
	    
	    if(world.state == World.WORLD_STATE_GAME_OVER) 
	    {
	        state = GAME_OVER;
	    }
	}
	
	private float knightLeftInput() 
	{
		float velocityX = world.knight.velocity.x; 
		
		for (int i = 0; i < 2; i++) 
		{
			if (game.getInput().isTouchDown(i)) 
			{
				guiCam.touchToWorld(touchPoint.set(game.getInput()
						.getTouchX(i), game.getInput().getTouchY(i)));
			    if(OverlapTester.pointInRectangle(rightBounds, touchPoint)) 
		        {
			    	world.knight.state = Knight.KNIGHT_STATE_RUN;
			    	world.knight.right = true;
			    	world.knight.left = false;
		        }
			        
		        if(OverlapTester.pointInRectangle(leftBounds, touchPoint)) 
		        {
		        	world.knight.state = Knight.KNIGHT_STATE_RUN;
		        	world.knight.left = true;
		        	world.knight.right = false;
		        }
			}
		} 
			
		return velocityX;
	}
	
	private float knightRightInput() 
	{
		float velocityY = world.knight.velocity.y; 
		
		for (int i = 0; i < 2; i++) 
		{
			if (game.getInput().isTouchDown(i)) 
			{
				guiCam.touchToWorld(touchPoint.set(game.getInput()
						.getTouchX(i), game.getInput().getTouchY(i)));
			    if(OverlapTester.pointInRectangle(jumpBounds, touchPoint) && world.knight.velocity.y == 0) 
		        {
			    	//velocityY = Knight.KNIGHT_JUMP_VELOCITY;
			    	world.knight.state = Knight.KNIGHT_STATE_JUMP;
			    	world.knight.stateTime = 0;
		        }
			    
			    if(OverlapTester.pointInRectangle(attackBounds, touchPoint)) 
		        {
			    	//velocityY = Knight.KNIGHT_JUMP_VELOCITY;
			    	world.knight.state = Knight.KNIGHT_STATE_ATTACK;
			    	world.knight.stateTime = 0;
		        }
			}
		} 
		
		return velocityY;
	}

	private void updatePaused() 
	{
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) 
	    {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	    }
	}
	
	private void updateLevelEnd() 
	{
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) 
	    {                   
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        world = new World(worldListener);
	        renderer = new WorldRenderer(glGraphics, batcher, world);
	        world.score = lastScore;
	        state = GAME_READY;
	    }
	}
	
	private void updateGameOver() 
	{
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) 
	    {                   
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        game.setScreen(new MainMenuScreen(game));
	    }
	}

	@Override
	public void present(float deltaTime) 
	{
	    GL10 gl = glGraphics.getGL();
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    
	    renderer.render(world, deltaTime);
	    
	    guiCam.setViewportAndMatrices();
	    gl.glEnable(GL10.GL_BLEND);
	    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	    batcher.beginBatch(Assets.items);
	    switch(state) {
	    case GAME_READY:
	        presentReady();
	        break;
	    case GAME_RUNNING:
	        presentRunning();
	        break;
	    case GAME_PAUSED:
	        presentPaused();
	        break;
	    case GAME_LEVEL_END:
	        presentLevelEnd();
	        break;
	    case GAME_OVER:
	        presentGameOver();
	        break;
	    }
	    batcher.endBatch();
	    gl.glDisable(GL10.GL_BLEND);
	    fpsCounter.logFrame();
	}
	
	private void presentReady() 
	{
		//Different positions just so we can see the change
		batcher.drawSprite(Values.SCREEN_WIDTH - 110, Values.SCREEN_HEIGHT - 55, 55, 55, Assets.leftArrow);
		batcher.drawSprite(Values.SCREEN_WIDTH - 55,	Values.SCREEN_HEIGHT - 55, 55, 55, Assets.rightArrow);
	}

	private void presentRunning() 
	{
		/*for(int i = 0; i < ((Values.SCREEN_WIDTH % Values.GROUND_WIDTH)*10); i++)
		{
			//batcher.drawSprite(i*Values.GROUND_WIDTH, 50, Values.GROUND_WIDTH, Values.GROUND_HEIGHT, Assets.ground);
			batcher.drawSprite((i*Values.GROUND_WIDTH)-(renderer.cam.position.x), 50, Values.GROUND_WIDTH, Values.GROUND_HEIGHT, Assets.ground);
		}*/
		
		//The good, useful stuff.
		batcher.drawSprite(30, 50, 60, 40, Assets.leftArrow);
		batcher.drawSprite(90, 50, 60, 40, Assets.rightArrow);
		batcher.drawSprite(Values.SCREEN_WIDTH - 190, 50, 60, 40, Assets.attackButton);
		batcher.drawSprite(Values.SCREEN_WIDTH - 130, 50, 60, 40, Assets.jumpButton);
	}
	
	private void presentPaused() 
	{        
		//Not currently used.
		batcher.drawSprite(Values.SCREEN_WIDTH - 110, Values.SCREEN_HEIGHT - 55, 55, 55, Assets.leftArrow);
		batcher.drawSprite(Values.SCREEN_WIDTH - 55, Values.SCREEN_HEIGHT - 55, 55, 55, Assets.rightArrow);
	}
	
	private void presentLevelEnd() 
	{}
	
	private void presentGameOver() 
	{
		//Not currently used.
		batcher.drawSprite(Values.SCREEN_WIDTH - 110, Values.SCREEN_HEIGHT - 55, 55, 55, Assets.leftArrow);
		batcher.drawSprite(Values.SCREEN_WIDTH - 55, Values.SCREEN_HEIGHT - 55, 55, 55, Assets.rightArrow);
	}

    @Override
    public void pause() 
    {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() 
    {}

    @Override
    public void dispose() 
    {}
}