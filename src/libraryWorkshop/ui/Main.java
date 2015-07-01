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
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.Address;
import libraryWorkshop.models.Book;
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
			mainPrimaryStage = primaryStage;
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
			LibraryMember libraryMember1 = new LibraryMember();
			libraryMember1.setFirstName("Walid");
			libraryMember1.setLastName("Sultan");
			libraryMember1.setPhone("6419808612");
			Address address= new Address("Nth Street","FairField","IA","52557");
			libraryMember1.setAddress(address);
			libraryMembersFacade.addLibraryMember(libraryMember1);



			LibraryMember libraryMember2 = new LibraryMember();
			libraryMember2.setFirstName("Yevheniy");
			libraryMember2.setLastName("Rohozhnikov");
			libraryMember2.setPhone("8885554545");
			address= new Address("12 Nth Street","FairField","IA","52557");
			libraryMember2.setAddress(address);
			libraryMembersFacade.addLibraryMember(libraryMember2);
		}

//		BooksFacade booksFacade=new BooksFacade();
//		
//		List<Book> books=booksFacade.getAllBooks();
		
		launch(args);
	}
}
