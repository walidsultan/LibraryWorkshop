package libraryWorkshop.dataAccess;


import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.Book;

public class BooksFacade extends DataAccessBase implements BooksBehavior {

	ArrayList<Book> allBooks = null;

	public void addBook(Book book) {
		ArrayList<Book> allBooks = getAllItems();

		allBooks.add(book);

		save(allBooks);
	}


	@Override
	public Book getBook(String title) {
		List<Book> allBooks = getAllItems();
		for (Book book : allBooks) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
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
