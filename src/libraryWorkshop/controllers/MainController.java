package libraryWorkshop.controllers;

import javafx.fxml.FXML;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class MainController implements BaseController {
	ScreensController myController;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void handleShowLibraryMembers(){
		
		 myController.setScreen(ScreenIndex.libraryMembersID);
		 Main.mainPrimaryStage.setTitle("Library Members");
	}
	
	@FXML
	private void handleShowAuthors(){
		
		 myController.setScreen(ScreenIndex.authorsID);
		 Main.mainPrimaryStage.setTitle("Authors");
	}
}
