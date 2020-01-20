package com.worstgamestudio.portcullisknight;

import com.worstgamestudio.framework.gl.TextureRegion;
import com.worstgamestudio.framework.Sound;
import com.worstgamestudio.framework.Music;
import com.worstgamestudio.framework.gl.Animation;
import com.worstgamestudio.framework.gl.Texture;
import com.worstgamestudio.framework.impl.GLGame;
//import com.worstgamestudio.portcullisknight.World;

public class Assets
{
	public static Texture items;
	public static TextureRegion leftArrow;
	public static TextureRegion rightArrow;
	public static TextureRegion attackButton;
	public static TextureRegion jumpButton;
	public static TextureRegion knightStand;
	public static TextureRegion ground;
	public static TextureRegion pillar;
	public static TextureRegion bridge;
	public static TextureRegion health;
	public static Texture knight;
	public static Animation knightRun;
	public static Animation knightJumpUp;
	public static Animation knightJumpDown;
	public static Animation knightAttack;
	public static Texture enemy;
	public static Animation bat;
	public static Animation wolf;
	public static Animation deathFlame;
	public static Texture background;
	public static Texture backgroundParallax;
	public static Texture backgroundClouds;
	public static Music music;
	public static Sound sound;
	public static TextureRegion backgroundRegion;
	public static TextureRegion backgroundRegionParallax;
	public static TextureRegion backgroundRegionClouds;
	
	public static Texture walls;
	public static TextureRegion wall;
	public static TextureRegion doorway;
	
	public static Texture objects;
	public static TextureRegion column;
	public static TextureRegion gate;
	public static TextureRegion grass;
	public static TextureRegion pavement;
	
	public static void load(GLGame game)
	{	
		music = game.getAudio().newMusic("battletheme.mp3");
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		
		background = new Texture(game, "background2.png");
		{
			backgroundRegion = new TextureRegion(background, 0, 0, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
		}

		backgroundParallax = new Texture(game, "background3.png");
		{
			backgroundRegionParallax = new TextureRegion(backgroundParallax, 0, 0, 512, Values.SCREEN_HEIGHT);
		}
		
		backgroundClouds = new Texture(game, "background3.png");
		{
			backgroundRegionClouds = new TextureRegion(backgroundClouds, 512, 0, 512, Values.SCREEN_HEIGHT);
		}
		
		items = new Texture(game, "items.png");
		leftArrow = new TextureRegion(items, 0,  200, Values.ARROW_WIDTH, Values.ARROW_HEIGHT);
		rightArrow = new TextureRegion(items, Values.ARROW_WIDTH,  200, Values.ARROW_WIDTH, Values.ARROW_HEIGHT);
		attackButton = new TextureRegion(items, Values.ARROW_WIDTH * 2,  200, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
		jumpButton = new TextureRegion(items, (Values.ARROW_WIDTH * 2) + Values.BUTTON_WIDTH,  200, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
		ground = new TextureRegion(items, 0, 250, Values.GROUND_WIDTH, Values.GROUND_HEIGHT);
		pillar = new TextureRegion(items, 44, 250, Values.PILLAR_WIDTH, Values.PILLAR_HEIGHT);
		bridge = new TextureRegion(items, 120, 250, Values.BRIDGE_WIDTH, Values.BRIDGE_HEIGHT);
		
		//walls = new Texture(game, "walls.png");
		//wall = new TextureRegion(walls,0,0,512,448);
		//doorway = new TextureRegion(walls,512,0,512,448);
		
		objects = new Texture(game, "objects3.png");
		//column = new TextureRegion(objects,0,0,448,58);
		//gate = new TextureRegion(objects,198,0,136,376);
		grass = new TextureRegion(objects,62,0,Values.GRASS_WIDTH,Values.GRASS_HEIGHT);
		//pavement = new TextureRegion(objects,62,0,64,128);
		health = new TextureRegion(objects,350, 0, 35, 35);
		
		knight = new Texture(game, "knight2.png");
		
		knightStand = new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 4, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT);
			
		knightJumpUp = new Animation(0.1f,
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 0, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 3, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 4, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT));
		
		knightRun = new Animation(0.1f, 
				new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 0, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 3, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(knight, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 4, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT));
		
		/*
		
		knightStand = new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 0, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT);
			
		knightRun = new Animation(0.1f, 
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 2, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 3, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 4, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 5, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 6, Values.KNIGHT_HEIGHT * 1, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT));
		
		knightJumpUp = new Animation(0.4f, 
				new TextureRegion(items, Values.KNIGHT_WIDTH * 0, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 1, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),/*)
		
		knightJumpDown = new Animation(0.1f
				new TextureRegion(items, Values.KNIGHT_WIDTH * 2, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT),
				new TextureRegion(items, Values.KNIGHT_WIDTH * 3, Values.KNIGHT_HEIGHT * 2, 
				Values.KNIGHT_WIDTH, Values.KNIGHT_HEIGHT));
	*/
		TextureRegion[] keyFrames = new TextureRegion[5];
		int frame = 0;
		for (int y = 0; y < Values.KNIGHT_HEIGHT*5; y += Values.KNIGHT_HEIGHT)
		{
			keyFrames[frame++] = new TextureRegion(items, Values.KNIGHT_WIDTH * 3, y,
					Values.KNIGHT_WIDTH*2, Values.KNIGHT_HEIGHT);
		}
		knightAttack = new Animation(0.05f, keyFrames);

		enemy = new Texture(game, "enemies.png");
		
		bat = new Animation(0.1f,
				new TextureRegion(enemy, 4, 88, Enemy.ENEMY_TYPE_WIDTH[Enemy.ENEMY_TYPE_BAT], Enemy.ENEMY_TYPE_HEIGHT[Enemy.ENEMY_TYPE_BAT]),
				new TextureRegion(enemy, 68, 88, Enemy.ENEMY_TYPE_WIDTH[Enemy.ENEMY_TYPE_BAT], Enemy.ENEMY_TYPE_HEIGHT[Enemy.ENEMY_TYPE_BAT]),
				new TextureRegion(enemy, 138, 88, Enemy.ENEMY_TYPE_WIDTH[Enemy.ENEMY_TYPE_BAT], Enemy.ENEMY_TYPE_HEIGHT[Enemy.ENEMY_TYPE_BAT]),
				new TextureRegion(enemy, 199, 88, Enemy.ENEMY_TYPE_WIDTH[Enemy.ENEMY_TYPE_BAT], Enemy.ENEMY_TYPE_HEIGHT[Enemy.ENEMY_TYPE_BAT]));

		deathFlame = new Animation(0.05f,
				new TextureRegion(enemy, 4, 120, 32, 32),
				new TextureRegion(enemy, 68, 120, 32, 32),
				new TextureRegion(enemy, 138, 120, 32, 32),
				new TextureRegion(enemy, 199, 120, 32, 32),
				new TextureRegion(enemy, 259, 120, 32, 32),
				new TextureRegion(enemy, 319, 120, 32, 32));

		sound = game.getAudio().newSound("swordsound.ogg");
	}
	
	public static void playSound(Sound sound) 
	{
		//if (Settings.soundEnabled)
			sound.play(1);
	}
}