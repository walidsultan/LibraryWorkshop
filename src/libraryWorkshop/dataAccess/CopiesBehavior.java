package libraryWorkshop.dataAccess;

import libraryWorkshop.models.Copy;

public interface CopiesBehavior {
	public void addCopy(Copy copy);

	public Copy getCopy(String copyNo);

	public void deleteCopy(int index);

	public void editCopy(Copy currentCopy);
}
