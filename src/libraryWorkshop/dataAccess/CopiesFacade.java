package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<Copy> foundCopy = LambdaLibrary.getCopyByCopyNo.apply(allCopies,copyNo);
	
		return foundCopy.isPresent() ? foundCopy.get() : null;
	}

	public Copy searchCopy(String publicationInfo) {
		List<Copy> allCopies = getAllItems();
		Optional<Copy> targetCopy = LambdaLibrary.searchCopy.apply(allCopies,
				publicationInfo);

		return targetCopy.isPresent() ? targetCopy.get() : null;
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
