package com.beardy.bballteammanager.data.contracts;

import android.provider.BaseColumns;

public final class TeamContract {
	public TeamContract(){}
	
	public static abstract class TeamEntry implements BaseColumns {
		public static final String TABLE_NAME = "team";
		public static final String COLUMN_NAME_TEAM_ID = "teamid";
		public static final String COLUMN_COACH_ID = "coachid";
		public static final String COLUMN_TEAM_NAME = "team_name";
	}

}
