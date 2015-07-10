package libraryWorkshop.models;

import java.util.UUID;

public class Author extends Person {

	public String credentials;
	
	public Author() {
		id = UUID.randomUUID();
	}
	
	public String getCredentials() {
		return credentials;
	}


	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	

	



	private static final long serialVersionUID = -3506645484707943700L;

}
