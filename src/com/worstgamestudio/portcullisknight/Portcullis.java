package com.worstgamestudio.portcullisknight;

/*import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class DarkApprentice extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_dark, menu);
        return true;
    }
}

*/
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.worstgamestudio.framework.Screen;
import com.worstgamestudio.framework.impl.GLGame;

public class Portcullis extends GLGame
{
	boolean firstTimeCreate = true;

	@Override
	public Screen getStartScreen() 
	{
		return new GameScreen(this);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) 
		{
			Settings.load(getFileIO());
			Assets.load(this);
			firstTimeCreate = false;
		} 
		else 
		{
			//Assets.reload();
		}
	}

	@Override
	public void onPause() 
	{
		super.onPause();
		if (Settings.soundEnabled)
			Assets.music.pause();
	}
}