package libraryWorkshop.dataAccess;

import java.util.UUID;

import libraryWorkshop.models.Book;
import libraryWorkshop.models.CheckoutRecord;

public interface CheckoutRecordsBehavior {
	public void addCheckoutRecord(CheckoutRecord checkoutRecord);

	public CheckoutRecord getCheckoutRecord(UUID id);

	public CheckoutRecord getCheckoutRecordByMemberId(int memberId);
	
	public void deleteCheckoutRecord(int index);

	public void editCheckoutRecord(CheckoutRecord currentCheckoutRecord);
}
