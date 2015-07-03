package sqlitedb;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    	
    	String query = "SELECT ROWID from Agent order by ROWID DESC limit 1";
    	Cursor c = database.rawQuery(query, new String[]{});
    	if (c != null && c.moveToFirst()) {
    	    lastId = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
    	}
    	
    	database.close();
    	
    	return lastId;
    }
    
    public boolean CheckLogin(String username,String password)
    {
    	database = openToRead();
    	Cursor c = database.rawQuery("select AgentSurName from Agent where AgentSurName=? and AgentPword=?", new String[]{username,password});
    	boolean isTrue = c.getCount()>0?true:false;
    	database.close();
    	return isTrue;
    }
    
    public boolean CheckIfRegistered(String deviceId)
    {
    	database = openToRead();
    	Cursor c = database.rawQuery("select DeviceId from DeviceInfo where DeviceId=?", new String[]{deviceId});
    	boolean isTrue = c.getCount()>0?true:false;
    	database.close();
    	return isTrue;
    }
    
    public void Update()
    {
    	
    }
    
}
