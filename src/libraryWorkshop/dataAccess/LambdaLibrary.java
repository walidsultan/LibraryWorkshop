package libraryWorkshop.dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import libraryWorkshop.models.Author;
import libraryWorkshop.models.Book;
import libraryWorkshop.models.CheckoutRecord;
import libraryWorkshop.models.CheckoutRecordEntry;
import libraryWorkshop.models.Copy;
import libraryWorkshop.models.LibraryMember;
import libraryWorkshop.models.OverdueCopy;
import libraryWorkshop.models.Periodical;
import libraryWorkshop.models.Publication;

public class LambdaLibrary {
	public static BiFunction<List<Author>, String, Optional<Author>> getAuthorByName = (
			list, name) -> list.stream()
			.filter(author -> author.toString().equals(name)).findAny();

	public static BiFunction<List<Author>, UUID, Optional<Author>> getAuthorById = (
			list, id) -> list.stream()
			.filter(author -> author.getId().equals(id)).findAny();

	public static BiFunction<List<Book>, String, Optional<Book>> getBookByTitle = (
			list, title) -> list.stream()
			.filter(book -> book.getTitle().equals(title)).findAny();

	public static BiFunction<List<Book>, String, Optional<Book>> getBookByISBN = (
			list, isbn) -> list.stream()
			.filter(book -> book.getIsbn().equals(isbn)).findAny();

	public static BiFunction<List<Book>, UUID, Optional<Book>> getBookById = (
			list, id) -> list.stream().filter(book -> book.getId().equals(id))
			.findAny();

	public static BiFunction<List<CheckoutRecord>, UUID, Optional<CheckoutRecord>> getCheckoutRecordById = (
			list, id) -> list.stream()
			.filter(checkoutRecord -> checkoutRecord.getId().equals(id))
			.findAny();

	public static BiFunction<List<CheckoutRecord>, Integer, Optional<CheckoutRecord>> getCheckoutRecordByMemberId = (
			list, memberId) -> list.stream()
			.filter(record -> record.getMember().getMemberId() == memberId)
			.findAny();

	public static BiConsumer<List<CheckoutRecord>, List<CheckoutRecordEntry>> getOverdueRecordEntries = (
			recordsList, entriesList) -> recordsList
			.forEach(record -> {
				List<CheckoutRecordEntry> recordEntries = new ArrayList<CheckoutRecordEntry>();
				record.getEntries().forEach(entry -> {
					entry.setMember(record.getMember());
					recordEntries.add(entry);
				});
				entriesList.addAll(recordEntries);
			});

	public static BiFunction<List<CheckoutRecordEntry>, Date, List<OverdueCopy>> getOverdueCopies = (
			list, currentTime) -> list.stream()
			.filter(entry -> entry.getDueDate().before(currentTime))
			.map(entry -> {
				OverdueCopy overdueCopy = new OverdueCopy();
				overdueCopy.setCopyNo(entry.getCopy().getCopyNo());
				overdueCopy.setPublication(entry.getCopy().getPublication());
				overdueCopy.setMember(entry.getMember());
				return overdueCopy;
			}).collect(Collectors.toList());

	public static BiFunction<List<Copy>, String, Optional<Copy>> getCopyByCopyNo = (
			list, copyNo) -> list.stream()
			.filter(copy -> copy.getCopyNo().equals(copyNo)).findAny();

	public static BiFunction<List<Copy>, String, Optional<Copy>> searchCopy = (
			list, publicationInfo) -> list
			.stream()
			.filter(copy -> {
				if (copy.isAvailable()) {
					Publication publication = copy.getPublication();
					if (publication.getTitle().equals(publicationInfo)) {
						return true;
					}

					if (publication.getClass() == Book.class) {
						if (((Book) publication).getIsbn().equals(
								publicationInfo)) {
							return true;
						}
					} else if (publication.getClass() == Periodical.class) {
						if (((Periodical) publication).getIssueNumber().equals(
								publicationInfo)) {
							return true;
						}
					}
				}
				return false;
			}).findAny();

	public static BiFunction<List<LibraryMember>, String, Optional<LibraryMember>> getLibraryMemberByName = (
			list, name) -> list.stream()
			.filter(member -> member.toString().equals(name)).findAny();

	public static BiFunction<List<LibraryMember>, Integer, Optional<LibraryMember>> getLibraryMemberByMemberId = (
			list, memberId) -> list.stream()
			.filter(member -> member.getMemberId() == memberId).findAny();

	public static BiFunction<List<Periodical>, String, Optional<Periodical>> getPeriodicalByTitle = (
			list, title) -> list.stream()
			.filter(periodical -> periodical.getTitle().equals(title))
			.findAny();

	public static BiFunction<List<Periodical>, UUID, Optional<Periodical>> getPeriodicalById = (
			list, id) -> list.stream()
			.filter(periodical -> periodical.getId().equals(id)).findAny();

}
