package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

import controler.GetData;

public class Intput {

	public void Input() throws IOException
	{GetData gd = new GetData();
	gd.readfile();
		while(true)
		{ 
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String inputname=br.readLine();
			gd.GetDatafromconsole(inputname);
			if(inputname.contains("exit"))
				break;
			if(inputname.split(",").length!=5)
			{System.out.print("Insert full data");}
	
		}
		gd.writefile();
		gd.readfile();
	}
}
