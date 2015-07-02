package libraryWorkshop.dataAccess;


import java.util.ArrayList;

import libraryWorkshop.models.Book;

public class BooksFacade extends DataAccessBase implements BooksBehavior {

	ArrayList<Book> allBooks = null;

	public void addBook(Book book) {

		allBooks = getAllItems();
		if (allBooks == null) {
			allBooks = new ArrayList<Book>();
		}
		allBooks.add(book);

		save(allBooks);
	}


	@Override
	public Book getBook(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editBook(Book currentBook) {
		// TODO Auto-generated method stub
		
	}


	
}
