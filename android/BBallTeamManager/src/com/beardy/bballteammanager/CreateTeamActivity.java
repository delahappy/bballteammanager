package com.beardy.bballteammanager;

import com.beardy.bballteammanager.data.contracts.TeamContract.TeamEntry;
import com.beardy.bballteammanager.data.dbhelpers.TeamDbHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class CreateTeamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_team);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_team, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void saveTeam(View view){
		EditText editText = (EditText) findViewById(R.id.editText1);
		String teamName = editText.getText().toString();
		this.getApplicationContext();
		TeamDbHelper dbHelper = new TeamDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(TeamEntry.COLUMN_COACH_ID, "1");
		values.put(TeamEntry.COLUMN_TEAM_NAME, teamName);
		long newRowId = db.insert(TeamEntry.TABLE_NAME, 
				"null", values);
		String toast = teamName + " is team " + newRowId + " in the database";
		toastTeam(toast, Toast.LENGTH_SHORT);
		setResult(RESULT_OK);
		finish();
		
	}
	
	private void toastTeam(String message, int duration){
		Context context = getApplicationContext();
		CharSequence text = message;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
