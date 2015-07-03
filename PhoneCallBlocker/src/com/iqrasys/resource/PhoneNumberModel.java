package com.iqrasys.resource;

public class PhoneNumberModel {

	private String strTitle = "";
	private String strNumber = "";

	public String getNumberText() {
		return strNumber;
	}

	public void setNumberText(String strText) {
		this.strNumber = strText;
	}

	public String getTitleText() {
		return strTitle;
	}

	public void setTitleText(String title) {
		this.strTitle = title;
	}
}
