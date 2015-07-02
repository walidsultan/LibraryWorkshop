package libraryWorkshop.models;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {

	private static final long serialVersionUID = -3027484395558115688L;

	protected String firstName;
	protected String lastName;
	protected String phone;
	protected Address address;
	protected UUID id;

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
