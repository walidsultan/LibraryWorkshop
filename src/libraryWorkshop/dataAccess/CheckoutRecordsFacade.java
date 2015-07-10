package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import libraryWorkshop.models.CheckoutRecord;

public class CheckoutRecordsFacade extends DataAccessBase implements
		CheckoutRecordsBehavior {

	ArrayList<CheckoutRecord> allCheckoutRecords = null;

	public void addCheckoutRecord(CheckoutRecord record) {
		ArrayList<CheckoutRecord> allCheckoutRecords = getAllItems();

		allCheckoutRecords.add(record);

		save(allCheckoutRecords);
	}

	@Override
	public CheckoutRecord getCheckoutRecord(UUID id) {
		List<CheckoutRecord> allCheckoutRecords = getAllItems();
		Optional<CheckoutRecord> checkoutRecord = allCheckoutRecords.stream()
				.filter(record -> record.getId().equals(id)).findAny();
		return checkoutRecord.isPresent() ? checkoutRecord.get() : null;
	}

	@Override
	public void deleteCheckoutRecord(int index) {
		ArrayList<CheckoutRecord> allCheckoutRecords = getAllItems();
		allCheckoutRecords.remove(index);
		save(allCheckoutRecords);
	}

	@Override
	public void editCheckoutRecord(CheckoutRecord currentCheckoutRecord) {
		ArrayList<CheckoutRecord> allCheckoutRecords = getAllItems();

		for (int i = 0; i < allCheckoutRecords.size(); i++) {
			if (allCheckoutRecords.get(i).getId()
					.equals(currentCheckoutRecord.getId())) {
				allCheckoutRecords.remove(i);
				allCheckoutRecords.add(i, currentCheckoutRecord);
			}
		}

		save(allCheckoutRecords);
	}

	@Override
	public CheckoutRecord getCheckoutRecordByMemberId(int memberId) {

		List<CheckoutRecord> allCheckoutRecords = getAllItems();
		Optional<CheckoutRecord> checkoutRecord = allCheckoutRecords.stream()
				.filter(record -> record.getMember().getMemberId() == memberId)
				.findAny();
		return checkoutRecord.isPresent() ? checkoutRecord.get() : null;
	}

}
