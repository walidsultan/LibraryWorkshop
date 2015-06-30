package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;

abstract public class Publication implements Serializable {
	
	private static final long serialVersionUID = 2010893663327964921L;
	private LocalDate dateDue;
	private String title;
	protected void setDateDue(LocalDate d) {
		dateDue = d;
	}
	public Publication(String title) {
		this.title = title;
	}
	public LocalDate getDateDue() {
		return dateDue;
	}
	public String getTitle() {
		return title;
	}
}
