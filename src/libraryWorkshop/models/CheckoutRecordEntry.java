package libraryWorkshop.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = -5907907544731348280L;
	private Copy copy;
	private Date checkoutDate;
	private Date dueDate;
	private LibraryMember member;

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}

	private static final String datePattern = "MM-dd-yyyy";

	public Date getDueDate() {
		return dueDate;
	}
	
	public CheckoutRecordEntry(Copy copy, Date checkoutDate, Date dueDate) {
		this.setCopy(copy);
		this.setCheckoutDate(checkoutDate);
		this.setDueDate(dueDate);
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return "[" + "checkoutdate:" + sdf.format(checkoutDate) + ", dueDate: "
				+ sdf.format(dueDate) + ", publication: " + copy + "]";
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public StringProperty getCheckoutDateProperty() {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return new SimpleStringProperty(sdf.format(checkoutDate));
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public StringProperty getDueDateProperty() {

		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return new SimpleStringProperty(sdf.format(dueDate));
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
