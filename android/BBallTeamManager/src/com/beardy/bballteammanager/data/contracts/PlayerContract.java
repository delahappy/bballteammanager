package com.beardy.bballteammanager.data.contracts;

import android.provider.BaseColumns;

public final class PlayerContract {
	public PlayerContract(){}
	
	public static abstract class PlayerEntry implements BaseColumns {
		public static final String TABLE_NAME = "player";
		public static final String COLUMN_FIRST_NAME = "first_name";
		public static final String COLUMN_LAST_NAME = "last_name";
		public static final String COLUMN_MIDDLE_NAME = "middle_name";
		public static final String COLUMN_DEFAULT_POSITION = "default_position";
		public static final String COLUMN_PARENT_NAME1 = "parent_name1";
		public static final String COLUMN_PHONE_NUMBER1 = "phone_number1";
		public static final String COLUMN_EMAIL_ADDRESS1 = "email_address1";
		public static final String COLUMN_PARENT_NAME2 = "parent_name2";
		public static final String COLUMN_PHONE_NUMBER2 = "phone_number2";
		public static final String COLUMN_EMAIL_ADDRESS2 = "email_address2";
		public static final String COLUMN_JERSEY = "jersey";
		public static final String COLUMN_TEAM_ID = "teamid";
		
	}

}
