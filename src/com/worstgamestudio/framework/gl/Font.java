package com.worstgamestudio.framework.gl;

import com.worstgamestudio.framework.gl.SpriteBatcher;
import com.worstgamestudio.framework.gl.Texture;
import com.worstgamestudio.framework.gl.TextureRegion;

//Font class. Takes bitmap glyphs and uses ASCII values to print
public class Font 
{
    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];   
    
    public Font(Texture texture, 
                int offsetX, int offsetY,
                int glyphsPerRow, int glyphWidth, int glyphHeight) 
    {        
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        //For each bitmap letter (arranged according to their ASCII value)
        //make it into a texture.  
        for(int i = 0; i < 96; i++) 
        {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == offsetX + glyphsPerRow * glyphWidth) 
            {
                x = offsetX;
                y += glyphHeight;
            }
        }        
    }
    
    //Draw the letters to the screen
    public void drawText(SpriteBatcher batcher, String text, float x, float y) 
    {
        int len = text.length();
        for(int i = 0; i < len; i++) 
        {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1) 
                continue;
            
            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
        }
    }
}
