// Code by: Igor Neres Trindade
// Some good references:
// https://www.tutorialspoint.com/javafx/javafx_application.htm
// https://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm

// Images and icons were taken from
// https://www.kisspng.com/free/pacman.html
// http://www.classicgaming.cc/classics/pac-man/icons
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import javafx.scene.image.Image; 

import engine.*;
import scenerendering.*;

// To create a JavaFX appliction, we need to inherit the Application class
public class Game extends Application implements EventHandler<KeyEvent> {
	private static final int SCREEN_HEIGHT = 500;
	private static final int SCREEN_WIDTH = 400;

	private boolean isRunning = false;

	private MenuScene menuScene;
	private GameScene gameScene;

	private Stage window;

	@Override  // overwritting an Application class method
	public void start(Stage primaryStage) throws Exception {
		// a Stage (that is a window) contains ALL THE OBJECTS of a JavaFX applicaton.
		// preparing the main stage (window)
		window = primaryStage;
		window.setTitle("Pacman");
		window.getIcons().add(new Image(Preferences.ICON_SRC));
		window.setResizable(false);
			
		menuScene = new MenuScene(SCREEN_WIDTH, SCREEN_HEIGHT, this);
		menuScene.setScene(window);
	}

	public static void main(String[] args) {
		launch(args);	
		// static method inherited from the class Application
		// internally, it calls start(), that is overwritten in this main class
		// Whenever a JavaFx application is launched:
		// 1. an instance of the application class is created
		// 2. init() method is called
		// 3. the start() method is called
		// 4. the launcher waits for the application to finish and calls the stop() method
	}

	@Override  // implementing method from interface 
	public void handle(KeyEvent event) {
		if(isRunning) {
			gameCommand(event);
		} else {
			menuCommand(event);
		}
	}

	private void menuCommand(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				menuScene.selectDown();
				break; 

			case UP :
				menuScene.selectUp();
				break; 

			case ENTER :
				isRunning = true;
				gameScene = new GameScene(SCREEN_WIDTH, SCREEN_HEIGHT, this, menuScene.getSelectedLevel());
				gameScene.setScene(window);
				break; 
		}
	}

	private void gameCommand(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				gameScene.getPacman().setDown();
				break; 

			case UP :
				gameScene.getPacman().setUp();
				break;

			case LEFT :
				gameScene.getPacman().setLeft();
				break; 

			case RIGHT :
				gameScene.getPacman().setRight();
				break;
		}
	}
}