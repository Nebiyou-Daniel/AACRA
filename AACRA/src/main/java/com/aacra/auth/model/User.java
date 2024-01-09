package com.aacra.auth.model;

import com.aacra.auth.utility.PasswordHashing;

public class User {
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String role;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + "]";
	}
	
    public boolean authenticate(String providedPassword) {
        // Compare the provided password with the stored hashed password
        return PasswordHashing.verifyPassword(providedPassword, this.password);
    }
	
	
}
