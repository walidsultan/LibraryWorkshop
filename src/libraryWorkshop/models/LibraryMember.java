package libraryWorkshop.models;

import java.io.Serializable;
import java.time.LocalDate;

public class LibraryMember extends Person  {
	private CheckoutRecord record = new CheckoutRecord();
	private int memberId;

	public LibraryMember() {

	}

	public void checkout(LendableCopy copy, LocalDate checkoutDate,
			LocalDate dueDate) {
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate,
				dueDate);
		record.addEntry(entry);

	}

	public String toString() {
		return "Checkout record for library member " + firstName +" " + lastName + ": " + record;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
