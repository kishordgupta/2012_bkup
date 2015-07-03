package com.kd.kdstagemanagement.model;

public class Timeline {

	public String time="";
	public String Type="";
	public int count=0;;
	public void reduceCount() {
		// TODO Auto-generated method stub
		if (count > 0) {
			count--;
		}
	}


	public String getCountAsString() {
		return Integer.toString(count);
	}
}
