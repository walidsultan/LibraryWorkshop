package libraryWorkshop.dataAccess;

import libraryWorkshop.models.LibraryMember;

public interface LibraryMembersBehavior {
	public void addLibraryMember(LibraryMember member);

	public LibraryMember getLibraryMember(String name);

	public void deleteLibraryMember(int index);

	public void editLibraryMember(LibraryMember currentMember);
}
