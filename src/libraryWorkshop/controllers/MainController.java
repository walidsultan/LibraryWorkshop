package libraryWorkshop.controllers;

import javafx.fxml.FXML;
import libraryWorkshop.util.ScreenIndex;

public class MainController implements BaseScreen {
	ScreensController myController;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML
	private void handleShowLibraryMembers(){
		
		 myController.setScreen(ScreenIndex.libraryMembersID);
	}
}
