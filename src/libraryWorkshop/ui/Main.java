package libraryWorkshop.ui;

import java.util.ArrayList;
import java.util.UUID;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import libraryWorkshop.controllers.ScreensController;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.LibraryMember;
import libraryWorkshop.util.ScreenIndex;

public class Main extends Application {
	public static Stage mainPrimaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			ScreensController mainContainer = new ScreensController();
			mainContainer.loadScreen(ScreenIndex.mainScreenID,
					ScreenIndex.mainScreenFile);
			mainContainer.loadScreen(ScreenIndex.libraryMembersID,
					ScreenIndex.libraryMembersFile);
			mainContainer.loadScreen(ScreenIndex.authorsID,
					ScreenIndex.authorsFile);
			mainContainer
					.loadScreen(ScreenIndex.booksID, ScreenIndex.booksFile);
			mainContainer.loadScreen(ScreenIndex.periodicalsID,
					ScreenIndex.periodicalsFile);
			mainContainer.loadScreen(ScreenIndex.checkoutPublicationID,
					ScreenIndex.checkoutPublicationFile);

			mainContainer.setScreen(ScreenIndex.mainScreenID);

			Group root = new Group();
			root.getChildren().addAll(mainContainer);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			mainPrimaryStage = primaryStage;
			mainPrimaryStage.setTitle("Library Workshop");
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
