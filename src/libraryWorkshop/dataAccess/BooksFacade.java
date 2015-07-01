package libraryWorkshop.dataAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.Book;
import libraryWorkshop.models.LibraryMember;

public class BooksFacade extends DataAccessBase  {

	ArrayList<Book> allBooks = null;

	public void addBook(Book book) {

		allBooks = getAllBooks();
		if (allBooks == null) {
			allBooks = new ArrayList<Book>();
		}
		allBooks.add(book);

		save(allBooks);
	}

	public ArrayList<Book> getAllBooks() {
		ObjectInputStream in = null;

		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"Books");
			if (path.toFile().isFile()) {
				in = new ObjectInputStream(Files.newInputStream(path));
				allBooks = (ArrayList<Book>) in.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return allBooks;
	}

	private void save(ArrayList<Book> list) {
			
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"Books");
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(list);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
}
