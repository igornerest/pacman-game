package scenerendering;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import engine.*;	// my package

// timer used to keep player's movement
// src : https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
import javafx.animation.AnimationTimer;

public class GameScene{
	private Scene gameScene; // A scene represents the physical contents of a JavaFX application
	
	private Map gameMap;

	private Player[] gamePlayers;

	public GameScene(int screenWidth, int screenHeight, EventHandler<KeyEvent> value, int selectedLevel) {
		Group gameRoot = new Group();
		// according to the selected level, newMap creates all the imageView objects
		// and stores them in a ObservableList
		gameMap = new Map(Preferences.LEVEL_SRC[selectedLevel]);
		gameRoot.getChildren().addAll(gameMap.getTilesList());

		setPlayers();
		for(int i = 0; i < 5; i++) 
			gameRoot.getChildren().add(gamePlayers[i].getPlayerImage());
	
		// The AnimationTimer's handle method is invoked once for each frame 
		// that is rendered, on the FX Application Thread. 
		// We should never block that thread (calling Thread.sleep(...)) here.
		// src: https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
		AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            // a timestamp in nanoseconds is passed to the handle(...) method 
            // We want to throttle updates so they don't happen more than once every time
            @Override
            public void handle(long now) {
            	if(now - lastUpdate >= Preferences.NSPF) {	// nanoseconds per frame
	            	movePlayers();
	            	lastUpdate = now;
	            }
            }
        };
    	timer.start();
			
		this.gameScene = new Scene(gameRoot, screenWidth, screenHeight);
		this.gameScene.setOnKeyPressed(value);
	}

	private void movePlayers() {
		for(int i = 0;  i < 5; i++)
			this.gamePlayers[i].move(this.gameMap);
	}

	private void setPlayers() {
		gamePlayers = new Player[5];
		gamePlayers[0] = new Pacman(1,1);
		gamePlayers[1] = new Ghost(1, 2, Preferences.YELLOW_GHOST_SRC);
		gamePlayers[2] = new Ghost(1, 3, Preferences.BLUE_GHOST_SRC);
		gamePlayers[3] = new Ghost(1, 4, Preferences.GREEN_GHOST_SRC);
		gamePlayers[4] = new Ghost(1, 5, Preferences.RED_GHOST_SRC);
	}

	public Player getPacman() {
		return this.gamePlayers[0];
	}

	public void setScene(Stage stage) {
		stage.setScene(this.gameScene);
		stage.show(); 
	}
}