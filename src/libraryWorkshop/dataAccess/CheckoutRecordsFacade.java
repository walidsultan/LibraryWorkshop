package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.CheckoutRecordEntry;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.OverdueCopy;

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

	public List<OverdueCopy> getOverdueCopies() {
		List<CheckoutRecord> allRecords = getAllItems();
		List<CheckoutRecordEntry> allEntries = new ArrayList<CheckoutRecordEntry>();
		allRecords
				.forEach(record -> {
					List<CheckoutRecordEntry> recordEntries = new ArrayList<CheckoutRecordEntry>();
					record.getEntries().forEach(entry -> {
						entry.setMember(record.getMember());
						recordEntries.add(entry);
					});
					allEntries.addAll(recordEntries);
				});

		Calendar cal = Calendar.getInstance();

		return allEntries
				.stream()
				.filter(entry -> entry.getDueDate().before(cal.getTime()))
				.map(entry -> {
					OverdueCopy overdueCopy = new OverdueCopy();
					overdueCopy.setCopyNo(entry.getCopy().getCopyNo());
					overdueCopy
							.setPublication(entry.getCopy().getPublication());
					overdueCopy.setMember(entry.getMember());
					return overdueCopy;
				}).collect(Collectors.toList());

	}
}
