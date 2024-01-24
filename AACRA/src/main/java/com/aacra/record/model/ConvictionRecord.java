package com.aacra.record.model;

import java.sql.Date;

public class ConvictionRecord {
	private int criminalId;
	private Date dateOfConviction;
	private String criminalOffenses;
	private String sentencingDetails;
	private String paroleAndProbationStatus;
	
	public int getCriminalId() {
		return criminalId;
	}
	public void setCriminalId(int criminalId) {
		this.criminalId = criminalId;
	}
	public Date getDateOfConviction() {
		return dateOfConviction;
	}
	public void setDateOfConviction(Date dateOfConviction) {
		this.dateOfConviction = dateOfConviction;
	}
	public String getCriminalOffenses() {
		return criminalOffenses;
	}
	public void setCriminalOffenses(String criminalOffenses) {
		this.criminalOffenses = criminalOffenses;
	}
	public String getSentencingDetails() {
		return sentencingDetails;
	}
	public void setSentencingDetails(String sentencingDetails) {
		this.sentencingDetails = sentencingDetails;
	}
	public String getParoleAndProbationStatus() {
		return paroleAndProbationStatus;
	}
	public void setParoleAndProbationStatus(String paroleAndProbationStatus) {
		this.paroleAndProbationStatus = paroleAndProbationStatus;
	}
	
	
}
