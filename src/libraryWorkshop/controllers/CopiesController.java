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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Publication;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class CopiesController implements BaseController {
	ScreensController myController;

	@FXML
	private TextField txtSearchPublication;

	@FXML
	private TableView<Copy> copiesTV;

	@FXML
	private TableColumn<Copy, String> copyNo;
	@FXML
	private TableColumn<Copy, String> title;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		CopiesFacade copiesFacade = new CopiesFacade();
		List<Copy> copies = copiesFacade.getAllItems();

		copyNo.setCellValueFactory(new PropertyValueFactory<Copy, String>(
				"copyNo"));

		title.setCellValueFactory(cellData -> cellData.getValue()
				.getPublication().getTitleProperty());

		copiesTV.setItems(FXCollections.observableArrayList(copies));
	}

	@FXML
	private void searchForPublication() {
		BooksFacade booksFacade = new BooksFacade();
		PeriodicalsFacade periodicalsFacade = new PeriodicalsFacade();
		String input = txtSearchPublication.getText();
		if (!input.isEmpty()) {
			Publication publication = booksFacade.getBookByTitle(input);

			if (publication == null) {
				publication = booksFacade.getBookByISBN(input);
			}

			if (publication == null) {
				publication = periodicalsFacade.getPeriodical(input);
			}
	
			if (publication != null) {
				showNewCopyDialog(publication);
			}else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wrong Input");
				alert.setHeaderText(null);
				alert.setContentText("Couldn't find the requested publication. Please try again.");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Wrong Input");
			alert.setHeaderText(null);
			alert.setContentText("Please enter valid publication ISBN, title or issue number");
			alert.showAndWait();
		}
	}

	private void showNewCopyDialog(Publication publication) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\CopyNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Copy");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CopyNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCopiesTV(copiesTV);
			controller.setTargetPublication(publication);
			controller.setAddButtonVisibilty(true);
			controller.setEditButtonVisibilty(false);

			txtSearchPublication.setText("");
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	@FXML
	private void deleteCopy() {
		int selectedIndex = copiesTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			CopiesFacade libraryMembersFacade = new CopiesFacade();
			libraryMembersFacade.deleteCopy(selectedIndex);
			copiesTV.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Copy Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select an Copy in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void editCopy() {
		int selectedIndex = copiesTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			showEditCopy(copiesTV.getItems().get(selectedIndex));
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Copy Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Copy in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void navigateBack() {
		myController.setScreen(ScreenIndex.mainScreenID);
		Main.mainPrimaryStage.setTitle("Library Workshop");
	}

	private void showEditCopy(Copy targetCopy) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\CopyNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Copy");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CopyNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCopiesTV(copiesTV);
			controller.setTargetCopy(targetCopy);
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
