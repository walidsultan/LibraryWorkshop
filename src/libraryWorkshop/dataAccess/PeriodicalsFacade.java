package libraryWorkshop.dataAccess;


import java.util.ArrayList;
import java.util.List;

import libraryWorkshop.models.Periodical;

public class PeriodicalsFacade extends DataAccessBase implements PeriodicalsBehavior {

	ArrayList<Periodical> allPeriodicals = null;

	public void addPeriodical(Periodical periodical) {
		ArrayList<Periodical> allPeriodicals = getAllItems();

		allPeriodicals.add(periodical);

		save(allPeriodicals);
	}


	@Override
	public Periodical getPeriodical(String title) {
		List<Periodical> allPeriodicals = getAllItems();
		for (Periodical periodical : allPeriodicals) {
			if (periodical.getTitle().equals(title)) {
				return periodical;
			}
		}
		return null;
	}

	@Override
	public void deletePeriodical(int index) {
		ArrayList<Periodical> allPeriodicals = getAllItems();
		allPeriodicals.remove(index);
		save(allPeriodicals);	
	}

	@Override
	public void editPeriodical(Periodical currentPeriodical) {
		ArrayList<Periodical> allPeriodicals = getAllItems(); 

		for (int i = 0; i < allPeriodicals.size(); i++) {
			if (allPeriodicals.get(i).getId().equals(currentPeriodical.getId())) {
				allPeriodicals.remove(i);
				allPeriodicals.add(i, currentPeriodical);
			}
		}

		save(allPeriodicals);
	}


	
}
