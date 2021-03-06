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
	private void handleShowLibraryMembers() {

		myController.setScreen(ScreenIndex.libraryMembersID);
		Main.mainPrimaryStage.setTitle("Library Members");
	}

	@FXML
	private void handleShowAuthors() {

		myController.setScreen(ScreenIndex.authorsID);
		Main.mainPrimaryStage.setTitle("Authors");
	}

	@FXML
	private void handleShowBooks() {

		myController.setScreen(ScreenIndex.booksID);
		Main.mainPrimaryStage.setTitle("Books");
	}

	@FXML
	private void handleShowPeriodicals() {
		myController.setScreen(ScreenIndex.periodicalsID);
		Main.mainPrimaryStage.setTitle("Periodicals");
	}

	@FXML
	private void handleShowCopies() {

		myController.unloadScreen(ScreenIndex.copiesID);
		myController.loadScreen(ScreenIndex.copiesID, ScreenIndex.copiesFile);
		
		myController.setScreen(ScreenIndex.copiesID);
		Main.mainPrimaryStage.setTitle("Copies");
	}

	@FXML
	private void handleShowCheckoutPublication() {
		myController.setScreen(ScreenIndex.checkoutPublicationID);
		Main.mainPrimaryStage.setTitle("Checkout Publication");
	}

	@FXML
	private void handleShowOverdueCopies() {
		myController.unloadScreen(ScreenIndex.overdueCopiesID);
		myController.loadScreen(ScreenIndex.overdueCopiesID,
				ScreenIndex.overdueCopiesFile);
		myController.setScreen(ScreenIndex.overdueCopiesID);
		Main.mainPrimaryStage.setTitle("Overdue Copies");
	}

}
