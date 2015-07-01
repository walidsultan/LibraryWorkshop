package libraryWorkshop.dataAccess;

import java.util.List;

import libraryWorkshop.models.LibraryMember;

public interface LibraryMembers {
	public void addLibraryMember(LibraryMember member);
	public LibraryMember getLibraryMember(String name);
	public List<LibraryMember> getAllLibraryMembers();
}
