package main;

import java.awt.im.InputContext;
import java.io.IOException;

import javax.annotation.Generated;

import view.Intput;

public class Main {

 public static void main(String[] args)
	{
	 System.out.print(true);
	 Intput input = new Intput();
	 try {
		input.Input();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
