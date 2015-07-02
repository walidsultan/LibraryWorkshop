package libraryWorkshop.dataAccess;

import libraryWorkshop.models.Book;

public interface BooksBehavior {
	public void addBook(Book book);

	public Book getBook(String name);

	public void deleteBook(int index);

	public void editBook(Book currentBook);
}
