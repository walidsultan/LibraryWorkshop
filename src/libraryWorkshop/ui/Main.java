package libraryWorkshop.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import libraryWorkshop.controllers.ScreensController;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.Address;
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

			mainContainer.setScreen(ScreenIndex.mainScreenID);

			Group root = new Group();
			root.getChildren().addAll(mainContainer);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			mainPrimaryStage=primaryStage;
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();

		List<LibraryMember> libraryMembers = libraryMembersFacade
				.getAllLibraryMembers();

		if (libraryMembers == null || libraryMembers.size() == 0) {
			libraryMembers = new ArrayList<LibraryMember>();
			LibraryMember libraryMember = new LibraryMember(){

				{
					firstName = "Walid";
					lastName = "Sultan";
					phone = "6419808612";
					address = new Address("Nth Street", "FairField", "IA",
							"52557");
				}
			};
			libraryMembersFacade.addLibraryMember(libraryMember);
			
			LibraryMember libraryMember2 = new LibraryMember(){

				{
					firstName = "Yevheniy";
					lastName = "Rohozhnikov";
					phone = "8885551122";
					address = new Address("12 Nth Street", "FairField", "IA",
							"52557");
				}
			};
			libraryMembersFacade.addLibraryMember(libraryMember2);
		}

		launch(args);
	}
}
