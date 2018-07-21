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

import engine.Preferences;

public class MenuScene {
	private Scene menuScene; // A scene represents the physical contents of a JavaFX application
	
	private Rectangle lvlSelector; // moves towards user options

	private int selectedLevel = 0;
	private int inOpPosition = 150;	// inicial Option position

	public MenuScene(int screenWidth, int screenHeight, EventHandler<KeyEvent> value) {
		// a Group node is a collective node that contains A LIST of CHILDREN NODES
		// this list is accessible through getChildren() method
		// whenever the groups node is rendered, all its children are rendered in order
		Group menuRoot = new Group();
		
		Text mainText = new Text(Preferences.MAIN_TEXT);
		mainText.setFont(new Font(15));
		mainText.setX(30);
		mainText.setY(30);	
		mainText.setTextAlignment(TextAlignment.CENTER);
		// adding mainText to the list of Nodes to be rendered in the scene
		menuRoot.getChildren().add(mainText);	

		Text[] levelText = new Text[5];	
		for(int i = 0;  i < Preferences.N_LEVELS; i++) {
			// for each level, displays its name in the Text and sets correct position
			levelText[i] = new Text(Preferences.LEVEL_NAME[i]);
			levelText[i].setX(100);
			levelText[i].setY(inOpPosition + 30*i);
			levelText[i].setFont(new Font(15));
			menuRoot.getChildren().add(levelText[i]);
		}
		
		lvlSelector = new Rectangle(45, 15);
		lvlSelector.setX(30);
		lvlSelector.setY(inOpPosition - 15);
		menuRoot.getChildren().add(lvlSelector);

		menuScene = new Scene(menuRoot, screenWidth, screenHeight);
		this.menuScene.setOnKeyPressed(value);
		// whenever a key is pressed, it will look for a handle method within THIS CLASS
	}

	public void selectUp() {
		this.selectedLevel--;
		if(this.selectedLevel == -1)
			this.selectedLevel = Preferences.N_LEVELS - 1;
		
		this.lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
	}

	public void selectDown() {
		this.selectedLevel++;	
		if(this.selectedLevel == Preferences.N_LEVELS)
			this.selectedLevel = 0;

		this.lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
	}

	public int getSelectedLevel() {
		return this.selectedLevel;
	}

	public void setScene(Stage stage) {
		stage.setScene(this.menuScene); 
		stage.setTitle("Pacman");
		stage.getIcons().add(new Image(Preferences.ICON_SRC));
		stage.setResizable(false);
		stage.show();
	}
}