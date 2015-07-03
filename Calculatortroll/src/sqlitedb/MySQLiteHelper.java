package sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="MobileMediaCMS.db";
	private static final int DATABASE_VERSION = 62;
	
	private String table_DeviceInfo = "CREATE TABLE IF NOT EXISTS DeviceInfo " +
								"(DeviceId TEXT, " +
								"AgenteID INTEGER);";
    private String dropTable_DeviceInfo = "drop table if exists DeviceInfo";
	
	private String table_Agent = "CREATE TABLE IF NOT EXISTS Agent " +
									"(AgentSales NUMERIC, " +
									"AgenteWallet NUMERIC, " +
									"vMerchantNo TEXT, " +
									"userPin TEXT, " +
									"AgentIdPassport TEXT, " +
									"AgentNo NUMERIC, " +
									"AgentPhoto BLOB," +
									" AgentPword TEXT, " +
									"AgentFullName TEXT, " +
									"AgentSurName TEXT, " +
									"AgentMobile TEXT, " +
									"_id INTEGER PRIMARY KEY);";
	private String dropTable_Agent = "drop table if exists Agent";
	
	private String table_DBVersion = "CREATE TABLE IF NOT EXISTS DBVersion " +
									"(versiondate TEXT, " +
									"version INTEGER PRIMARY KEY);";
	private String dropTable_DBVersion = "drop table if exists DBVersion";
	
	private String table_ErrorCodes = "CREATE TABLE IF NOT EXISTS ErrorCodes " +
									 "(ErrorNo NUMERIC, " +
									 "ErrorDesc TEXT);";
	private String dropTable_ErrorCodes = "drop table if exists ErrorCodes";
	
	private String table_Stock = "CREATE TABLE IF NOT EXISTS Stock " +
								 "(ID INTEGER PRIMARY KEY, " +
								 "VoucherType TEXT, " +
								 "VoucherQty NUMERIC, " +
								 "VoucherPin TEXT, " +
								 "VoucherRef TEXT, " +
								 "ImportDate Date, " +
								 "ImportCode NUMERIC);";
	private String dropTable_Stock = "drop table if exists Stock";
	
	private String table_StockItems = "CREATE TABLE IF NOT EXISTS StockItems " +
									"(ID INTEGER PRIMARY KEY, " +
									"GSMProvider TEXT, " +
									"VoucherType TEXT, " +
									"VoucherPrice TEXT, " +
									"OrderCode TEXT, " +
									"SellingPrice CURRENCY, " +
									"ImportCode NUMERIC, " +
									"Instructions TEXT);";
	private String dropTable_StockItems = "drop table if exists StockItems";
	
	private String table_StockRequest = "CREATE TABLE IF NOT EXISTS StockRequest " +
									"(ID INTEGER PRIMARY KEY, " +
									"StkDetails TEXT, " +
									"StkDateReq Date, " +
									"StkTotal TEXT, " +
									"SessionID TEXT, " +
									"errcode TEXT);";
	private String dropTable_StockRequest = "drop table if exists StockRequest";
	
	private String table_Transactions = "CREATE TABLE IF NOT EXISTS Transactions " +
									"(ID INTEGER PRIMARY KEY, " +
									"VoucherType TEXT, " +
									"VoucherNumber NUMERIC, " +
									"VoucherPin TEXT, " +
									"VoucherRef TEXT, " +
									"VoucherValue CURRENCY, " +
									"Date Date, " +
									"Time Time, " +
									"Operator TEXT);";
	private String dropTable_Transactions = "drop table if exists Transactions";
	
	private String table_Vendor = "CREATE TABLE IF NOT EXISTS Vendor " +
									"(VendorID INTEGER PRIMARY KEY, " +
									"VendorName TEXT, " +
									"vndrImage BLOB);";
	private String dropTable_Vendor = "drop table if exists Vendor";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void CreateTables(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		try
		{
			db.execSQL(this.table_DeviceInfo);
			db.execSQL(this.table_Agent);
			db.execSQL(this.table_DBVersion);
			db.execSQL(this.table_ErrorCodes);
			db.execSQL(this.table_Stock);
			db.execSQL(this.table_StockItems);
			db.execSQL(this.table_StockRequest);
			db.execSQL(this.table_Transactions);
			db.execSQL(this.table_Vendor);
			
		}catch(Exception exp)
		{
			
		}
	}
	
  private void DropTables(SQLiteDatabase db)
  {
	  try
		{
		  db.execSQL(this.dropTable_DeviceInfo);
		  db.execSQL(this.dropTable_Agent);
			db.execSQL(this.dropTable_DBVersion);
			db.execSQL(this.dropTable_ErrorCodes);
			db.execSQL(this.dropTable_Stock);
			db.execSQL(this.dropTable_StockItems);
			db.execSQL(this.dropTable_StockRequest);
			db.execSQL(this.dropTable_Transactions);
			db.execSQL(this.dropTable_Vendor);
			
		}catch(Exception exp)
		{
			
		}
  }
  @Override
  public void onCreate(SQLiteDatabase database) 
  {
    CreateTables(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
  {
    DropTables(db);
    onCreate(db);
  }

} 
