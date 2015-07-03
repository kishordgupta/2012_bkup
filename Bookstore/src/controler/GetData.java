package controler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import model.Bookshop;

public class GetData {

	Vector<Bookshop> bookshpdata = new Vector<Bookshop>();;
	public void GetDatafromconsole(String read)
	{
		String[] data = read.split(",");
		if(data.length>=5)
		{
		Bookshop bookshop = new Bookshop();
		bookshop.setAuthor(data[4]);
		bookshop.setCatagory(data[3]);
		bookshop.setPrice(Double.parseDouble(data[2]));
		bookshop.setStock(Integer.parseInt(data[1]));
		bookshop.setTitle(data[0]);
		bookshpdata.add(bookshop);
		}
		
	}
	public void writefile()
	{
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("employee.txt");
	         ObjectOutputStream out =
	                            new ObjectOutputStream(fileOut);
	      
	        	 out.writeObject(bookshpdata);
			
	         
	         out.close();
	          fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	public void readfile()
	{ Bookshop b;
		
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream("employee.txt");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	      
	         bookshpdata= (Vector<Bookshop>) in.readObject();
	       
	     
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("");
	         c.printStackTrace();
	         return;
	      }
	      System.out.println("Deserialized Employee...");
	      System.out.println("Name: " + bookshpdata.size());
	      
	  
	}
	
}
