package libraryWorkshop.controllers;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.LibraryMember;
import libraryWorkshop.ui.Main;

public class LibraryMemebersController implements BaseController {
	ScreensController myController;

	@FXML
	private TableView<LibraryMember> libraryMembersTV;

	@FXML
	private TableColumn<LibraryMember, String> firstNameCol;
	@FXML
	private TableColumn<LibraryMember, String> lastNameCol;
	@FXML
	private TableColumn<LibraryMember, String> phoneCol;
	@FXML
	private TableColumn<LibraryMember, String> cityCol;
	@FXML
	private TableColumn<LibraryMember, String> stateCol;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		List<LibraryMember> libraryMembers = libraryMembersFacade
				.getAllLibraryMembers();

		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"firstName"));

		lastNameCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"lastName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
				"phone"));
		cityCol.setCellValueFactory(cellData -> cellData.getValue()
				.getAddress().getCityProperty());

		stateCol.setCellValueFactory(cellData -> cellData.getValue()
				.getAddress().getStateProperty());

		libraryMembersTV.setItems(FXCollections
				.observableArrayList(libraryMembers));
		;
	}
	
	@FXML
	private void showNewLibraryDialog(){
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("..\\views\\LibraryMemberNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Library Member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			LibraryMemberNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}	
	}
}
