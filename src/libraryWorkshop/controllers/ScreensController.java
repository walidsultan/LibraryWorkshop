package libraryWorkshop.controllers;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

	private HashMap<String, Node> screens = new HashMap<>();

	public ScreensController() {
		super();
	}

	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}

	public Node getScreen(String name) {
		return screens.get(name);
	}

	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) myLoader.load();
			BaseController myScreenController = ((BaseController) myLoader
					.getController());
			myScreenController.setScreenParent(this);
			addScreen(name, loadScreen);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean setScreen(final String name) {
		if (screens.get(name) != null) {
			if (getChildren().size() > 0) {
				getChildren().remove(0);
			}
			getChildren().add(screens.get(name));
			return true;
		} else {
			System.out.println("screen hasn't been loaded!!! \n");
			return false;
		}

	}

	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			return false;
		} else {
			return true;
		}
	}
}
