// Code by: Igor Neres Trindade
// Good references:
// https://www.tutorialspoint.com/javafx/javafx_application.htm
// https://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm

// Images and icons were taken from
// https://www.kisspng.com/free/pacman.html
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image; 

import scenerendering.ScreenSetting;

// To create a JavaFX appliction, we need to inherit the Application class
public class Game extends Application {
	
	@Override  // overwritting an Application class method
	public void start(Stage primaryStage) {
		configureStage(primaryStage);
		ScreenSetting.getInstance(primaryStage); // using Singleton pattern to set a Scene to this Stage
	}

	private void configureStage(Stage stage) { // preparing the main stage (window)
		// a Stage (that is a window) contains ALL THE OBJECTS of a JavaFX applicaton.
		stage.setTitle("Pacman");
		stage.getIcons().add(new Image("./images/pacman-icon.png"));
		stage.setResizable(false);
		stage.show();
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