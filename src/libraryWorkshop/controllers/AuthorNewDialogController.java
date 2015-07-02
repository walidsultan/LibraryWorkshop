package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.AuthorsFacade;
import libraryWorkshop.models.Address;
import libraryWorkshop.models.Author;

public class AuthorNewDialogController {

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
	private TextField txtCredentials;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;

	private Stage dialogStage;
	private Author currentAuthor;

	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}

	@FXML
	private TableView<Author> libraryAuthorsTV;

	public void setAuthorsTV(TableView<Author> libraryAuthorsTV) {
		this.libraryAuthorsTV = libraryAuthorsTV;
	}

	@FXML
	private void handleAddAuthor() {
		Author author = new Author();
		author.setFirstName(txtFirstName.getText());
		author.setLastName(txtLastName.getText());
		author.setPhone(txtPhone.getText());
		Address address = new Address(txtStreet.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText());
		author.setAddress(address);
		author.setCredentials(txtCredentials.getText());
		
		AuthorsFacade libraryAuthorsFacade = new AuthorsFacade();
		libraryAuthorsFacade.addAuthor(author);

		// Update Library Authors table view
		List<Author> libraryAuthors = libraryAuthorsFacade
				.getAllItems();
		libraryAuthorsTV.setItems(FXCollections
				.observableArrayList(libraryAuthors));

		this.dialogStage.close();
	}

	@FXML
	private void handleEditAuthor() {
		
		currentAuthor.setFirstName(txtFirstName.getText());
		currentAuthor.setLastName(txtLastName.getText());
		currentAuthor.setPhone(txtPhone.getText());
		Address address = new Address(txtStreet.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText());
		currentAuthor.setAddress(address);
		currentAuthor.setCredentials(txtCredentials.getText());
		
		AuthorsFacade libraryAuthorsFacade = new AuthorsFacade();
		libraryAuthorsFacade.editAuthor(currentAuthor);
		
		// Update Library Authors table view
				List<Author> libraryAuthors = libraryAuthorsFacade
						.getAllItems();
				libraryAuthorsTV.setItems(FXCollections
						.observableArrayList(libraryAuthors));
				
		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTargetAuthor(Author targetAuthor) {
		currentAuthor = targetAuthor;
		txtCredentials.setText(targetAuthor.getCredentials());
		txtFirstName.setText(targetAuthor.getFirstName());
		txtLastName.setText(targetAuthor.getLastName());
		txtPhone.setText(targetAuthor.getPhone());
		if (targetAuthor.getAddress() != null) {
			Address address = targetAuthor.getAddress();
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
