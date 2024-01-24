package com.aacra.record.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class ArrestRecord {
	private int criminalId;
	private LocalDateTime  dateTimeArrest;
	private String arrestingAgency;
	private String arrestingOfficer;
	private String charges;
	private String arrestLocation;
	
	public int getCriminalId() {
		return criminalId;
	}
	public void setCriminalId(int criminalId) {
		this.criminalId = criminalId;
	}
	public LocalDateTime  getDateTimeArrest() {
		return dateTimeArrest;
	}
	public void setDateTimeArrest(LocalDateTime dateTimeArrest) {
		this.dateTimeArrest = dateTimeArrest;
	}
	public String getArrestingAgency() {
		return arrestingAgency;
	}
	public void setArrestingAgency(String arrestingAgency) {
		this.arrestingAgency = arrestingAgency;
	}
	public String getArrestingOfficer() {
		return arrestingOfficer;
	}
	public void setArrestingOfficer(String arrestingOfficer) {
		this.arrestingOfficer = arrestingOfficer;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getArrestLocation() {
		return arrestLocation;
	}
	public void setArrestLocation(String arrestLocation) {
		this.arrestLocation = arrestLocation;
	}
	
}
