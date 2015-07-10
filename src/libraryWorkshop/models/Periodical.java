package libraryWorkshop.models;

import java.io.Serializable;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import libraryWorkshop.dataAccess.AuthorsFacade;

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
