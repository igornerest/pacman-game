// Code by: Igor Neres Trindade
// Some good references:
// https://www.tutorialspoint.com/javafx/javafx_application.htm
// https://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm

// Images and icons were taken from
// https://www.kisspng.com/free/pacman.html
// http://www.classicgaming.cc/classics/pac-man/icons

import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
// my packages
import engine.*;
import scenerendering.*;

// To create a JavaFX appliction, we need to inherit the Application class
public class Game extends Application{
	private static final int screenHeight = 500;
	private static final int screenWidth = 400;

	@Override // overwritting an Application class method
	public void start(Stage primaryStage) throws Exception {
		// a stage (that is a window) contains ALL THE OBJECTS of a JavaFX applicaton.
		// it is represented by Stage class
		// primaryStage, created by the plataform itself, is passed as argument in this method
		try {
			MenuScene teste = new MenuScene(screenWidth, screenHeight);
			teste.setScene(primaryStage);

		} catch(Exception e) {
			System.out.println(e);
		}
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

}