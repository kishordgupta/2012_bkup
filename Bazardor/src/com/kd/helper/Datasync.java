package com.kd.helper;

import kd.model.collection.ListService;
import kd.model.collection.Listofdb;
import sqlitedb.Contentvalueset;
import sqlitedb.Datalistset;
import sqlitedb.DbTablenames;
import sqlitedb.SQLiteWraper;
import android.content.ContentValues;
import android.content.Context;

import com.kd.model.Catagory;
import com.kd.model.Market;
import com.kd.model.Product;
import com.kd.service.GetallCatagory;

public class Datasync {

	public static boolean sync = false;

	public static void servicestar() {

		try {

			GetallCatagory.start();

			sync = true;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void offline(Context context) {
		{
			try {
				SQLiteWraper sqlitedb = new SQLiteWraper(context);

				Datalistset.GetProductfromdata(sqlitedb
						.getTitle(DbTablenames.Product));
				Datalistset.GetMarketfromdata(sqlitedb
						.getTitle(DbTablenames.Market));
				Datalistset.GetCatagoryfromdata(sqlitedb
						.getTitle(DbTablenames.Catagory));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void Datastart(Context context) {
		SQLiteWraper sqlitedb = new SQLiteWraper(context);
		if (sync) {
			sync = false;
			Listofdb.Catagorylist = ListService.Catagorylist;
			Listofdb.productlist = ListService.productlist;
			Listofdb.Marketlist = ListService.Marketlist;
			sqlitedb.clear(DbTablenames.Product);

			for (Product product : ListService.productlist) {
				ContentValues cValues = Contentvalueset
						.Contentvaluesetforproducts(product);
				sqlitedb.Insert(DbTablenames.Product, null, cValues);

			}

			sqlitedb.clear(DbTablenames.Market);

			for (Market stock : ListService.Marketlist) {
				ContentValues cValues = Contentvalueset
						.Contentvaluesetformarket(stock);
				sqlitedb.Insert(DbTablenames.Market, null, cValues);

			}

			sqlitedb.clear(DbTablenames.Catagory);

			for (Catagory catalogue : ListService.Catagorylist) {
				ContentValues cValues = Contentvalueset
						.Contentvaluesetforcatagory(catalogue);
				sqlitedb.Insert(DbTablenames.Catagory, null, cValues);

			}

		

		}

	}
}
