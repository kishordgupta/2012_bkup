package sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="kishor.db";
	private static final int DATABASE_VERSION = 62;
	
	private String table_DeviceInfo = "CREATE TABLE IF NOT EXISTS PlayerInfo " +
								"(PlayerName TEXT, " +
								"PlayerID TEXT);";
    private String dropTable_DeviceInfo = "drop table if exists PlayerInfo";
	


  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void CreateTables(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		try
		{
			db.execSQL(this.table_DeviceInfo);
		
			
		}catch(Exception exp)
		{
			
		}
	}
	
  private void DropTables(SQLiteDatabase db)
  {
	  try
		{
		  db.execSQL(this.dropTable_DeviceInfo);
		  
			
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
