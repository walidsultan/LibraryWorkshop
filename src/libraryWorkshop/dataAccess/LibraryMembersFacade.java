package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import libraryWorkshop.models.LibraryMember;

public class LibraryMembersFacade extends DataAccessBase implements
		LibraryMembersBehavior {

	public LibraryMember getLibraryMember(String name) {
		List<LibraryMember> allMembers = getAllItems();

		Optional<LibraryMember> libraryMember = LambdaLibrary.getLibraryMemberByName
				.apply(allMembers, name);
		return libraryMember.isPresent() ? libraryMember.get() : null;
	}

	public LibraryMember getLibraryMemberByMemberId(int memberId) {
		List<LibraryMember> allMembers = getAllItems();
		Optional<LibraryMember> libraryMember = LambdaLibrary.getLibraryMemberByMemberId
				.apply(allMembers, memberId);
		return libraryMember.isPresent() ? libraryMember.get() : null;
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
