package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import libraryWorkshop.dataAccess.CheckoutRecordsFacade;
import libraryWorkshop.models.OverdueCopy;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class OverdueCopiesController implements BaseController {
	ScreensController myController;

	@FXML
	private TextField txtSearchPublication;

	@FXML
	private TableView<OverdueCopy> copiesTV;

	@FXML
	private TableColumn<OverdueCopy, String> copyNo;
	@FXML
	private TableColumn<OverdueCopy, String> title;
	@FXML
	private TableColumn<OverdueCopy, String> memberId;
	@FXML
	private TableColumn<OverdueCopy, String> memberFirstName;
	@FXML
	private TableColumn<OverdueCopy, String> memberLastName;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void initialize() {
		CheckoutRecordsFacade checkoutRecordsFacade = new CheckoutRecordsFacade();
		List<OverdueCopy> copies = checkoutRecordsFacade.getOverdueCopies();

		copyNo.setCellValueFactory(new PropertyValueFactory<OverdueCopy, String>(
				"copyNo"));

		title.setCellValueFactory(cellData -> cellData.getValue()
				.getPublication().getTitleProperty());

		memberId.setCellValueFactory(cellData -> cellData.getValue()
				.getMember().getMemberIdProperty());

		memberFirstName.setCellValueFactory(cellData -> cellData.getValue()
				.getMember().getFirstNameProperty());

		memberLastName.setCellValueFactory(cellData -> cellData.getValue()
				.getMember().getLastNameProperty());

		copiesTV.setItems(FXCollections.observableArrayList(copies));
	}

	@FXML
	private void navigateBack() {

		myController.setScreen(ScreenIndex.mainScreenID);
		Main.mainPrimaryStage.setTitle("Library Workshop");
	}

}
