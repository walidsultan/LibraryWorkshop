package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import libraryWorkshop.models.Author;

public class AuthorsFacade extends DataAccessBase implements AuthorsBehavior {

	public Author getAuthor(String name) {
		List<Author> allMembers = getAllItems();
		Optional<Author> foundAuthor = LambdaLibrary.getAuthorByName.apply(allMembers,name);
		return foundAuthor.isPresent() ? foundAuthor.get() : null;
	}

	public Author getAuthor(UUID id) {
		List<Author> allMembers = getAllItems();
		Optional<Author> foundAuthor = LambdaLibrary.getAuthorById.apply(allMembers,id);
		return foundAuthor.isPresent() ? foundAuthor.get() : null;
	}

	public void deleteAuthor(int index) {
		ArrayList<Author> allMembers = getAllItems();
		allMembers.remove(index);
		save(allMembers);
	}

	public void addAuthor(Author member) {

		ArrayList<Author> allMembers = getAllItems();

		allMembers.add(member);

		save(allMembers);
	}

	public void editAuthor(Author currentMember) {
		ArrayList<Author> allMembers = getAllItems();
		
		for (int i = 0; i < allMembers.size(); i++) {
			if (allMembers.get(i).getId().equals(currentMember.getId())) {
				allMembers.remove(i);
				allMembers.add(i, currentMember);
			}
		}

		save(allMembers);
	}

}
