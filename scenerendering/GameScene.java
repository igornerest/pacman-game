package scenerendering;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import java.io.File;		// for reading .txt document
import java.util.Scanner;	// for getting user input/read .txt document

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

		setPlayers(Preferences.LEVEL_SRC[selectedLevel]);
		for (int i = 0; i < 5; i++) 
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
            	if (now - lastUpdate >= Preferences.NSPF) {	// nanoseconds per frame
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
		if (this.getPacman().isAlive()) {
			for (int i = 0;  i < 5; i++) {
				this.gamePlayers[i].move(this.gameMap);

				if (gamePlayers[i] instanceof Ghost)
					if(this.getPacman().touchedGhosts((Ghost) gamePlayers[i]))
						this.getPacman().setDeath();

			}
		}
	}

	private void setPlayers(String mapString) {
		int index = 1;

		gamePlayers = new Player[5];

		try (Scanner sc = new Scanner(new File(mapString))){
			int mapHeigth = Integer.parseInt(sc.nextLine());
			int mapWidth = Integer.parseInt(sc.nextLine());

			for (int i = 0; i < mapHeigth; i++) {
				String tileString = sc.nextLine();

				for (int j = 0; j < mapWidth; j++) {
					switch(tileString.charAt(j)) {
						case 'P':
							gamePlayers[0] = new Pacman(j, i);
							break;

						case 'R':
							gamePlayers[index++] = new Ghost(j, i, Preferences.RED_GHOST_SRC);
							break;

						case 'G':
							gamePlayers[index++] = new Ghost(j, i, Preferences.GREEN_GHOST_SRC);
							break;

						case 'B':
							gamePlayers[index++] = new Ghost(j, i, Preferences.BLUE_GHOST_SRC);
							break;

						case 'Y':
							gamePlayers[index++] = new Ghost(j, i, Preferences.YELLOW_GHOST_SRC);
							break; 
					}
				}
			}
		} catch (Exception e) {
		    System.out.println(e);
		}
	}

	public Pacman getPacman() {
		return (Pacman) this.gamePlayers[0];
	}

	public void setScene(Stage stage) {
		stage.setScene(this.gameScene);
		stage.show(); 
	}
}