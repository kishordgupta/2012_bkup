package sqlitedb;

import android.content.ContentValues;

import com.kd.model.Catagory;
import com.kd.model.Market;
import com.kd.model.Product;

public class Contentvalueset {

	public static ContentValues Contentvaluesetforproducts(Product product) {
		ContentValues cValues = new ContentValues();

		cValues.put("Catagory", product.getCatagory());
		cValues.put("Currency", product.getCurrency());
		cValues.put("Market", product.getMarket());
		cValues.put("Name", product.getName());
		cValues.put("Price", product.getPrice());
		cValues.put("Unit", product.getUnit());

		return cValues;
	}

	public static ContentValues Contentvaluesetforcatagory(Catagory catagory) {
		ContentValues cValues = new ContentValues();

		cValues.put("Code", catagory.getCode());
		cValues.put("Name", catagory.getName());

		return cValues;
	}

	public static ContentValues Contentvaluesetformarket(Market market) {
		ContentValues cValues = new ContentValues();

		cValues.put("Code", market.getCode());
		cValues.put("Name", market.getName());

		return cValues;
	}

}
