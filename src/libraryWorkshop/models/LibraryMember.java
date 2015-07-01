package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class LibraryMember extends Person implements Serializable {
	private CheckoutRecord record = new CheckoutRecord();
	private int memberId;
	private UUID id;

	public LibraryMember() {
		id = UUID.randomUUID();
	}

	public void checkout(LendableCopy copy, LocalDate checkoutDate,
			LocalDate dueDate) {
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate,
				dueDate);
		record.addEntry(entry);

	}

	public String toString() {
		return "Checkout record for library member " + firstName + " "
				+ lastName + ": " + record;
	}

	public UUID getId() {
		return id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
