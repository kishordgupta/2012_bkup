package com.kd.mobilemediacms.list;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.kd.mobilemediacms.R;
import com.kd.model.Product;

public class Listproductstock extends ArrayAdapter<Product> {
	  private final Context context;
	  public static   ArrayList<Product> values=new ArrayList<Product>();

	  public Listproductstock(Context context) {
	    super(context, R.layout.productlist,values);
	    this.context = context;
	    //this.values = values;
	  }
	  

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    final int positionplayer = position;
		ViewHolderaway2 holder;
	    if (convertView != null)
	    {
	    	holder = new ViewHolderaway2();
			
		
	    	return convertView;
	    }
	    else { 
	    	 View row = inflater.inflate(R.layout.productlist, parent, false);
	    
		

		 holder = new ViewHolderaway2();
		 
         holder.ProductItemID = (TextView)row.findViewById(R.id.ProductItemID);
      
         holder.VoucherCost = (TextView)row.findViewById(R.id.VoucherCost);

         holder.SupplierID = (TextView)row.findViewById(R.id.SupplierID);
        
       
	
         row.setTag(holder);

		holder = (ViewHolderaway2) row.getTag();
		Product Productsdata = values.get(positionplayer);
		if(Productsdata!=null)
		{
			holder.ProductItemID.setText(Productsdata.getName());
			holder.VoucherCost.setText(Productsdata.getPrice());
			holder.SupplierID.setText(Productsdata.getUnit());
			
		}
		return row;
	    }
	}


	} 



class ViewHolderaway2 {
	
	TextView ProductItemID;
	
	TextView VoucherCost;
	TextView SupplierID;
	Button buy;

	
}