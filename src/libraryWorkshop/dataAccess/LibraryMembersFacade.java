package libraryWorkshop.dataAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.LibraryMember;

public class LibraryMembersFacade extends DataAccessBase implements
		LibraryMembers {

	List<LibraryMember> allMembers = null;

	public LibraryMember getLibraryMember(String name) {
		List<LibraryMember> allMembers = getAllLibraryMembers();
		for (LibraryMember member : allMembers) {
			if (member.getFirstName().concat(member.getLastName()).equals(name)) {
				return member;
			}
		}
		return null;
	}

	public void addLibraryMember(LibraryMember member) {

		allMembers = getAllLibraryMembers();
		if (allMembers == null) {
			allMembers = new ArrayList<LibraryMember>();
		}
		allMembers.add(member);

		save(allMembers);
	}

	public List<LibraryMember> getAllLibraryMembers() {
		ObjectInputStream in = null;

		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"LibraryMembers");
			if (path.toFile().isFile()) {
				in = new ObjectInputStream(Files.newInputStream(path));
				allMembers = (List<LibraryMember>) in.readObject();
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
		return allMembers;
	}

	private void save(List<LibraryMember> list) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"LibraryMembers");
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
