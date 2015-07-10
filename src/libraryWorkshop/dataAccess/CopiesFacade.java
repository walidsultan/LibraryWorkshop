package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import libraryWorkshop.models.Book;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.Periodical;
import libraryWorkshop.models.Publication;

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

	public Copy searchCopy(String publicationInfo) {
		List<Copy> allCopies = getAllItems();
		Optional<Copy> targetCopy = allCopies
				.stream()
				.filter(copy -> {
					if (copy.isAvailable()) {
						Publication publication = copy.getPublication();
						if (publication.getTitle().equals(publicationInfo)) {
							return true;
						}

						if (publication.getClass() == Book.class) {
							if (((Book) publication).getIsbn().equals(
									publicationInfo)) {
								return true;
							}
						} else if (publication.getClass() == Periodical.class) {
							if (((Periodical) publication).getIssueNumber()
									.equals(publicationInfo)) {
								return true;
							}
						}
					}
					return false;
				}).findAny();

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
