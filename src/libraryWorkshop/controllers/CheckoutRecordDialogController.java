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
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.CheckoutRecordsFacade;
import libraryWorkshop.dataAccess.CopiesFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;
import libraryWorkshop.models.Book;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.CheckoutRecordEntry;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Publication;
import libraryWorkshop.ui.Main;
import libraryWorkshop.util.ScreenIndex;

public class CheckoutRecordDialogController implements BaseController {
	ScreensController myController;

	@FXML
	private TextField txtSearchPublication;

	@FXML
	private TableView<CheckoutRecordEntry> checkoutTV;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> copyNo;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> title;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> checkoutDate;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> dueDate;

	private Stage dialogStage;
	private CheckoutRecord currentRecord;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	
	public void loadData() {
		List<CheckoutRecordEntry> entries = currentRecord.getEntries();

		copyNo.setCellValueFactory(cellData -> cellData.getValue().getCopy()
				.getCopyNoProperty());

		 title.setCellValueFactory(cellData -> cellData.getValue().getCopy()
		 .getPublication().getTitleProperty());
		
		 checkoutDate.setCellValueFactory(cellData -> cellData.getValue()
				 .getCheckoutDate());
		
		 dueDate.setCellValueFactory(cellData -> cellData.getValue()
				 .getDueDate());
		
		checkoutTV.setItems(FXCollections.observableArrayList(entries));
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setCheckoutRecord(CheckoutRecord record) {
		this.currentRecord = record;
	}
}
