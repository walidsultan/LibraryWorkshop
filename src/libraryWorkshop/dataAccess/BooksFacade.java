package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import libraryWorkshop.models.Book;

public class BooksFacade extends DataAccessBase implements BooksBehavior {

	ArrayList<Book> allBooks = null;

	public void addBook(Book book) {
		ArrayList<Book> allBooks = getAllItems();

		allBooks.add(book);

		save(allBooks);
	}

	@Override
	public Book getBookByTitle(String title) {
		List<Book> allBooks = getAllItems();

		Optional<Book> foundBook = LambdaLibrary.getBookByTitle.apply(allBooks,
				title);
		return foundBook.isPresent() ? foundBook.get() : null;
	}

	public Book getBookByISBN(String isbn) {
		List<Book> allBooks = getAllItems();
		Optional<Book> foundBook = LambdaLibrary.getBookByISBN.apply(allBooks,
				isbn);

		return foundBook.isPresent() ? foundBook.get() : null;
	}

	public Book getBook(UUID id) {
		List<Book> allBooks = getAllItems();

		Optional<Book> foundBook = LambdaLibrary.getBookById.apply(allBooks,
				id);

		return foundBook.isPresent() ? foundBook.get() : null;
	}

	@Override
	public void deleteBook(int index) {
		ArrayList<Book> allBooks = getAllItems();
		allBooks.remove(index);
		save(allBooks);
	}

	@Override
	public void editBook(Book currentBook) {
		ArrayList<Book> allBooks = getAllItems();

		for (int i = 0; i < allBooks.size(); i++) {
			if (allBooks.get(i).getId().equals(currentBook.getId())) {
				allBooks.remove(i);
				allBooks.add(i, currentBook);
			}
		}

		save(allBooks);
	}

}
