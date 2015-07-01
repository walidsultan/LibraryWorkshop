package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.Address;
import libraryWorkshop.models.LibraryMember;

public class LibraryMemberNewDialogController {

	@FXML
	private TextField txtMemberId;
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

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;

	private Stage dialogStage;
	private LibraryMember currentMember;

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
		libraryMember.setMemberId(Integer.parseInt(txtMemberId.getText()));
		libraryMember.setFirstName(txtFirstName.getText());
		libraryMember.setLastName(txtLastName.getText());
		libraryMember.setPhone(txtPhone.getText());
		Address address = new Address(txtStreet.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText());
		libraryMember.setAddress(address);

		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		libraryMembersFacade.addLibraryMember(libraryMember);

		// Update Library Members table view
		List<LibraryMember> libraryMembers = libraryMembersFacade
				.getAllLibraryMembers();
		libraryMembersTV.setItems(FXCollections
				.observableArrayList(libraryMembers));

		this.dialogStage.close();
	}

	@FXML
	private void handleEditLibraryMember() {
		currentMember.setMemberId(Integer.parseInt(txtMemberId.getText()));
		currentMember.setFirstName(txtFirstName.getText());
		currentMember.setLastName(txtLastName.getText());
		currentMember.setPhone(txtPhone.getText());
		Address address = new Address(txtStreet.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText());
		currentMember.setAddress(address);
		
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		libraryMembersFacade.editLibraryMember(currentMember);
		
		// Update Library Members table view
				List<LibraryMember> libraryMembers = libraryMembersFacade
						.getAllLibraryMembers();
				libraryMembersTV.setItems(FXCollections
						.observableArrayList(libraryMembers));
				
		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTargetLibraryMember(LibraryMember targetMember) {
		currentMember = targetMember;
		txtMemberId.setText(Integer.toString(targetMember.getMemberId()));
		txtFirstName.setText(targetMember.getFirstName());
		txtLastName.setText(targetMember.getLastName());
		txtPhone.setText(targetMember.getPhone());
		if (targetMember.getAddress() != null) {
			Address address = targetMember.getAddress();
			txtCity.setText(address.getCity());
			txtState.setText(address.getState());
			txtStreet.setText(address.getStreet());
			txtZip.setText(address.getZip());
		}
	}

	public void setAddButtonVisibilty(boolean visible) {
		this.btnAdd.visibleProperty().set(visible);
	}

	public void setEditButtonVisibilty(boolean visible) {
		this.btnEdit.visibleProperty().set(visible);
	}

}
