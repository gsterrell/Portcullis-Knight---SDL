package com.worstgamestudio.framework.impl;

import com.worstgamestudio.framework.Game;
import com.worstgamestudio.framework.Screen;

public abstract class GLScreen extends Screen
{
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;
	
	public GLScreen(Game game)
	{
		super(game);
		glGame = (GLGame)game;
		glGraphics = ((GLGame)game).getGLGraphics();
	}
}