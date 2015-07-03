package utils;

import java.util.ArrayList;

import PRTAndroidLib.PRTAndroidPrint;
import android.content.Context;

public class Printer 
{
	private Context context;
	private PRTAndroidPrint mobilePrint;
	public boolean error;
	private ArrayList<String> availablePrinters;
	
	public Printer(Context context)
	{
		this.context = context;
		mobilePrint = new PRTAndroidPrint();
		error = mobilePrint.PRTInitLib();
	}
	
	public ArrayList<String> GetAvailablePrinters()
	{
		availablePrinters = mobilePrint.PRTGetBondedDevices(); 
		return availablePrinters;
	}
	
	public boolean Connect(String printerName)
	{
		boolean success = false;
		if(availablePrinters != null)
		{
			for(int i = 0 ; i < availablePrinters.size() ; i++)
			{
				if(( i%2 == 0) && availablePrinters.get(i).equals(printerName))
				{
					if(mobilePrint.PRTConnectDevice(availablePrinters, i))
					{
						success = true;
						break;
					}
				}
			}
		}
		return success;
	}
	
	public void SendToPrinter()
	{
		
	}
	
	public void Disconnect()
	{
		mobilePrint.PRTCloseConnect();
		mobilePrint.PRTFreeLib(true);
	}
	
}
