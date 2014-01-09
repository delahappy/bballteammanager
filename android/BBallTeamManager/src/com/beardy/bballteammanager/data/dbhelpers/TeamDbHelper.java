package com.beardy.bballteammanager.data.dbhelpers;

import com.beardy.bballteammanager.data.contracts.TeamContract.TeamEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeamDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Team.db";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + TeamEntry.TABLE_NAME + " (" +
	    		TeamEntry._ID + " INTEGER PRIMARY KEY," +
	    		TeamEntry.COLUMN_NAME_TEAM_ID + TEXT_TYPE + COMMA_SEP +
	    		TeamEntry.COLUMN_COACH_ID + TEXT_TYPE + COMMA_SEP +
	    		TeamEntry.COLUMN_TEAM_NAME + TEXT_TYPE + " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + TeamEntry.TABLE_NAME;
	
	public TeamDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
		onUpgrade(db, oldVersion, newVersion);
	}

}
