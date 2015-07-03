package com.tmm.android.chuck.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class Initializeoptions {

	public static Category S1;
	public static Category S2;
	public static Category S3;
	public static Category S4;
	public static Category S5;
	public static Category S6;
	public static Category S7;
	public static Category S8;
	public static Category S9;
	public static Category S10;
	public static Category S11;
	public static Category S12;
	public static Category S13;
	public static Category S14;
	public static Category S15;
	static SharedPreferences sharedPreferences = null;
	public static int numberofq=10;
	
	public static void initializesettings(Context con) {

		  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(con);
		 
		  
		  
		  S1=new Category();
		  S1.tablename="1";
		  S1.viewidd="S1";
		  loadSavedPreferences(S1,true);
		  
		  
		  S2=new Category();
		  S2.tablename="2";
		  S2.viewidd="S2";
		  loadSavedPreferences(S2);
		  
		  S3=new Category();
		  S3.tablename="3";
		  S3.viewidd="S3";
		  loadSavedPreferences(S3);
		  
		  S4=new Category();
		  S4.tablename="4";
		  S4.viewidd="S4";
		  loadSavedPreferences(S4);
		  
		  S5=new Category();
		  S5.tablename="5";
		  S5.viewidd="S5";
		  loadSavedPreferences(S5);
		  
		  S6=new Category();
		  S6.tablename="6";
		  S6.viewidd="S6";
		  loadSavedPreferences(S6);
		  
		  S7=new Category();
		  S7.tablename="7";
		  S7.viewidd="S7";
		  loadSavedPreferences(S7);
		  
		  S8=new Category();
		  S8.tablename="8";
		  S8.viewidd="S8";
		  loadSavedPreferences(S8);
		  
		  S9=new Category();
		  S9.tablename="9";
		  S9.viewidd="S9";
		  loadSavedPreferences(S9);
		  
		  S10=new Category();
		  S10.tablename="10";
		  S10.viewidd="S10";
		  loadSavedPreferences(S10);
		  
		  S11=new Category();
		  S11.tablename="11";
		  S11.viewidd="S11";
		  loadSavedPreferences(S11);
		  
		  S12=new Category();
		  S12.tablename="12";
		  S12.viewidd="S12";
		  loadSavedPreferences(S12);
		  
		  S13=new Category();
		  S13.tablename="13";
		  S13.viewidd="S13";
		  loadSavedPreferences(S13);
		  
		  S14=new Category();
		  S14.tablename="14";
		  S14.viewidd="S14";
		  loadSavedPreferences(S14);
		  
		  S15=new Category();
		  S15.tablename="15";
		  S15.viewidd="S15";
		  loadSavedPreferences(S15);
		  
		  numberofq=sharedPreferences.getInt(Constants.NUM_ROUNDS,10);
	}
	
	
	private static void loadSavedPreferences(Category S, boolean b) {
		// TODO Auto-generated method stub
		S.shouldadd= sharedPreferences.getBoolean(S.viewidd,
				b);
		Initializeoptions.numberofq= sharedPreferences.getInt(Constants.NUM_ROUNDS, 10);
	}



	private static void loadSavedPreferences(Category S) {

		S.shouldadd= sharedPreferences.getBoolean(S.viewidd,
				false);

	}

	public static void savePreferences(Category S) {

	Editor editor = sharedPreferences.edit();

		editor.putBoolean(S.viewidd, S.shouldadd);

		editor.commit();

	}
	
	public static void savePreferences() {

		Editor editor = sharedPreferences.edit();

			editor.putInt(Constants.NUM_ROUNDS,Initializeoptions.numberofq);

			editor.commit();

		}

}
