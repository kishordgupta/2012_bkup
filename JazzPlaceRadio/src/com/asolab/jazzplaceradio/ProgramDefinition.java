package com.asolab.jazzplaceradio;



public class ProgramDefinition {
	
	private String programName="";
	private String programTime="";
	
	public ProgramDefinition() {
		// TODO Auto-generated constructor stub
		programName="";
		programTime="";
	}
	public ProgramDefinition(String progname, String progTime) {
		// TODO Auto-generated constructor stub
		programName=progname;
		programTime=progTime;
	}
	public String getProgramName(){
		return programName;
	}
	public String getProgramTime(){
		return programTime;
	}

}
