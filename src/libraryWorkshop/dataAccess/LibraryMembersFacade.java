package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.LibraryMember;

public class LibraryMembersFacade extends DataAccessBase implements
		LibraryMembersBehavior {

	public LibraryMember getLibraryMember(String name) {
		List<LibraryMember> allMembers = getAllItems();
		for (LibraryMember member : allMembers) {
			if (member.toString().equals(name)) {
				return member;
			}
		}
		return null;
	}

	public void deleteLibraryMember(int index) {
		ArrayList<LibraryMember> allMembers = getAllItems();
		allMembers.remove(index);
		save(allMembers);
	}

	public void addLibraryMember(LibraryMember member) {

		ArrayList<LibraryMember> allMembers = getAllItems();

		allMembers.add(member);

		save(allMembers);
	}

	public void editLibraryMember(LibraryMember currentMember) {
		ArrayList<LibraryMember> allMembers = getAllItems(); 

		for (int i = 0; i < allMembers.size(); i++) {
			if (allMembers.get(i).getId().equals(currentMember.getId())) {
				allMembers.remove(i);
				allMembers.add(i, currentMember);
			}
		}

		save(allMembers);
	}

}
