package libraryWorkshop.dataAccess;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class DataAccessBase {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\libraryWorkshop\\storage\\";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	
}
