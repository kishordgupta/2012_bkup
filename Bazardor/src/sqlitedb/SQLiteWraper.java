package sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteWraper
{
	private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    
    public SQLiteWraper(Context context)
    {
    	dbHelper = new MySQLiteHelper(context);
    }
    
    public SQLiteDatabase openToWrite() throws SQLException 
    {
        database = dbHelper.getWritableDatabase();
        return database;
    }
    
    public SQLiteDatabase openToRead() throws SQLException 
    {
        database = dbHelper.getReadableDatabase();
        return database;
    }
    
    public long Insert(String tableName,String nullColHack,ContentValues cValues)
    {
    	long lastId = -1;
    	
    	database = openToWrite();
    	
    	database.insert(tableName, nullColHack, cValues);
    	
//    	String query = "SELECT ROWID from Agent order by ROWID DESC limit 1";
//    	Cursor c = database.rawQuery(query, new String[]{});
//    	if (c != null && c.moveToFirst()) {
//    	    lastId = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
//    	}
//    	
    	database.close();
    	
    	return lastId;
    }
    
 
    public Cursor getTitle(String DATABASE_TABLE) throws SQLException 
    {
    	database = openToRead();
        Cursor mCursor =
        	database.query(false, DATABASE_TABLE, null, 
                		null, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
      
    	//Log.d("data",mCursor.getCount()+"");
    	
        database.close();
       // Log.d("data",mCursor.getCount()+"");
     return mCursor;
    }
    
    public void clear(String DATABASE_TABLE)
    {
		database = openToRead();

		database.delete(DATABASE_TABLE, null, null);

		database.close();
    }
    
  
}
