package scenerendering;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

public class ScreenSetting {

	private static final int SCREEN_HEIGHT = 500;
	private static final int SCREEN_WIDTH = 400;
	private static final int NUMBER_OF_LEVELS= 5;

	private static ScreenSetting instance;

	private IScene currentScene;

	private Stage currentStage;

	private ScreenSetting(Stage primaryStage) {
		this.currentStage = primaryStage;
	}

	public static ScreenSetting getInstance(Stage primaryStage) {
		if (instance == null) {
			instance = new ScreenSetting(primaryStage);
			instance.setMenu();
		}
		return instance;
	}

	// may thrown an exception 
	public static ScreenSetting getInstance() {
		return instance;
	}

	public void setMenu() {
		currentScene = new MenuScene(SCREEN_WIDTH, SCREEN_HEIGHT);
		currentStage.setScene(currentScene.getScene());
	}

	public void setGameplay(int selectedLevel) throws ScreenException {
		currentScene = new GameScene(SCREEN_WIDTH, SCREEN_HEIGHT, getLevelSrc(selectedLevel));
		currentStage.setScene(currentScene.getScene());
	}

	private String getLevelSrc(int selectedLevel) throws ScreenException {
		switch(selectedLevel) {
			case 0:
				return "./maps/level1.txt";

			case 1:
				return "./maps/level2.txt";

			case 2:
				return "./maps/level3.txt";

			case 3:
				return "./maps/level4.txt";

			case 4:
				return "./maps/level5.txt";

			default:
				throw new ScreenException("Intern error! Choosen Level does not exist");
		}
	}

	public void closeApplication() {
		currentStage.close();
	}

	public int getNumberOfLevels() {
		return this.NUMBER_OF_LEVELS;
	}
}