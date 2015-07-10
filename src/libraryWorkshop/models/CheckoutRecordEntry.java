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
	public CheckoutRecordEntry(Copy copy, Date checkoutDate, Date dueDate){ 
		this.setCopy(copy);
		this.setCheckoutDate(checkoutDate);
		this.setDueDate(dueDate);
	}
//	public String toString() {
//		return "[" + "checkoutdate:" + 
//	        checkoutDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
//	        ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
//	        ", publication: " + copy + "]";
//	}
	public Copy getCopy() {
		return copy;
	}
	public void setCopy(Copy copy) {
		this.copy = copy;
	}
	public StringProperty getCheckoutDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		return new SimpleStringProperty( sdf.format(checkoutDate));
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public StringProperty getDueDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		return new SimpleStringProperty( sdf.format(dueDate));
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
