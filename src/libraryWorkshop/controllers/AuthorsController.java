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
import libraryWorkshop.dataAccess.AuthorsFacade;
import libraryWorkshop.models.Author;
import libraryWorkshop.ui.Main;

public class AuthorsController implements BaseController {
	ScreensController myController;

	@FXML
	private TableView<Author> authorsTV;

	@FXML
	private TableColumn<Author, String> firstNameCol;
	@FXML
	private TableColumn<Author, String> lastNameCol;
	@FXML
	private TableColumn<Author, String> phoneCol;
	@FXML
	private TableColumn<Author, String> cityCol;
	@FXML
	private TableColumn<Author, String> stateCol;

	@FXML
	private TableColumn<Author, String> credentialsCol;


	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		AuthorsFacade libraryMembersFacade = new AuthorsFacade();
		List<Author> libraryMembers = libraryMembersFacade
				.getAllItems();

		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"firstName"));

		lastNameCol
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"lastName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Author, String>(
				"phone"));
		cityCol.setCellValueFactory(cellData -> cellData.getValue()
				.getAddress().getCityProperty());

		stateCol.setCellValueFactory(cellData -> cellData.getValue()
				.getAddress().getStateProperty());


		credentialsCol
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"credentials"));
		
		authorsTV.setItems(FXCollections
				.observableArrayList(libraryMembers));
		;
	}

	@FXML
	private void showNewAuthorDialog() {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class
							.getResource("..\\views\\AuthorNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Author");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			AuthorNewDialogController controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setAuthorsTV(authorsTV);
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
	private void deleteAuthor() {
		int selectedIndex = authorsTV.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex >= 0) {
			AuthorsFacade libraryMembersFacade = new AuthorsFacade();
			libraryMembersFacade.deleteAuthor(selectedIndex);
			authorsTV.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Author Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select an Author in the table.");
			alert.showAndWait();
		}
	}

	
	@FXML
	private void editAuthor()
	{
		int selectedIndex = authorsTV.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex >= 0) {
			showEditAuthor(authorsTV.getItems().get(selectedIndex));
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Author Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select an Author in the table.");
			alert.showAndWait();
		}
	}
	
	private void showEditAuthor(Author targetAuthor) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class
							.getResource("..\\views\\AuthorNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Author");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			AuthorNewDialogController controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setAuthorsTV(authorsTV);
			controller.setTargetAuthor(targetAuthor);
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
