package libraryWorkshop.controllers;

import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import libraryWorkshop.dataAccess.CheckoutRecordsFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.LibraryMembersFacade;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.CheckoutRecordEntry;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.LibraryMember;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class CheckoutPublicationController implements BaseController {
	ScreensController myController;

	@FXML
	private TextField txtMemberId;

	@FXML
	private TextField txtPublicationTitle;

	@FXML
	private TextField txtIssueNumber;

	@FXML
	private Label lblIssueNumber;

	@FXML
	private RadioButton periodicalRadioButton;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void checkoutPublication() {

		if (isValidInput()) {
			LibraryMember targetMember = getTargetMember();

			Copy copy = getTargetCopy();

			if (copy != null && targetMember != null) {
				// Both copy and library Member were found
				CheckoutRecordsFacade checkoutRecordsFacade = new CheckoutRecordsFacade();
				CheckoutRecord memberRecord = checkoutRecordsFacade
						.getCheckoutRecordByMemberId(targetMember.getMemberId());

				if (memberRecord == null) {
					memberRecord = new CheckoutRecord();
					memberRecord.setMember(targetMember);
					checkoutRecordsFacade.addCheckoutRecord(memberRecord);
				}

				Calendar cal = Calendar.getInstance();
				Date checkoutDate = cal.getTime();

				cal.setTime(new Date()); // Now use today date.
				cal.add(Calendar.DATE, 5); // Adding 5 days
				Date dueDate = cal.getTime();

				copy.setAvailable(false);
				CopiesFacade copiesFacade = new CopiesFacade();
				copiesFacade.editCopy(copy);

				CheckoutRecordEntry entry = new CheckoutRecordEntry(copy,
						checkoutDate, dueDate);
				memberRecord.addEntry(entry);
				checkoutRecordsFacade.editCheckoutRecord(memberRecord);

				txtMemberId.setText("");
				txtPublicationTitle.setText("");
				txtIssueNumber.setText("");
				showAlert("Checkout Success", "The copy "
						+ copy.getPublication().getTitle()
						+ " was checked out successfully.");
			}

		}

	}

	private LibraryMember getTargetMember() {
		LibraryMembersFacade libraryMembersFacade = new LibraryMembersFacade();
		LibraryMember targetMember = libraryMembersFacade
				.getLibraryMemberByMemberId(Integer.parseInt(txtMemberId
						.getText()));
		if (targetMember == null) {
			showAlert("Wrong Input", "The library member with Id "
					+ txtMemberId.getText() + " wasn't found.");
		}
		return targetMember;
	}

	private Copy getTargetCopy() {
		CopiesFacade copiesFacade = new CopiesFacade();

		Copy targetCopy = copiesFacade.searchCopy(
				txtPublicationTitle.getText(), txtIssueNumber.getText());
		if (targetCopy == null) {
			showAlert(
					"Wrong Input",
					"Couldn't find the requested publication or it is not available. Please try again.");
		}
		return targetCopy;
	}

	private boolean isValidInput() {
		boolean isValid = true;
		if (!txtMemberId.getText().matches("\\d+")) {
			isValid = false;

			showAlert("Wrong Input", "Please enter valid Member Id.");
		}

		if (txtPublicationTitle.getText().isEmpty()) {
			isValid = false;

			showAlert("Wrong Input",
					"Please enter valid publication ISBN, title or issue number.");
		}

		if (periodicalRadioButton.isSelected()
				&& txtIssueNumber.getText().isEmpty()) {
			isValid = false;
			showAlert("Wrong Input", "Please enter valid issue number.");
		}

		return isValid;
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private void navigateBack() {
		myController.setScreen(ScreenIndex.mainScreenID);
		Main.mainPrimaryStage.setTitle("Library Workshop");
	}

	@FXML
	private void setBookType() {
		txtIssueNumber.visibleProperty().set(false);
		lblIssueNumber.visibleProperty().set(false);
		txtIssueNumber.setText("");
	}

	@FXML
	private void setPeriodicalType() {
		txtIssueNumber.visibleProperty().set(true);
		lblIssueNumber.visibleProperty().set(true);
	}

}
