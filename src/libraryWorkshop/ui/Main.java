package libraryWorkshop.ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import libraryWorkshop.controllers.ScreensController;
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;
import libraryWorkshop.models.Book;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Periodical;
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
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		CopiesFacade copiesFacade = new CopiesFacade();
		
//
//		ArrayList<Copy> copies = copiesFacade.getAllItems();
//		for (Copy copy : copies) {
//			copy.setAvailable(true);
//			copiesFacade.editCopy(copy);
//		}

//		BooksFacade booksFacade = new BooksFacade();
//		ArrayList<Book> allBooks= booksFacade.getAllItems();
//		
//		PeriodicalsFacade periodicalsFacade = new PeriodicalsFacade();
//		ArrayList<Periodical> allPeriodicals= periodicalsFacade.getAllItems();
//		
//		for(Book book : allBooks){
//			Copy copy= new Copy();
//			copy.setAvailable(true);
//			copy.setCopyNo("1");
//			copy.setPublicationId(book.getId());
//			copiesFacade.addCopy(copy);
//		}
//		
//
//		for(Periodical book : allPeriodicals){
//			Copy copy= new Copy();
//			copy.setAvailable(true);
//			copy.setCopyNo("1");
//			copy.setPublicationId(book.getId());
//			copiesFacade.addCopy(copy);
//		}
		
		launch(args);
	}
}
