package sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Market.db";
	private static final int DATABASE_VERSION = 1;

	private String table_Market = "CREATE TABLE IF NOT EXISTS Market "
			+ "(Name TEXT, " + "Code TEXT);";
	private String dropTable_Market = "drop table if exists Market";

	private String table_Product = "CREATE TABLE IF NOT EXISTS Product "
			+ "(Price TEXT, " + "Unit TEXT, " + "Catagory TEXT, "
			+ "Currency TEXT, " + "Market TEXT, " + "Name TEXT, "
			+ "_id INTEGER PRIMARY KEY);";

	private String dropTable_Product = "drop table if exists Product";

	private String table_Catagory = "CREATE TABLE IF NOT EXISTS Catagory "
			+ "(Name TEXT, " + "Code TEXT);";
	private String dropTable_Catagory = "drop table if exists Catagory";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void CreateTables(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(this.table_Market);
			db.execSQL(this.table_Product);
			db.execSQL(this.table_Catagory);

		} catch (Exception exp) {

		}
	}

	private void DropTables(SQLiteDatabase db) {
		try {
			db.execSQL(this.dropTable_Market);
			db.execSQL(this.dropTable_Product);
			db.execSQL(this.dropTable_Catagory);

		} catch (Exception exp) {

		}
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		CreateTables(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		DropTables(db);
		onCreate(db);
	}

}
