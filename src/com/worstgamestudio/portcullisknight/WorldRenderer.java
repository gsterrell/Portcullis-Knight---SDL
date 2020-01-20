package com.worstgamestudio.portcullisknight;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.worstgamestudio.framework.gl.Animation;
import com.worstgamestudio.framework.gl.Camera2D;
import com.worstgamestudio.framework.gl.SpriteBatcher;
import com.worstgamestudio.framework.gl.TextureRegion;
import com.worstgamestudio.framework.impl.GLGraphics;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = Values.SCREEN_WIDTH;
    static final float FRUSTUM_HEIGHT = Values.SCREEN_HEIGHT;    
    GLGraphics glGraphics;
    World world;
    public Camera2D cam;
    SpriteBatcher batcher;
    int farBackground = 0;
    float nearBackground = 0;
    
    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) 
    {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.batcher = batcher;        
    }
    
    public void render(World world, float deltaTime)
    {
    	//if(world.knight.position.x > cam.position.x )
    	cam.position.x = world.knight.position.x;
        cam.setViewportAndMatrices();
        renderBackground();
        renderItems(world, deltaTime);
        renderObjects();
    }
    
    public void renderBackground() 
    {
    	GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.backgroundParallax);
        batcher.drawSprite(world.knight.position.x, (Values.SCREEN_HEIGHT / 2)-24, //cam.position.x, cam.position.y,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                    Assets.backgroundRegionParallax);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1.0f * 0.75f, 1.0f * 0.75f, 1.0f * 0.75f, 0.75f);
        gl.glEnable(GL10.GL_BLEND);
        
        batcher.beginBatch(Assets.backgroundClouds);
        for(int i = 0; i < 200; i++)
        {
        	//batcher.drawSprite(i*Values.WORLD_WIDTH, Values.WORLD_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT, Assets.backgroundRegion);
        	batcher.drawSprite(((i*512)+(cam.position.x*.8) - nearBackground), (Values.SCREEN_HEIGHT / 2)-24, //cam.position.x, cam.position.y,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                    Assets.backgroundRegionClouds);
        }
        nearBackground += 0.3f;
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1.0f * 1.0f, 1.0f * 1.0f, 1.0f * 1.0f, 1.0f);
        gl.glEnable(GL10.GL_BLEND);
        
        batcher.beginBatch(Assets.background);
        for(int i = 0; i < 10; i++)
        {
        	//batcher.drawSprite(i*Values.WORLD_WIDTH, Values.WORLD_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT, Assets.backgroundRegion);
        	batcher.drawSprite(((i*512)+(cam.position.x*.8)), (Values.SCREEN_HEIGHT / 2)-24, //cam.position.x, cam.position.y,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                    Assets.backgroundRegion);
        }
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
      }
    
    public void renderItems(World world, float deltaTime)
    {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.knight);
        renderKnight(world, deltaTime);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.enemy);
        renderEnemies(world.enemies, deltaTime);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }
    
    public void renderObjects()
    {
    	GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.objects);
        renderElements();
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }
    
    private void renderKnight(World world, float deltaTime)
    {
        TextureRegion keyFrame;
        switch(world.knight.state) 
        {
        case Knight.KNIGHT_STATE_RUN:
        	keyFrame = Assets.knightRun.getKeyFrame(world.knight.stateTime, Animation.ANIMATION_LOOPING);
        	break;
        case Knight.KNIGHT_STATE_JUMP:
        	keyFrame = Assets.knightJumpUp.getKeyFrame(world.knight.stateTime, Animation.ANIMATION_LOOPING);
        	break;
        case Knight.KNIGHT_STATE_ATTACK:
        	keyFrame = Assets.knightAttack.getKeyFrame(world.knight.stateTime, Animation.ANIMATION_NONLOOPING);
        	break;
        case Knight.KNIGHT_STATE_STAND:
        	keyFrame = Assets.knightStand;
        	break;
        default:
        	keyFrame = Assets.knightStand;
        	//keyFrame = Assets.knightJumpUp.getKeyFrame(world.knight.stateTime, Animation.ANIMATION_NONLOOPING);
        	break;
        }
        
        float side = world.knight.faceRight == false? -1: 1;        
        if(world.knight.state == Knight.KNIGHT_STATE_ATTACK)
        	batcher.drawSprite(world.knight.position.x - (Values.KNIGHT_WIDTH*(-side)/2), world.knight.position.y, 
        			side * (Values.KNIGHT_WIDTH*2), Values.KNIGHT_HEIGHT, keyFrame);
        else
        	batcher.drawSprite(world.knight.position.x, world.knight.position.y, 
        			side * Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT, keyFrame);
    }
    
    private void renderElements() 
    {
    	/*batcher.drawSprite(i*Values.WORLD_WIDTH, Values.WORLD_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT, Assets.backgroundRegion);
        	batcher.drawSprite(((i*Values.SCREEN_WIDTH)+(cam.position.x*.8)), Values.SCREEN_HEIGHT / 2, //cam.position.x, cam.position.y,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                    Assets.backgroundRegion);
                    */
    	int groundLen = world.theGround.size();
    	
    	for(int g = 0; g < groundLen; g++)
		{
    		Ground thisGround = world.theGround.get(g);
			//batcher.drawSprite(i*Values.GROUND_WIDTH, 50, Values.GROUND_WIDTH, Values.GROUND_HEIGHT, Assets.ground);
			//batcher.drawSprite((i*Values.BRIDGE_WIDTH)-(world.knight.position.x), (100 - (Values.BRIDGE_HEIGHT/2)), Values.BRIDGE_WIDTH, Values.BRIDGE_HEIGHT, Assets.bridge);
    		batcher.drawSprite(thisGround.position.x, thisGround.position.y, Values.GRASS_WIDTH, Values.GRASS_HEIGHT, Assets.grass);
    		//batcher.drawSprite(thisGround.position.x, thisGround.position.y, Values.GRASS_WIDTH, Values.GRASS_HEIGHT, Assets.grass);
    		//batcher.drawSprite(100, 40, Values.GRASS_WIDTH, Values.GRASS_HEIGHT, Assets.grass);
		}
    	
    	for(int h = 0; h < world.knight.health; h++)
    	{
    		batcher.drawSprite(cam.position.x - (Values.SCREEN_WIDTH/2) + (15+(h*35)), Values.WORLD_HEIGHT - 40, 35, 35, Assets.health);
    	}
    	/*
    	int pillarLen = world.pillars.size();
    	//for(int p = 0; p < ((Values.SCREEN_WIDTH % Values.PILLAR_WIDTH)*30); i++)
    	for(int p = 0; p < pillarLen; p++)
		{
    		Pillar pillar = world.pillars.get(p);
			//batcher.drawSprite(i*Values.GROUND_WIDTH, 50, Values.GROUND_WIDTH, Values.GROUND_HEIGHT, Assets.ground);
			//batcher.drawSprite(((i*3)*Values.PILLAR_WIDTH)-(world.knight.position.x), (100 - (Values.PILLAR_HEIGHT/2)), Values.PILLAR_WIDTH, Values.PILLAR_HEIGHT, Assets.pillar);
    		batcher.drawSprite(pillar.position.x, pillar.position.y, Values.PILLAR_WIDTH, Values.PILLAR_HEIGHT, Assets.pillar);
		}
		*/
    }
    
    private void renderEnemies(List<Enemy> enemies, float deltaTime)
    {
    	TextureRegion keyFrame;

        int len = enemies.size();
        for(int e = 0; e < len; e++)
        {
            Enemy enemy = enemies.get(e);
            if(enemy.state == Enemy.ENEMY_STATE_DEAD)
            {
                renderDeathFlame(enemy);
            }
            else
            {
                keyFrame = Assets.bat.getKeyFrame(world.enemies.get(e).stateTime, Animation.ANIMATION_LOOPING);
                float side = world.enemies.get(e).velocity.x < 0? 1: -1;
                batcher.drawSprite(world.enemies.get(e).position.x, world.enemies.get(e).position.y,
                        side * Enemy.ENEMY_TYPE_WIDTH[world.enemies.get(e).type],  Enemy.ENEMY_TYPE_HEIGHT[world.enemies.get(e).type], keyFrame);
            }
        }
    }

    private void renderDeathFlame(Enemy enemy)
    {
        TextureRegion keyFrame;

        keyFrame = Assets.deathFlame.getKeyFrame(enemy.stateTime, Animation.ANIMATION_NONLOOPING);
        batcher.drawSprite(enemy.position.x, enemy.position.y, Enemy.ENEMY_TYPE_WIDTH[enemy.type],  Enemy.ENEMY_TYPE_HEIGHT[enemy.type], keyFrame);
    }
}