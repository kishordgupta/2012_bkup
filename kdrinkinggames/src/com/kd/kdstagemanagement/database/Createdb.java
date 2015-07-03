package com.kd.kdstagemanagement.database;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;

public class Createdb {

	public static void create(Context con)
	{
		 DBHelper myDbHelper = new DBHelper(con);
			try {
				myDbHelper.createDataBase();
			} catch (IOException ioe) {
				throw new Error("Unable to create database");
			}
			try {
				myDbHelper.openDataBase();
			}catch(SQLException sqle){
				throw sqle;
			}
//			List<Question> questions = myDbHelper.getQuestionSet(diff, numQuestions);
			myDbHelper.close();
	}
}
