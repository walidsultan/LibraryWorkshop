package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

abstract public class Publication implements Serializable {

	private static final long serialVersionUID = 2010893663327964921L;
	private LocalDate dateDue;
	private String title;
	private boolean isAvailable;

	public void setTitle(String title) {
		this.title = title;
	}

	private UUID id;
	private int maxCheckoutLength;

	protected void setDateDue(LocalDate d) {
		dateDue = d;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Publication() {
		this.id = UUID.randomUUID();
		this.isAvailable=true;
	}

	public Publication(String title) {
		this.title = title;
		this.id = UUID.randomUUID();
		this.isAvailable=true;
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

	public StringProperty getTitleProperty() {
		return new SimpleStringProperty(this.title);
	}

	public StringProperty getIsAvailableProperty() {
		return new SimpleStringProperty(isAvailable?"Yes":"No");
	}
}
