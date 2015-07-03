package com.demo.pagol;

public class Option implements Comparable<Option>{
	private String name;
	private String data;
	private String path;
	
	public Option(String name, String data, String path){
		this.name= name;
		this.data= data;
		this.path=path;
	}
	public String getName(){
		return this.name;
	}
	public String getData(){
		return this.data;
	}
	public String getPath(){
		return this.path;
	}

	public int compareTo(Option another) {
		// TODO Auto-generated method stub
		if(this.name!=null)
			return this.name.toLowerCase().compareTo(another.getName().toLowerCase());
		else
			throw new IllegalArgumentException();
	}

}
