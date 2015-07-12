package libraryWorkshop.models;

public class OverdueCopy {
	private String copyNo;
	private Publication publication;
	private LibraryMember member;
	public String getCopyNo() {
		return copyNo;
	}
	public void setCopyNo(String copyNo) {
		this.copyNo = copyNo;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public LibraryMember getMember() {
		return member;
	}
	public void setMember(LibraryMember member) {
		this.member = member;
	}
	
	
}
