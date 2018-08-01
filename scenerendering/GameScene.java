package scenerendering;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import java.io.File;  // for reading .txt document
import java.util.Scanner;  // for getting user input/read .txt document

import engine.*;  // my package

// timer used to keep player's movement
// src : https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
import javafx.animation.AnimationTimer;

public class GameScene implements IScene, EventHandler<KeyEvent> {
	private Scene gameScene; // A scene represents the physical contents of a JavaFX application
	
	private Map gameMap;

	private Player[] gamePlayers;

	private boolean gameOver;

	public GameScene(int screenWidth, int screenHeight, String levelSource) {
		Group gameRoot = new Group();
		// according to the selected level, newMap creates all the imageView objects
		// and stores them in a ObservableList
		try {
			gameMap = new Map(levelSource);
			gameRoot.getChildren().addAll(gameMap.getTilesList());

			setPlayers(levelSource);
			for (int i = 0; i < 5; i++) 
				gameRoot.getChildren().add(gamePlayers[i].getPlayerImageView());
			
		} catch(ScreenException e) {
			e.handleException();
		}
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
            	if (now - lastUpdate >= 90000000) {	// nanoseconds per frame
            		try {
	            		movePlayers();
	            		lastUpdate = now;
	            	} catch(ScreenException e) {
						e.handleException();
					}
	            }
            }
        };
		timer.start();

		this.gameScene = new Scene(gameRoot, screenWidth, screenHeight);
		this.gameScene.setOnKeyPressed(this);
	}

	@Override  // implementing method from EventHandler interface 
	public void handle(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				this.getPacman().setDown();
				break; 

			case UP :
				this.getPacman().setUp();
				break;

			case LEFT :
				this.getPacman().setLeft();
				break; 

			case RIGHT :
				this.getPacman().setRight();
				break;

			case ESCAPE:
				ScreenSetting.getInstance().closeApplication();
				break;

			case ENTER:
				if (this.gameOver)
					ScreenSetting.getInstance().setMenu();
				break;
		}
	}

	@Override  // implementing method from Iscene interface 
	public Scene getScene() {
		return this.gameScene;
	}

	private void movePlayers() throws ScreenException {
		if (this.getPacman().isAlive() && this.gameMap.hasFood()) {
			this.getPacman().move(this.gameMap);

			Path tmpTile = (Path) this.gameMap.getTile(this.getPacman().getXPos(), this.getPacman().getYPos());
			if (tmpTile.hasFood()) {
				tmpTile.setCleared();
				this.gameMap.eatFood();
			}

			if(this.getPacman().touchedGhosts(gamePlayers)) 
				this.getPacman().setDeath();
			else {
				for (int i = 1;  i < 5; i++) 
					this.gamePlayers[i].move(this.gameMap);
				
				if(this.getPacman().touchedGhosts(gamePlayers))
					this.getPacman().setDeath();
			}
		}
		else {
			this.gameOver = true;
		}
	}

	private void setPlayers(String mapString) throws ScreenException {
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
							gamePlayers[index++] = new RedGhost(j, i);
							break;

						case 'G':
							gamePlayers[index++] = new GreenGhost(j, i);
							break;

						case 'B':
							gamePlayers[index++] = new BlueGhost(j, i);
							break;

						case 'Y':
							gamePlayers[index++] = new YellowGhost(j, i);
							break; 
					}
				}
			}
		} catch (Exception e) {
		    throw new ScreenException(e.toString());
		}
	}

	private Pacman getPacman() {
		return (Pacman) this.gamePlayers[0]; // Pacman is always the first object of the vector (pattern)
	}
}