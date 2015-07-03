package com.model;

public class User {

	String Firstname="";
	String LastName="";
	String Email="";
	String password="";
	String birthyear="";
	String Birthmonth="";
	String NotifyEmail="";
	String UserID="as";
	String Activated="";
	
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	public String getBirthmonth() {
		return Birthmonth;
	}
	public void setBirthmonth(String birthmonth) {
		Birthmonth = birthmonth;
	}
	public String getNotifyEmail() {
		return NotifyEmail;
	}
	public void setNotifyEmail(String notifyEmail) {
		NotifyEmail = notifyEmail;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getActivated() {
		return Activated;
	}
	public void setActivated(String activated) {
		Activated = activated;
	}
}
