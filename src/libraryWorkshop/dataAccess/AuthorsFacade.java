package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.Author;

public class AuthorsFacade extends DataAccessBase implements
		AuthorsBehavior {

	public Author getAuthor(String name) {
		List<Author> allMembers = getAllItems();
		for (Author member : allMembers) {
			if (member.getFirstName().concat(member.getLastName()).equals(name)) {
				return member;
			}
		}
		return null;
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
