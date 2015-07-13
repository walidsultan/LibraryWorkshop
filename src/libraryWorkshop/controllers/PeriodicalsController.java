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
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;
import libraryWorkshop.models.Book;
import libraryWorkshop.models.Periodical;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class PeriodicalsController implements BaseController {
	ScreensController myController;

	@FXML
	private TableView<Periodical> periodicalsTV;

	@FXML
	private TableColumn<Periodical, String> title;
	@FXML
	private TableColumn<Periodical, String> issueNumber;
	@FXML
	private TableColumn<Periodical, String> maxCheckoutLength;
	
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		PeriodicalsFacade periodicalsFacade = new PeriodicalsFacade();
		List<Periodical> periodicals = periodicalsFacade.getAllItems();

		title.setCellValueFactory(new PropertyValueFactory<Periodical, String>(
				"title"));

		issueNumber.setCellValueFactory(new PropertyValueFactory<Periodical, String>("issueNumber"));
		
		maxCheckoutLength.setCellValueFactory(new PropertyValueFactory<Periodical, String>("maxCheckoutLength"));

		
		periodicalsTV.setItems(FXCollections
				.observableArrayList(periodicals));
	}

	@FXML
	private void showNewPeriodicalDialog() {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\PeriodicalNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Periodical");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PeriodicalNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPeriodicalsTV(periodicalsTV);
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
	private void deletePeriodical() {
		int selectedIndex = periodicalsTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Periodical selectedPeriodical= periodicalsTV.getItems().get(selectedIndex);
			
			PeriodicalsFacade libraryMembersFacade = new PeriodicalsFacade();
			libraryMembersFacade.deletePeriodical(selectedIndex);
		
			CopiesFacade copiesFacade = new CopiesFacade();
			copiesFacade.deleteCopyByPublicationId(selectedPeriodical.getId());
			
			periodicalsTV.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Periodical Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select an Periodical in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void editPeriodical() {
		int selectedIndex = periodicalsTV.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			showEditPeriodical(periodicalsTV.getItems().get(selectedIndex));
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Periodical Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Periodical in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void navigateBack() {
		myController.setScreen(ScreenIndex.mainScreenID);
		Main.mainPrimaryStage.setTitle("Library Workshop");
	}

	private void showEditPeriodical(Periodical targetPeriodical) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("..\\views\\PeriodicalNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Periodical");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PeriodicalNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPeriodicalsTV(periodicalsTV);
			controller.setTargetPeriodical(targetPeriodical);
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
