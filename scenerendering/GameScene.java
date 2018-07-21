package scenerendering;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import javafx.scene.image.Image;  

import javafx.scene.text.Text;
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;

import javafx.scene.shape.Rectangle;

import engine.*;

// timer used to keep player's movement
// src : https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
import javafx.animation.AnimationTimer;

public class GameScene{
	private Scene gameScene; // A scene represents the physical contents of a JavaFX application
	
	private Map gameMap;

	private Pacman usrPacman;

	public GameScene(int screenWidth, int screenHeight, EventHandler<KeyEvent> value, int selectedLevel) {
		// according to the selected level, newMap creates all the imageView objects
		// and stores them in a ObservableList
		gameMap = new Map(Preferences.LEVEL_SRC[selectedLevel]);
		
		Group gameRoot = new Group(gameMap.getTilesList());
		
		usrPacman = new Pacman(1, 1);
		gameRoot.getChildren().add(usrPacman.getPlayerImage());

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
	            	usrPacman.move(gameMap);
	            	lastUpdate = now;
	            }
            }
        };
    	timer.start();
			
		this.gameScene = new Scene(gameRoot, screenWidth, screenHeight);
		this.gameScene.setOnKeyPressed(value);
	}

	public Player getPacman() {
		return this.usrPacman;
	}

	public void setScene(Stage stage) {
		stage.setScene(this.gameScene);
		stage.setTitle("Pacman - playing...");
		stage.getIcons().add(new Image(Preferences.ICON_SRC));
		stage.setResizable(false);
		stage.show(); 
	}
}