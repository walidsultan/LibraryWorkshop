package libraryWorkshop.controllers;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {
	// Holds the screens to be displayed

	private HashMap<String, Node> screens = new HashMap<>();

	public ScreensController() {
		super();
	}

	// Add the screen to the collection
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}

	// Returns the Node with the appropriate name
	public Node getScreen(String name) {
		return screens.get(name);
	}

	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) myLoader.load();
			BaseScreen myScreenController = ((BaseScreen) myLoader
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

	// This method will remove the screen with the given name from the
	// collection of screens
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen didn't exist");
			return false;
		} else {
			return true;
		}
	}
}
