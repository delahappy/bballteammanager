package com.beardy.bballteammanager.data.dbhelpers;

import com.beardy.bballteammanager.data.contracts.PlayerContract.PlayerEntry;
import com.beardy.bballteammanager.data.contracts.TeamContract.TeamEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "Player.db";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
				PlayerEntry._ID + " INTEGER PRIMARY KEY," +
				PlayerEntry.COLUMN_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_LAST_NAME + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_MIDDLE_NAME + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_DEFAULT_POSITION + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_PARENT_NAME1 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_PHONE_NUMBER1 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_EMAIL_ADDRESS1 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_PARENT_NAME2 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_PHONE_NUMBER2 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_EMAIL_ADDRESS2 + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_JERSEY + TEXT_TYPE + COMMA_SEP +
	    		PlayerEntry.COLUMN_TEAM_ID + TEXT_TYPE +
	    		" )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + TeamEntry.TABLE_NAME;
	
	public PlayerDbHelper(Context context){
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
