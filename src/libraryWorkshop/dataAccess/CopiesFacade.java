package libraryWorkshop.dataAccess;


import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.Copy;

public class CopiesFacade extends DataAccessBase implements CopiesBehavior {

	ArrayList<Copy> allCopies = null;

	public void addCopy(Copy copy) {
		ArrayList<Copy> allCopies = getAllItems();

		allCopies.add(copy);

		save(allCopies);
	}


	@Override
	public Copy getCopy(String copyNo) {
		List<Copy> allCopies = getAllItems();
		for (Copy copy : allCopies) {
			if (copy.getCopyNo().equals(copyNo)) {
				return copy;
			}
		}
		return null;
	}

	@Override
	public void deleteCopy(int index) {
		ArrayList<Copy> allCopies = getAllItems();
		allCopies.remove(index);
		save(allCopies);	
	}

	@Override
	public void editCopy(Copy currentCopy) {
		ArrayList<Copy> allCopies = getAllItems(); 

		for (int i = 0; i < allCopies.size(); i++) {
			if (allCopies.get(i).getId().equals(currentCopy.getId())) {
				allCopies.remove(i);
				allCopies.add(i, currentCopy);
			}
		}

		save(allCopies);
	}


	
}
