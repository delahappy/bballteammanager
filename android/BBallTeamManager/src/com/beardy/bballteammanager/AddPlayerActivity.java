package com.beardy.bballteammanager;

import java.util.LinkedList;
import java.util.List;

import com.beardy.bballteammanager.data.contracts.PlayerContract.PlayerEntry;
import com.beardy.bballteammanager.data.contracts.TeamContract.TeamEntry;
import com.beardy.bballteammanager.data.dbhelpers.PlayerDbHelper;
import com.beardy.bballteammanager.data.dbhelpers.TeamDbHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPlayerActivity extends Activity implements
		PlayerListFragment.Callbacks {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		populateTeamSpinner();

	}
	
	private void populateTeamSpinner() {
		Spinner teamSpinner = (Spinner) findViewById(R.id.team);
		TeamDbHelper dbHelper = new TeamDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String[] projection = {
				TeamEntry.COLUMN_TEAM_NAME
		};
		
		String sortOrder = TeamEntry.COLUMN_TEAM_NAME + " DESC";
		
		Cursor cursor = db.query(
				TeamEntry.TABLE_NAME,
				projection,
				null,
				null,
				null,
				null,
				sortOrder
				);
		
		List<String> teams = new LinkedList<String>();
		for(int i = 0; i < cursor.getCount(); i++){
			if(!cursor.moveToNext()){
				break;
			}
			
			String teamName = cursor.getString(0);
			teams.add(teamName);
			
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, teams);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		teamSpinner.setAdapter(dataAdapter);
		
	}

	public void savePlayer(View view){
		String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
		String middleName = ((EditText) findViewById(R.id.middleName)).getText().toString();
		Spinner positionSpinner = (Spinner) findViewById(R.id.position);
		String position = (String) positionSpinner.getSelectedItem();
		String primaryParentName = ((EditText) findViewById(R.id.parentName1)).getText().toString();
		String primaryParentPhone = ((EditText) findViewById(R.id.parentPhone1)).getText().toString();
		String primaryParentEmail = ((EditText) findViewById(R.id.parentEmail1)).getText().toString();
		String secondaryParentName = ((EditText) findViewById(R.id.parentName2)).getText().toString();
		String secondaryParentPhone = ((EditText) findViewById(R.id.parentPhone2)).getText().toString();
		String secondaryParentEmail = ((EditText) findViewById(R.id.parentEmail2)).getText().toString();
		String jersey = ((EditText) findViewById(R.id.jersey)).getText().toString();
		Spinner teamSpinner = (Spinner) findViewById(R.id.team);
		String team = (String) teamSpinner.getSelectedItem();
		String teamId = resolveTeamId(team);
		
		PlayerDbHelper dbHelper = new PlayerDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(PlayerEntry.COLUMN_FIRST_NAME, firstName);
		values.put(PlayerEntry.COLUMN_LAST_NAME, lastName);
		values.put(PlayerEntry.COLUMN_MIDDLE_NAME, middleName);
		values.put(PlayerEntry.COLUMN_DEFAULT_POSITION, position);
		values.put(PlayerEntry.COLUMN_PARENT_NAME1, primaryParentName);
		values.put(PlayerEntry.COLUMN_PHONE_NUMBER1,primaryParentPhone);
		values.put(PlayerEntry.COLUMN_EMAIL_ADDRESS1,primaryParentEmail);
		values.put(PlayerEntry.COLUMN_PARENT_NAME2, secondaryParentName);
		values.put(PlayerEntry.COLUMN_PHONE_NUMBER2,secondaryParentPhone);
		values.put(PlayerEntry.COLUMN_EMAIL_ADDRESS2,secondaryParentEmail);
		values.put(PlayerEntry.COLUMN_JERSEY, jersey);
		values.put(PlayerEntry.COLUMN_TEAM_ID, teamId);
		db.insert(PlayerEntry.TABLE_NAME, "null", values);
		String toast = firstName + " " + lastName + " saved";
		toastPlayer(toast, Toast.LENGTH_LONG);
		finish();
	}

	private String resolveTeamId(String team) {
		String teamId = "";
		TeamDbHelper dbHelper = new TeamDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String[] projection = {
				TeamEntry._ID
		};
		
		Cursor cursor = db.query(
				TeamEntry.TABLE_NAME,
				projection,
				TeamEntry.COLUMN_TEAM_NAME +" = '" + team + "'",
				null,
				null,
				null,
				null
				);
		
		if(cursor.moveToFirst()){
			teamId = cursor.getString(0);
		}
		
		return teamId;
	}
	
	private void toastPlayer(String message, int duration){
		Context context = getApplicationContext();
		CharSequence text = message;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@Override
	public void onItemSelected(String id) {
		// TODO Auto-generated method stub
		
	}

}
