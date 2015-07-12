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
import libraryWorkshop.dataAccess.CheckoutRecordsFacade;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.LibraryMember;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class LibraryMemebersController implements BaseController {
	ScreensController myController;

	@FXML
	private TableView<LibraryMember> libraryMembersTV;

	@FXML
	private TableColumn<LibraryMember, Integer> memberIdCol;
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

	@FXML
	private TextField txtSearchMemberId;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		List<LibraryMember> libraryMembers = libraryMembersFacade
				.getAllItems();

		memberIdCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, Integer>(
						"memberId"));

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
	private void showNewLibraryDialog() {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class
							.getResource("..\\views\\LibraryMemberNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Library Member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			LibraryMemberNewDialogController controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setLibraryMembersTV(libraryMembersTV);
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
	private void deleteLibraryMember() {
		int selectedIndex = libraryMembersTV.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex >= 0) {
			LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
			libraryMembersFacade.deleteLibraryMember(selectedIndex);
			libraryMembersTV.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Library Member Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Library Member in the table.");
			alert.showAndWait();
		}
	}

	@FXML
	private void searchForLibraryMember() {
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		List<LibraryMember> libraryMembers = libraryMembersFacade
				.getAllItems();

		if (txtSearchMemberId.getText().matches("\\d+")) {

			LibraryMember targetMember = null;
			for (LibraryMember member : libraryMembers) {
				if (member.getMemberId() == Integer.parseInt(txtSearchMemberId
						.getText())) {
					targetMember = member;
					break;
				}
			}

			if (targetMember != null) {
				showEditLibraryMember(targetMember);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Library Member Not Found");
				alert.setHeaderText(null);
				alert.setContentText("The library member with Id "
						+ txtSearchMemberId.getText() + " wasn't found.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Wrong Input");
			alert.setHeaderText(null);
			alert.setContentText("Please enter valid Member Id");
			alert.showAndWait();
		}
	}

	@FXML
	private void editLibraryMember()
	{
		int selectedIndex = libraryMembersTV.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex >= 0) {
			showEditLibraryMember(libraryMembersTV.getItems().get(selectedIndex));
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Library Member Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Library Member in the table.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void navigateBack()
	{
		 myController.setScreen(ScreenIndex.mainScreenID);
		 Main.mainPrimaryStage.setTitle("Library Workshop");
	}
	
	@FXML
	private void getCheckoutRecordDialog(){
		int selectedIndex = libraryMembersTV.getSelectionModel()
				.getSelectedIndex();
		
		if (selectedIndex >= 0) {
			LibraryMember member= libraryMembersTV.getItems().get(selectedIndex);
			
			CheckoutRecordsFacade checkoutRecordsFacade = new CheckoutRecordsFacade();
			CheckoutRecord record= checkoutRecordsFacade.getCheckoutRecordByMemberId(member.getMemberId());
			
			if( record !=null){
				showCheckoutRecordDialog(record);
			}else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("No checkout record found");
				alert.setHeaderText(null);
				alert.setContentText("No checkout data were found for the selected member.");
				alert.showAndWait();
			}
			
		} else {
			// Nothing selected
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Library Member Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a Library Member in the table.");
			alert.showAndWait();
		}
	}
	
	private void showCheckoutRecordDialog(CheckoutRecord record) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class
							.getResource("..\\views\\CheckoutRecordDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Checkout Record");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CheckoutRecordDialogController controller = loader
					.getController();
			controller.setCheckoutRecord(record);
			controller.loadData();
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
		
	}
	
	private void showEditLibraryMember(LibraryMember targetMember) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					Main.class
							.getResource("..\\views\\LibraryMemberNewDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Library Member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.mainPrimaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			LibraryMemberNewDialogController controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setLibraryMembersTV(libraryMembersTV);
			controller.setTargetLibraryMember(targetMember);
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
