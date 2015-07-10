package libraryWorkshop.models;

import java.io.Serializable;
import java.util.UUID;

import libraryWorkshop.dataAccess.BooksFacade;
import libraryWorkshop.dataAccess.PeriodicalsFacade;

public class Copy implements Serializable{
	
	
	private String copyNo;
	private UUID publicationId;
	private UUID id;

	public Copy() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public String getCopyNo() {
		return copyNo;
	}

	public void setCopyNo(String copyNo) {
		this.copyNo = copyNo;
	}

	public UUID getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(UUID publicationId) {
		this.publicationId = publicationId;
	}

	public Publication getPublication() {
		BooksFacade booksFacade = new BooksFacade();
		PeriodicalsFacade periodicalsFacade = new PeriodicalsFacade();
		Publication publication = booksFacade.getBook(publicationId);
		if (publication != null) {
			return publication;
		} else {
			return periodicalsFacade.getPeriodical(publicationId);
		}
	}
	
	private static final long serialVersionUID = 378233350002042303L;
}
