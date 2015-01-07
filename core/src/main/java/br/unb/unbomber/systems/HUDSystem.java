package br.unb.unbomber.systems;

import java.util.List;

import br.unb.bomberman.ui.screens.Assets;
import br.unb.unbomber.core.BaseSystem;
import br.unb.unbomber.core.Entity;
import br.unb.unbomber.core.EntityManager;
import br.unb.unbomber.core.Event;
import br.unb.unbomber.component.Score;
import br.unb.unbomber.component.Timer;
import br.unb.unbomber.event.DestroyedEvent;
import br.unb.unbomber.event.GameOverEvent;
import br.unb.unbomber.event.TimeOverEvent;
import br.unb.unbomber.systems.TimeSystem;
import br.unb.unbomber.event.GameOverEvent;
import br.unb.unbomber.event.TimeOverEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Control the system of the status bar.
 * 
 * Create the status bar and update the values of the time e and score.
 * 
 * @version 0.1 13 Dez 2014
 * @author Grupo 5 - Dayanne <dayannefernandesc@gmail.com>
 * 
 */
public class HUDSystem extends BaseSystem {
	
	SpriteBatch batch;
	
	private Entity HUD;
	
	TimeSystem sysTime;
	TimeOverEvent timeOver;
	private Timer timerHUD;
	private String timeString;
	
	ScoreSystem sysScore;
	private Score scoreHUD;
	private int lastScore;
	private String scoreString;
	
	

	
	public HUDSystem (EntityManager entityManager,
			SpriteBatch batch) {
		super(entityManager);
		this.batch = batch;
	}
	
	@Override
	public void start() {
		// Inits the time system
		this.sysTime = new TimeSystem(this.getEntityManager());
		this.sysScore = new ScoreSystem(this.getEntityManager());
		
		this.timeString = "";
		this.scoreString = "";
		this.lastScore = 0;
		
		// Creation of a HUD entity
		HUD =  this.getEntityManager().createEntity();
		// Creation of a component time for HUD entity
		timerHUD = new Timer(2200, timeOver);
		HUD.addComponent(timerHUD);
		// Creation of a component score for HUD entity
		scoreHUD = new Score(1);
		HUD.addComponent(scoreHUD);
		
		this.getEntityManager().update(HUD);
		
	}
	
	@Override
	public void update() {
		List<Event> timeOver = this.getEntityManager().getEvents(TimeOverEvent.class);
		// If the event of time over exist then stop the game (or match)
		if(timeOver != null)
		{
			stop();
		}
		
		// Elapsed time is divided with the average of the frame rate to get the time in seconds
		float timeElpsed = this.timerHUD.getElapsedTime() / 30;
		float min = (long)(timeElpsed / 60) ;
		float seg = (long)(timeElpsed % 60);
		
		// Modify the string to show on HUD with the new time
		String minString = String.format("%02.0f", min);
		String segString = String.format("%02.0f", seg);
		
		this.timeString = minString + ":" + segString;
		this.scoreString = String.format("%d", this.scoreHUD.getScore());
		
		// Scale of the font on HUD
		Assets.font.setScale(0.6f, 1);
		
		// Draw on HUD the time regressively
		Assets.font.draw(batch, this.timeString , 85, 480 - 28);
		
		// Draw on HUD the score of the main player
		Assets.font.draw(batch, this.scoreString, 252, 480 - 32);
	}
	
	@Override
	public void stop() {
		// If the time over create a game over event
		createGameOverEvent();
	}
	
	private void createGameOverEvent() {
		// Create a game over event
		GameOverEvent gameOverEvent = new GameOverEvent(this.HUD.getEntityId());
		getEntityManager().addEvent(gameOverEvent);
	}
}
