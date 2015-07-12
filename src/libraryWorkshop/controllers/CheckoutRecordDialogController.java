package libraryWorkshop.controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.CheckoutRecordEntry;

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
				 .getCheckoutDateProperty());
		
		 dueDate.setCellValueFactory(cellData -> cellData.getValue()
				 .getDueDateProperty());
		
		checkoutTV.setItems(FXCollections.observableArrayList(entries));
	}


	public void setCheckoutRecord(CheckoutRecord record) {
		this.currentRecord = record;
	}
	
	@FXML
	private void printCheckoutRecord(){
		List<CheckoutRecordEntry> entries = currentRecord.getEntries();
		for(CheckoutRecordEntry entry : entries){
			System.out.println(entry.toString());
		}
	}
}
