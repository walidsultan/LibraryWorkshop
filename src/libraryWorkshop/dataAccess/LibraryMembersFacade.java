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

	public LibraryMember getLibraryMember(String name) {
		List<LibraryMember> allMembers = getAllLibraryMembers();
		for (LibraryMember member : allMembers) {
			if (member.getFirstName().concat(member.getLastName()).equals(name)) {
				return member;
			}
		}
		return null;
	}

	public void deleteLibraryMember(int index) {
		ArrayList<LibraryMember> allMembers = getAllLibraryMembers();
		allMembers.remove(index);
		save(allMembers);
	}

	public void addLibraryMember(LibraryMember member) {

		ArrayList<LibraryMember> allMembers = getAllLibraryMembers();

		allMembers.add(member);

		save(allMembers);
	}

	public ArrayList<LibraryMember> getAllLibraryMembers() {
		ObjectInputStream in = null;
		ArrayList<LibraryMember> allMembers = new ArrayList<LibraryMember>();
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"LibraryMembers");
			if (path.toFile().isFile()) {
				in = new ObjectInputStream(Files.newInputStream(path));
				allMembers = (ArrayList<LibraryMember>) in.readObject();
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

	private void save(ArrayList<LibraryMember> list) {

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

	public void editLibraryMember(LibraryMember currentMember) {
		ArrayList<LibraryMember> allMembers = getAllLibraryMembers();

		for (int i = 0; i < allMembers.size(); i++) {
			if (allMembers.get(i).getId().equals(currentMember.getId())) {
				allMembers.remove(i);
				allMembers.add(i, currentMember);
			}
		}

		save(allMembers);
	}

}
