package libraryWorkshop.controllers;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.models.Book;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class BooksController implements BaseController {
	ScreensController myController;

	@FXML
	private TableView<Book> booksTV;

	@FXML
	private TableColumn<Book, String> title;
	@FXML
	private TableColumn<Book, String> isbn;
	@FXML
	private TableColumn<Book, String> author;
	@FXML
	private TableColumn<Book, String> maxCheckoutLength;
	
	
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		BooksFacade booksFacade = new BooksFacade();
		List<Book> books = booksFacade.getAllItems();

		title.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"title"));

		isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));

		author.setCellValueFactory(cellData -> cellData.getValue()
				.getAuthorNameProperty());
		
		
		maxCheckoutLength.setCellValueFactory(new PropertyValueFactory<Book, String>("maxCheckoutLength"));
		
		booksTV.setItems(FXCollections
				.observableArrayList(books));
	}

	@FXML
	private void showNewBookDialog() {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\BookNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Book");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BookNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBooksTV(booksTV);
			controller.setAddButtonVisibilty(true);
			controller.setEditButtonVisibilty(false);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	@FXML
	private void deleteBook() {
		int selectedIndex = booksTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Book selectedBook= booksTV.getItems().get(selectedIndex);
			
			BooksFacade libraryMembersFacade = new BooksFacade();
			libraryMembersFacade.deleteBook(selectedIndex);
			
			CopiesFacade copiesFacade = new CopiesFacade();
			copiesFacade.deleteCopyByPublicationId(selectedBook.getId());
			
			booksTV.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Book Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select an Book in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void editBook() {
		int selectedIndex = booksTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			showEditBook(booksTV.getItems().get(selectedIndex));
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Book Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Book in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void navigateBack() {
		myController.setScreen(ScreenIndex.mainScreenID);
		Main.mainPrimaryStage.setTitle("Library Workshop");
	}

	private void showEditBook(Book targetBook) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\BookNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Book");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BookNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBooksTV(booksTV);
			controller.setTargetBook(targetBook);
			controller.setAddButtonVisibilty(false);
			controller.setEditButtonVisibilty(true);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}

	}
}
