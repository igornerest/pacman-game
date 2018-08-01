package scenerendering;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 

import javafx.scene.text.Text;
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

import javafx.scene.paint.Color; 

import engine.Preferences;

public class MenuScene implements IScene, EventHandler<KeyEvent>{
	private Scene menuScene; // A scene represents the physical contents of a JavaFX application
	
	private Rectangle lvlSelector; // moves towards user options

	private int selectedLevel = 0;
	private int inOpPosition = 200;	// inicial Option position

	public MenuScene(int screenWidth, int screenHeight) {
		// a Group node is a collective node that contains A LIST of CHILDREN NODES
		// this list is accessible through getChildren() method
		// whenever the groups node is rendered, all its children are rendered in order
		Group menuRoot = new Group();

		ImageView pacmanLogo = new ImageView(new Image("./images/pacman-logo.png"));
		pacmanLogo.setX(0);
		pacmanLogo.setY(0);
		pacmanLogo.setPreserveRatio(true);
		pacmanLogo.setFitHeight(100);
		menuRoot.getChildren().add(pacmanLogo);
		
		Text mainText = new Text("Use UP and DOWN arrows to choose your level!\n"
									+ "Press ENTER to start the game!\n"
									+ "Press ESC anytime to quit");
		mainText.setFont(new Font(15));
		mainText.setX(15);
		mainText.setY(130);	
		mainText.setTextAlignment(TextAlignment.CENTER);
		menuRoot.getChildren().add(mainText);	

		Text[] levelText = new Text[5];	
		for(int i = 0;  i < ScreenSetting.getInstance().getNumberOfLevels(); i++) {
			// for each level, displays its name in the Text and sets correct position
			levelText[i] = new Text("Level " + (i + 1));
			levelText[i].setX(100);
			levelText[i].setY(inOpPosition + 30*i);
			levelText[i].setFont(new Font(15));
			menuRoot.getChildren().add(levelText[i]);
		}

		Text creditText = new Text("Game developed by Igor Neres Trindade, as a final\n"
									+ "project for the Object-oriented programming \n"
									+ "subject at the Federal University of ABC (2018)\n");
		creditText.setFont(new Font(15));
		creditText.setX(10);
		creditText.setY(400);	
		creditText.setTextAlignment(TextAlignment.CENTER);
		menuRoot.getChildren().add(creditText);	

		
		lvlSelector = new Rectangle(15, 15);
		lvlSelector.setX(60);
		lvlSelector.setY(inOpPosition - 15);
		lvlSelector.setFill(new ImagePattern(new Image("./images/arrow.png")));
		menuRoot.getChildren().add(lvlSelector);

		menuScene = new Scene(menuRoot, screenWidth, screenHeight);
		menuScene.setFill(Color.ORANGE);
		this.menuScene.setOnKeyPressed(this);
		// whenever a key is pressed, it will look for a handle method passed as argument
	}

	@Override  // implementing method from interface 
	public void handle(KeyEvent event) {
		switch(event.getCode()) {
			case DOWN :
				this.selectDown();
				break; 

			case UP :
				this.selectUp();
				break; 

			case ESCAPE:
				ScreenSetting.getInstance().closeApplication();
				break;
				
			case ENTER :
				try {
					ScreenSetting.getInstance().setGameplay(this.selectedLevel);
				} catch(ScreenException e) {
					System.out.println(e);
				}
				break; 
		}
	}

	private void selectUp() {
		this.selectedLevel--;
		if(this.selectedLevel == -1)
			this.selectedLevel = ScreenSetting.getInstance().getNumberOfLevels() - 1;
		
		this.lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
	}

	private void selectDown() {
		this.selectedLevel++;	
		if(this.selectedLevel == ScreenSetting.getInstance().getNumberOfLevels())
			this.selectedLevel = 0;

		this.lvlSelector.setY(inOpPosition - 15 + 30 * selectedLevel);
	}

	@Override
	public Scene getScene() {
		return this.menuScene;
	}
}