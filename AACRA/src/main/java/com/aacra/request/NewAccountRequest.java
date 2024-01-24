package com.aacra.request;

import java.io.InputStream;

public class NewAccountRequest {
	private String userFname;
	private String userLname;
	private String userEmail;
	private String userPassword;
	private String userRole;
	private InputStream officerIdPhoto;
	private String workLocation;
	private int officerWereda;
	private InputStream analystCertification;
	
	public String getUserFname() {
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public InputStream getOfficerIdPhoto() {
		return officerIdPhoto;
	}
	public void setOfficerIdPhoto(InputStream officerIdPhoto) {
		this.officerIdPhoto = officerIdPhoto;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public int getOfficerWereda() {
		return officerWereda;
	}
	public void setOfficerWereda(int officerWereda) {
		this.officerWereda = officerWereda;
	}
	public InputStream getAnalystCertification() {
		return analystCertification;
	}
	public void setAnalystCertification(InputStream analystCertification) {
		this.analystCertification = analystCertification;
	}
	
}
