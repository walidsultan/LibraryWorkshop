package libraryWorkshop.models;

import java.io.Serializable;

public class Periodical extends Publication implements Serializable {

	private String issueNumber;

	public Periodical() {

	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	private static final long serialVersionUID = -697183216174030295L;
}
