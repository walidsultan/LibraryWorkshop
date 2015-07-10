package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Publication;

public class CopyNewDialogController {

	@FXML
	private Label lblPublicationTitle;
	@FXML
	private TextField txtCopyNo;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	
	private Stage dialogStage;
	private Copy currentCopy;
	private Publication currentPublication;
	
	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}

	@FXML
	private TableView<Copy> libraryCopiesTV;

	public void setCopiesTV(TableView<Copy> libraryCopiesTV) {
		this.libraryCopiesTV = libraryCopiesTV;
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddCopy() {
		Copy copy = new Copy();
		copy.setCopyNo(txtCopyNo.getText());
		copy.setPublicationId(currentPublication.getId());

		CopiesFacade libraryCopiesFacade = new CopiesFacade();
		libraryCopiesFacade.addCopy(copy);

		// Update Library Copies table view
		List<Copy> libraryCopies = libraryCopiesFacade.getAllItems();
		libraryCopiesTV
				.setItems(FXCollections.observableArrayList(libraryCopies));

		this.dialogStage.close();
	}

	@FXML
	private void handleEditCopy() {
		currentCopy.setCopyNo(txtCopyNo.getText());
		currentCopy.setPublicationId(currentPublication.getId());
		
		CopiesFacade libraryCopiesFacade = new CopiesFacade();
		libraryCopiesFacade.editCopy(currentCopy);

		// Update Library Copies table view
		List<Copy> libraryCopies = libraryCopiesFacade.getAllItems();
		libraryCopiesTV
				.setItems(FXCollections.observableArrayList(libraryCopies));

		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTargetCopy(Copy targetCopy) {
		currentCopy = targetCopy;
		txtCopyNo.setText(targetCopy.getCopyNo());
		lblPublicationTitle.setText(targetCopy.getPublication().getTitle());

	}

	public void setTargetPublication(Publication targetPublication) {
		currentPublication = targetPublication;
		lblPublicationTitle.setText(targetPublication.getTitle());

	}

	public void setAddButtonVisibilty(boolean visible) {
		this.btnAdd.visibleProperty().set(visible);
	}

	public void setEditButtonVisibilty(boolean visible) {
		this.btnEdit.visibleProperty().set(visible);
	}

}
