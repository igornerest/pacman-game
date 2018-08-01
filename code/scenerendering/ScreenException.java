package scenerendering; 

public class ScreenException extends Exception {

	public ScreenException(String error) {
		super(error);
	}

	public void handleException() {
		System.out.println(this.toString());
		ScreenSetting.getInstance().setMenu();
	}
}