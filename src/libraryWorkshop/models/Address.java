package libraryWorkshop.models;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address implements Serializable{
	
	
	private String street;
	private String city;
	private String state;
	private String zip;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public StringProperty getCityProperty()
	{
		return new SimpleStringProperty(this.city);
	}

	public StringProperty getStateProperty()
	{
		return new SimpleStringProperty(this.state);
	}
	private static final long serialVersionUID = -1631307052644801418L;
}
