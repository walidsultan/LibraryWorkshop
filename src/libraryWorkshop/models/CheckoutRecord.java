package libraryWorkshop.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckoutRecord implements Serializable {

	public List<CheckoutRecordEntry> getEntries() {
		return entries;
	}

	private static final long serialVersionUID = -3119855589946373695L;
	private LibraryMember member;
	private List<CheckoutRecordEntry> entries = new ArrayList<>();
	private UUID id;

	public CheckoutRecord() {
		this.id = UUID.randomUUID();
	}

	public void addEntry(CheckoutRecordEntry c) {
		entries.add(c);
	}

	public String toString() {
		return entries.toString();
	}

	public UUID getId() {
		return id;
	}

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}
}
