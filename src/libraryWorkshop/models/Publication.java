package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

abstract public class Publication implements Serializable {

	private static final long serialVersionUID = 2010893663327964921L;
	private LocalDate dateDue;
	private String title;

	public void setTitle(String title) {
		this.title = title;
	}

	private UUID id;
	private int maxCheckoutLength;

	protected void setDateDue(LocalDate d) {
		dateDue = d;
	}

	public Publication() {
		this.id = UUID.randomUUID();
	}

	public Publication(String title) {
		this.title = title;
		this.id = UUID.randomUUID();
	}

	public LocalDate getDateDue() {
		return dateDue;
	}

	public String getTitle() {
		return title;
	}

	public UUID getId() {
		return id;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
}
