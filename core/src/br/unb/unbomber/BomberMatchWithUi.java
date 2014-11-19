package br.unb.unbomber;

import br.unb.unbomber.core.EntityManager;
import br.unb.unbomber.systems.AudioSystem;
import br.unb.unbomber.systems.LoadStageSystem;
import br.unb.unbomber.systems.PlayerControlSystem;
import br.unb.unbomber.systems.RenderSystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Bomber match with Libgdx User Interface
 * 
 * @author grodrigues
 *
 */
public class BomberMatchWithUi extends TargetFrameRateMatch{
	final static int DEFAUT_FRAME_RATE = 30;
	
	public enum State {
		WORLD_STATE_NEXT_LEVEL,
		WORLD_STATE_GAME_OVER
	}
	
	
	public int score;

	public State state; 
	
	public BomberMatchWithUi( SpriteBatch batch, String stageId) {
		super(DEFAUT_FRAME_RATE);
		EntityManager em = this.getEntityManager();
		
		/* create the UI systems */
		super.addSystem(new LoadStageSystem(em, stageId));
		super.addSystem(new PlayerControlSystem(em));
		super.addSystem(new AudioSystem(em));
		super.addSystem(new RenderSystem(em, batch));
		
	}

	public void update(){
		super.update();
	}

	public void removeAllEntities() {
		//TODO
	}

}
