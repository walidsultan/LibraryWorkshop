package libraryWorkshop.dataAccess;

import libraryWorkshop.models.Periodical;

public interface PeriodicalsBehavior {
	public void addPeriodical(Periodical periodical);

	public Periodical getPeriodical(String name);

	public void deletePeriodical(int index);

	public void editPeriodical(Periodical currentPeriodical);
}
