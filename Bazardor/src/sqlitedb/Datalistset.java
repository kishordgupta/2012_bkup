package sqlitedb;

import kd.model.collection.Listofdb;
import android.database.Cursor;

import com.kd.model.Catagory;
import com.kd.model.Market;
import com.kd.model.Product;

public class Datalistset {

	public static void GetProductfromdata(Cursor cur) {
		cur.moveToFirst();
		// Log.d("data",cur.getCount()+"");
		while (cur.isAfterLast() == false) {
			Product q = new Product();
			q.setCatagory(cur.getString(cur.getColumnIndex("Catagory")));
			q.setPrice(cur.getString(cur.getColumnIndex("Price")));
			q.setCurrency(cur.getString(cur.getColumnIndex("Currency")));
			q.setName(cur.getString(cur.getColumnIndex("Name")));
			q.setUnit(cur.getString(cur.getColumnIndex("Unit")));
			q.setMarket(cur.getString(cur.getColumnIndex("Market")));

			Listofdb.productlist.add(q);
			// Log.d("data", "?");
			cur.moveToNext();
		}

		cur.close();
	}

	public static void GetCatagoryfromdata(Cursor cur) {
		cur.moveToFirst();
		// Log.d("data",cur.getCount()+"");
		while (cur.isAfterLast() == false) {
			Catagory q = new Catagory();
			q.setCode(cur.getString(cur.getColumnIndex("Code")));

			q.setName(cur.getString(cur.getColumnIndex("Name")));

			Listofdb.Catagorylist.add(q);

			cur.moveToNext();
		}

		cur.close();
	}

	public static void GetMarketfromdata(Cursor cur) {
		cur.moveToFirst();
		// Log.d("data",cur.getCount()+"");
		while (cur.isAfterLast() == false) {
			Market q = new Market();
			q.setCode(cur.getString(cur.getColumnIndex("Code")));

			q.setName(cur.getString(cur.getColumnIndex("Name")));

			// bmp.recycle();
			Listofdb.Marketlist.add(q);
			// Log.d("data", "!");
			cur.moveToNext();
		}

		cur.close();
	}

}
