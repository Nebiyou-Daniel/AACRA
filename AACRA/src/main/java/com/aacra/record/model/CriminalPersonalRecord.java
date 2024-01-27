package com.aacra.record.model;

import java.sql.Date;

public class CriminalPersonalRecord {
	
	private String fname;
	private String lname;
	private Date dateOfBirth;
	private String gender;
	private String raceAndEthnicity;
	private String kebeleId;
	private String address;
	private String phoneNumber;
	private int criminalId;
	
	
	public int getCriminalId() {
		return criminalId;
	}
	public void setCriminalId(int criminalId) {
		this.criminalId = criminalId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRaceAndEthnicity() {
		return raceAndEthnicity;
	}
	public void setRaceAndEthnicity(String raceAndEthnicity) {
		this.raceAndEthnicity = raceAndEthnicity;
	}
	public String getKebeleId() {
		return kebeleId;
	}
	public void setKebeleId(String kebeleId) {
		this.kebeleId = kebeleId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}