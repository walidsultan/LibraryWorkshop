package libraryWorkshop.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.AuthorsFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Periodical;

public class PeriodicalNewDialogController {

	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtIssueNumber;
	@FXML
	private TextField txtMaxCheckoutLength;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;

	private Stage dialogStage;
	private Periodical currentPeriodical;

	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}

	@FXML
	private TableView<Periodical> libraryPeriodicalsTV;

	public void setPeriodicalsTV(TableView<Periodical> libraryPeriodicalsTV) {
		this.libraryPeriodicalsTV = libraryPeriodicalsTV;
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddPeriodical() {
		Periodical periodical = new Periodical();
		periodical.setTitle(txtTitle.getText());
		periodical.setIssueNumber(txtIssueNumber.getText());
		periodical.setMaxCheckoutLength(Integer.parseInt(txtMaxCheckoutLength
				.getText()));

		PeriodicalsFacade libraryPeriodicalsFacade = new PeriodicalsFacade();
		libraryPeriodicalsFacade.addPeriodical(periodical);

		//Add corresponding copy
		Copy copy = new Copy();
		copy.setCopyNo("1");
		copy.setPublicationId(periodical.getId());

		CopiesFacade libraryCopiesFacade = new CopiesFacade();
		libraryCopiesFacade.addCopy(copy);
		
		// Update Library Periodicals table view
		List<Periodical> libraryPeriodicals = libraryPeriodicalsFacade.getAllItems();
		libraryPeriodicalsTV
				.setItems(FXCollections.observableArrayList(libraryPeriodicals));

		this.dialogStage.close();
	}

	@FXML
	private void handleEditPeriodical() {
		currentPeriodical.setTitle(txtTitle.getText());
		currentPeriodical.setIssueNumber(txtIssueNumber.getText());
		currentPeriodical.setMaxCheckoutLength(Integer.parseInt(txtMaxCheckoutLength
				.getText()));
		
		PeriodicalsFacade libraryPeriodicalsFacade = new PeriodicalsFacade();
		libraryPeriodicalsFacade.editPeriodical(currentPeriodical);

		// Update Library Periodicals table view
		List<Periodical> libraryPeriodicals = libraryPeriodicalsFacade.getAllItems();
		libraryPeriodicalsTV
				.setItems(FXCollections.observableArrayList(libraryPeriodicals));

		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTargetPeriodical(Periodical targetPeriodical) {
		currentPeriodical = targetPeriodical;
		txtTitle.setText(targetPeriodical.getTitle());
		txtIssueNumber.setText(targetPeriodical.getIssueNumber());
		txtMaxCheckoutLength.setText(Integer.toString(targetPeriodical
				.getMaxCheckoutLength()));

	}

	public void setAddButtonVisibilty(boolean visible) {
		this.btnAdd.visibleProperty().set(visible);
	}

	public void setEditButtonVisibilty(boolean visible) {
		this.btnEdit.visibleProperty().set(visible);
	}

}
