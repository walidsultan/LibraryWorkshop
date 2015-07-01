package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.Address;
import libraryWorkshop.models.LibraryMember;

public class LibraryMemberNewDialogController {

	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtZip;

	private Stage dialogStage;

	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}
	
	@FXML
	private TableView<LibraryMember> libraryMembersTV;

	public void setLibraryMembersTV(TableView<LibraryMember> libraryMembersTV) {
		this.libraryMembersTV = libraryMembersTV;
	}

	@FXML
	private void handleAddLibraryMember() {
		LibraryMember libraryMember = new LibraryMember();
		libraryMember.setFirstName(txtFirstName.getText());
		libraryMember.setLastName(txtLastName.getText());
		libraryMember.setPhone(txtPhone.getText());
		Address address = new Address(txtStreet.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText());
		libraryMember.setAddress(address);

		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		libraryMembersFacade.addLibraryMember(libraryMember);

		//Update Library Members table view
		List<LibraryMember> libraryMembers= libraryMembersFacade.getAllLibraryMembers();
		libraryMembersTV.setItems(FXCollections
				.observableArrayList(libraryMembers));
		
		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
