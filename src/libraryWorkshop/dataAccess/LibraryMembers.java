package libraryWorkshop.dataAccess;

import libraryWorkshop.models.LibraryMember;

public interface LibraryMembers {
	public void saveLibraryMember(LibraryMember member);
	public LibraryMember readLibraryMember(String name);
}
