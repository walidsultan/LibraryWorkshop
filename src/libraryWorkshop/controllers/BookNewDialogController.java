package libraryWorkshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libraryWorkshop.dataAccess.AuthorsFacade;
import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.models.Address;
import libraryWorkshop.models.Author;
import libraryWorkshop.models.Book;

public class BookNewDialogController {

	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtISBN;
	@FXML
	private ComboBox<String> cmbAuthor;
	@FXML
	private TextField txtMaxCheckoutLength;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;

	private Stage dialogStage;
	private Book currentBook;

	@FXML
	private void handleClose() {
		this.dialogStage.close();
	}

	@FXML
	private TableView<Book> libraryBooksTV;

	public void setBooksTV(TableView<Book> libraryBooksTV) {
		this.libraryBooksTV = libraryBooksTV;
	}

	@FXML
	private void initialize() {
		// Load Authors Combo box
		AuthorsFacade authorsFacade = new AuthorsFacade();
		List<Author> authors = authorsFacade.getAllItems();
		List<String> authorNames = new ArrayList<String>();
		for (Author author : authors) {
			authorNames.add(author.getFirstName() + " " + author.getLastName());
		}
		ObservableList<String> authorItems = FXCollections
				.observableArrayList(authorNames);

		cmbAuthor.itemsProperty().set(authorItems);
	}

	@FXML
	private void handleAddBook() {
		Book book = new Book();
		book.setTitle(txtTitle.getText());
		book.setIsbn(txtISBN.getText());
		book.setMaxCheckoutLength(Integer.parseInt(txtMaxCheckoutLength
				.getText()));

		if (cmbAuthor.getValue() != null) {
			AuthorsFacade authorsFacade = new AuthorsFacade();
			Author author = authorsFacade.getAuthor(cmbAuthor.getValue());
			if (author != null) {
				book.setAuthorId(author.getId());
			}
		}

		BooksFacade libraryBooksFacade = new BooksFacade();
		libraryBooksFacade.addBook(book);

		// Update Library Books table view
		List<Book> libraryBooks = libraryBooksFacade.getAllItems();
		libraryBooksTV
				.setItems(FXCollections.observableArrayList(libraryBooks));

		this.dialogStage.close();
	}

	@FXML
	private void handleEditBook() {
		currentBook.setTitle(txtTitle.getText());
		currentBook.setIsbn(txtISBN.getText());
		currentBook.setMaxCheckoutLength(Integer.parseInt(txtMaxCheckoutLength
				.getText()));

		if (cmbAuthor.getValue() != null) {
			AuthorsFacade authorsFacade = new AuthorsFacade();
			Author author = authorsFacade.getAuthor(cmbAuthor.getValue());
			if (author != null) {
				currentBook.setAuthorId(author.getId());
			}
		}
		
		BooksFacade libraryBooksFacade = new BooksFacade();
		libraryBooksFacade.editBook(currentBook);

		// Update Library Books table view
		List<Book> libraryBooks = libraryBooksFacade.getAllItems();
		libraryBooksTV
				.setItems(FXCollections.observableArrayList(libraryBooks));

		this.dialogStage.close();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTargetBook(Book targetBook) {
		currentBook = targetBook;
		txtTitle.setText(targetBook.getTitle());
		txtISBN.setText(targetBook.getIsbn());
		txtMaxCheckoutLength.setText(Integer.toString(targetBook
				.getMaxCheckoutLength()));
		
		AuthorsFacade authorsFacade = new AuthorsFacade();
		Author author = authorsFacade.getAuthor(targetBook.getAuthorId());
		if(author!=null){
			cmbAuthor.setValue(author.toString());
		}

	}

	public void setAddButtonVisibilty(boolean visible) {
		this.btnAdd.visibleProperty().set(visible);
	}

	public void setEditButtonVisibilty(boolean visible) {
		this.btnEdit.visibleProperty().set(visible);
	}

}
