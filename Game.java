//	Code by: Igor Neres Trindade
//	Some good references:
// https://www.tutorialspoint.com/javafx/javafx_application.htm
// https://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm
// https://www.youtube.com/watch?v=saLcT5UE-JM
// https://www.youtube.com/watch?v=7LxWQIDOzyE

// Images from
// https://www.freeiconspng.com/images/pacman-png
// -----> https://www.kisspng.com/free/pacman.html
// http://www.classicgaming.cc/classics/pac-man/icons

import javafx.application.Application;

import javafx.collections.ObservableList;

import javafx.event.EventHandler;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

//// ???
import javafx.scene.image.Image;

import javafx.scene.text.Font; 
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Rectangle;

import engine.*;

// To create a JavaFX applictin, we need to inherit the Application class
public class Game extends Application implements EventHandler<KeyEvent> {

	private static final int screenHeight = 500;
	private static final int screenWidth = 400;
	
	private static int selectedLevel = 0;
	private static int inOpPosition = 150;	// inicial Option position
	
	private static boolean isRunning = false; 

	// moves towards user options
	// global Shape, so we can modify its position in EventHandler
	private Rectangle lvlSelector = new Rectangle(45, 15);

	// global stage, so we can modify its stage in different methods (and in the EventHandler)
	private Stage mainWindow;

	Map newMap = new Map();

	Pacman usrPacman;

	private Scene mainSceneRender() {
		// a Group node is a collective node that contains A LIST of CHILDREN NODES
		// this list is accessible through getChildren() method
		// whenever the groups node is rendered, all its children are rendered in order
		// we will add Leaf Nodes to this root, such as ImageView, Text, Rectangle...
		Group mainRoot = new Group();
		
		Text mainText = new Text(Preferences.MAIN_TEXT);
		mainText.setFont(new Font(15));
		mainText.setX(30);
		mainText.setY(30);	
		mainText.setTextAlignment(TextAlignment.CENTER);
		mainRoot.getChildren().add(mainText);	// adding mainText to the list of Nodes
											// to be rendered in the Scene

		Text[] levelText = new Text[5];	
		for(int i = 0;  i < Preferences.N_LEVELS; i++) {
			// for each level, displays its name in the Text and sets correct position
			levelText[i] = new Text(Preferences.LEVEL_NAME[i]);
			levelText[i].setX(100);
			levelText[i].setY(inOpPosition + 30*i);
			levelText[i].setFont(new Font(15));
			mainRoot.getChildren().add(levelText[i]);
		}
		
		lvlSelector.setX(30);
		lvlSelector.setY(inOpPosition - 15);
		mainRoot.getChildren().add(lvlSelector);

		// Creating a Scene by passing the group object, height and width
		// the group object contains all the nodes to be rendered
		// A scene represents the physical contents of a JavaFX application
		Scene mainScene = new Scene(mainRoot, screenWidth, screenHeight);
		
		// whenever a key is pressed, it will look for a 
		// handle method inside of THIS CLASS
		mainScene.setOnKeyPressed(this);
		return mainScene;
	}

	// Exception caught possibly thrown by Map constructor
	private Scene gameSceneRender() throws Exception {

		// according to the selected level, newMap creates all the imageView objects
		// and stores them in a ObservableList
		newMap.createMap(Preferences.LEVEL_SRC[selectedLevel]);

		Group gameRoot = new Group(newMap.getTilesList());
		try{ 
			if(!isRunning)
				usrPacman = new Pacman(0, 0);
			gameRoot.getChildren().add(usrPacman.getPlayerImage());
		} catch(Exception e) {
			System.out.println(e);
		}

		Scene gameScene = new Scene(gameRoot, screenWidth, screenHeight);
		gameScene.setOnKeyPressed(this);
		return gameScene;
	}

	@Override // overwritting an Application class method
	public void start(Stage primaryStage) throws Exception {
		// a stage (that is a window) contains ALL THE OBJECTS of a JavaFX applicaton.
		// it is represented by Stage class
		// primaryStage, created by the plataform itself, is passed as argument in this method
		try {
			mainWindow = primaryStage;

			mainWindow.setTitle("Pacman");
			mainWindow.getIcons().add(new Image("./images/pacman-icon.png"));
			mainWindow.setScene(mainSceneRender()); // Adding the Scene to the Stage
			mainWindow.setResizable(false);
			mainWindow.show(); // Displaying the contents ot the stage
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void menuCommand(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				selectedLevel++;	// changing selected option
				if(selectedLevel == Preferences.N_LEVELS)
					selectedLevel = 0;
				lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
				break; 

			case UP :
				selectedLevel--;	// changing selected option
				if(selectedLevel == -1)
					selectedLevel = Preferences.N_LEVELS - 1;
				lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
				break; 

			case ENTER :
				try{
					mainWindow.setScene(gameSceneRender()); // changes to gameplay Scene
					isRunning = true; 
				} catch(Exception e) {
					System.out.println(e);
				}
				break; 
		}
	}

	private void runningCommand(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				usrPacman.moveDown();
				break; 

			case UP :
				usrPacman.moveUp();
				break;

			case LEFT :
				usrPacman.moveLeft();
				break; 

			case RIGHT :
				usrPacman.moveRight();
				break;
		}
		
	}

	@Override 	// implementing method from interface 
	public void handle(KeyEvent event) {
		if(isRunning) {
			try {	
				runningCommand(event);	
			} catch(Exception e) {
				System.out.println(e);
			}
		} else {
			
			menuCommand(event);
		}
	}

	public static void main(String[] args) {
		launch(args);	// static method inherited from the class Application
		// internally, it calls start(), that is overwritten in this main class
		// Whenever a JavaFx application is launched:
		// 1. an instance of the application class is created
		// 2. init() method is called
		// 3. the start() method is called
		// 4. the launcher waits for the application to finish and calls the stop() method
	}

}