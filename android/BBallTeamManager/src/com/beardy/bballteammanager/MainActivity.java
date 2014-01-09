package com.beardy.bballteammanager;

import org.xmlpull.v1.XmlPullParser;

import com.beardy.bballteammanager.data.contracts.TeamContract.TeamEntry;
import com.beardy.bballteammanager.data.dbhelpers.TeamDbHelper;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final int CREATE_TEAM = 0;
	private static final int EDIT_TEAM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadLayout();
	}

	private void loadLayout() {
		LinearLayout layout = setupLayout();
		
		populateTeamList(layout);
		
		addCreateTeamButton(layout);
		
		setContentView(layout);
	}

	private LinearLayout setupLayout() {
		XmlPullParser parser = getResources().getXml(R.layout.activity_main);
		AttributeSet attributes = Xml.asAttributeSet(parser);
		LinearLayout layout = new LinearLayout(this,attributes);
		layout.setPadding(10, 10, 10, 10);
		layout.setOrientation(LinearLayout.VERTICAL);
		return layout;
	}

	private void addCreateTeamButton(LinearLayout layout) {
		Button button = new Button(this);
		button.setText(R.string.button_create_team);
		button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), CreateTeamActivity.class);	    
			    startActivityForResult(intent, CREATE_TEAM);
				
			}
		});
		
		layout.addView(button);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == CREATE_TEAM && resultCode == RESULT_OK) {
			loadLayout();
		}
	}

	private void populateTeamList(LinearLayout layout) {
		
		
		TeamDbHelper dbHelper = new TeamDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String[] projection = {
				TeamEntry._ID,
				TeamEntry.COLUMN_NAME_TEAM_ID,
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
		
		for(int i = 0; i < cursor.getCount(); i++){
			if(!cursor.moveToNext()){
				break;
			}
			LinearLayout teamLayout = new LinearLayout(this);
			teamLayout.setPadding(5, 5, 5, 5);
			teamLayout.setOrientation(LinearLayout.HORIZONTAL);
			
			int teamId = cursor.getInt(0);
			int coachId = cursor.getInt(1);
			String teamName = cursor.getString(2);
			
			TextView team = new TextView(this);
			team.setText(teamName);
			team.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			Button deleteButton = createDeleteButton(teamId);
			Button editButton = createEditButton(teamId);
			
			teamLayout.setBackgroundColor(getResources().getColor(R.color.light_blue));
			teamLayout.addView(team);
			teamLayout.addView(editButton);
			teamLayout.addView(deleteButton);
			layout.addView(teamLayout);
			
		}
		
		
	}

	private Button createDeleteButton(int teamId) {
		Button deleteButton = new Button(this);
		deleteButton.setText(R.string.button_delete_team);
		deleteButton.setId(teamId);
		deleteButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int teamId = v.getId();
				deleteTeam(teamId);
				loadLayout();
				
			}
		});
		return deleteButton;
	}
	
	private Button createEditButton(int teamId) {
		Button edit = new Button(this);
		edit.setText(R.string.button_edit_team);
		edit.setId(teamId);
		edit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int teamId = v.getId();
				Intent intent = new Intent(getApplicationContext(), PlayerListActivity.class);
				intent.setData(Uri.parse("/team/" + teamId));
			    startActivityForResult(intent, EDIT_TEAM);
				
			}
		});
		return edit;
	}

	protected void deleteTeam(int teamId) {
		TeamDbHelper dbHelper = new TeamDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String selection = TeamEntry._ID + " = ?";
		
		String[] selectionArgs = {String.valueOf(teamId)};
		
		db.delete(TeamEntry.TABLE_NAME, selection, selectionArgs);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
