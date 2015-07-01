package libraryWorkshop.controllers;

import java.io.Serializable;

import javafx.fxml.FXML;
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
	private void handleAddLibraryMember() {
		LibraryMember libraryMember = new LibraryMember() {
			{
				firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				phone = txtPhone.getText();
				address = new Address(txtStreet.getText(), txtCity.getText(),
						txtState.getText(), txtZip.getText());
			}
		};

		LibraryMembersFacade libraryMembersFacade =new LibraryMembersFacade();
		libraryMembersFacade.addLibraryMember(libraryMember);
		
		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
