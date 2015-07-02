package libraryWorkshop.dataAccess;

import libraryWorkshop.models.Author;

public interface AuthorsBehavior {
	public void addAuthor(Author author);

	public Author getAuthor(String name);

	public void deleteAuthor(int index);

	public void editAuthor(Author currentAuthor);
}
