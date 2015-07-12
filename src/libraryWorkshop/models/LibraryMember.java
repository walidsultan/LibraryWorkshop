package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibraryMember extends Person implements Serializable {
	private CheckoutRecord record = new CheckoutRecord();
	private int memberId;

	public LibraryMember() {
		id = UUID.randomUUID();
	}

	public String toString() {
		return "Checkout record for library member " + firstName + " "
				+ lastName + ": " + record;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public StringProperty getMemberIdProperty() {
		return new SimpleStringProperty(Integer.toString(memberId));
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
