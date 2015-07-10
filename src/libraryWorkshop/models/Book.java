package libraryWorkshop.models;

import java.io.Serializable;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import libraryWorkshop.dataAccess.AuthorsFacade;

public class Book extends Publication implements Serializable {

	private String isbn;
	private boolean available;
	private UUID authorId;

	public Book() {

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Book(String isbn, String title) {
		super(title);
		this.isbn = isbn;
	}

	public void isAvailable(boolean b) {
		available = b;
	}

	@Override
	public String toString() {
		return "isbn: " + isbn + ", available: " + available;
	}

	public UUID getAuthorId() {
		return authorId;
	}

	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}

	public StringProperty getAuthorNameProperty() {
		AuthorsFacade authorsFacade = new AuthorsFacade();
		Author author = authorsFacade.getAuthor(authorId);
		if (author != null) {
			return new SimpleStringProperty(author.toString());
		} else {
			return new SimpleStringProperty("");
		}
	}

	private static final long serialVersionUID = -697183216174030295L;
}
