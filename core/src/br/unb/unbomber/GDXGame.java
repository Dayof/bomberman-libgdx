package br.unb.unbomber;

import br.unb.bomberman.ui.screens.Assets;
import br.unb.bomberman.ui.screens.GameScreen;
import br.unb.bomberman.ui.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GDXGame extends Game {
    
    public final String FIRST_STAGE_LEVEL_ID = "stage";

	
	public SpriteBatch batch;
	
    /**
     *  Load the assets and 
     */
	@Override public void create () {
        batch = new SpriteBatch();
		
        Settings.load();
		Assets.load();                
        
		//set main menu
		this.setScreen(new MainMenuScreen(this));
	}
	
	/**
	 * 
	 */
	public void play(){
		this.setScreen(new GameScreen(this, FIRST_STAGE_LEVEL_ID));
	}

    public void render() {
        super.render(); //important!
    }
    
    public void dispose() {
        batch.dispose();
    }
}
