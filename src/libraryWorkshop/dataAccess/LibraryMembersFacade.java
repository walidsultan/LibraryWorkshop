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

public class LibraryMembersFacade implements LibraryMembers {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\libraryWorkshop\\storage\\";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	List<LibraryMember> allMembers = null;

	public void saveLibraryMember(LibraryMember member) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					member.getFirstName() + member.getLastName());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(member);
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

	public LibraryMember readLibraryMember(String name) {
		ObjectInputStream in = null;
		LibraryMember member = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, name);
			in = new ObjectInputStream(Files.newInputStream(path));
			member = (LibraryMember) in.readObject();
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
		return member;
	}

	public void addLibraryMember(LibraryMember member) {

		allMembers = getAllLibraryMembers();
		if (allMembers == null) {
			allMembers = new ArrayList<LibraryMember>();
		}
		allMembers.add(member);

		save();
	}

	public List<LibraryMember> getAllLibraryMembers() {
		ObjectInputStream in = null;

		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"libraryMembers");
			in = new ObjectInputStream(Files.newInputStream(path));
			allMembers = (List<LibraryMember>) in.readObject();
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

	public void save() {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					"libraryMembers");
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(allMembers);
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
