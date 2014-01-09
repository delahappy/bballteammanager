package com.beardy.bballteammanager;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beardy.bballteammanager.beans.Player;
import com.beardy.bballteammanager.data.contracts.PlayerContract.PlayerEntry;
import com.beardy.bballteammanager.data.dbhelpers.PlayerDbHelper;
import com.beardy.bballteammanager.dummy.DummyContent;

/**
 * A list fragment representing a list of Players. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link PlayerDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class PlayerListFragment extends ListFragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public PlayerListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		buildFragment();
		
	}

	private void buildFragment() {
		List<Player> players = getPlayers(); 
		
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, players );
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		buildFragment();
	}
	@Override
	public void onResume() {
		super.onResume();
		buildFragment();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		buildFragment();
	}

	private List<Player> getPlayers() {
		String teamId = getActivity().getIntent().getData().getLastPathSegment();
		List<Player> players = new LinkedList<Player>();
		
		PlayerDbHelper dbHelper = new PlayerDbHelper(getActivity());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String[] projection = {
				PlayerEntry._ID,
				PlayerEntry.COLUMN_FIRST_NAME,
				PlayerEntry.COLUMN_LAST_NAME,
				PlayerEntry.COLUMN_MIDDLE_NAME,
				PlayerEntry.COLUMN_DEFAULT_POSITION,
				PlayerEntry.COLUMN_PARENT_NAME1,
				PlayerEntry.COLUMN_PHONE_NUMBER1,
				PlayerEntry.COLUMN_EMAIL_ADDRESS1,
				PlayerEntry.COLUMN_PARENT_NAME2,
				PlayerEntry.COLUMN_PHONE_NUMBER2,
				PlayerEntry.COLUMN_EMAIL_ADDRESS2,
				PlayerEntry.COLUMN_JERSEY,
				PlayerEntry.COLUMN_TEAM_ID				
		};
		
		Cursor cursor = db.query(
				PlayerEntry.TABLE_NAME,
				projection,
				PlayerEntry.COLUMN_TEAM_ID + " = " + teamId,
				null,
				null,
				null,
				null
			);
		
		for(int i = 0; i < cursor.getColumnCount(); i++){
			if(!cursor.moveToNext()){
				break;
			}
			
			Player player = new Player();
			player.setId(cursor.getString(0));
			player.setFirstName(cursor.getString(1));
			player.setLastName(cursor.getString(2));
			player.setMiddleName(cursor.getString(3));
			player.setPosition(cursor.getString(4));
			player.setParentName1(cursor.getString(5));
			player.setParentPhone1(cursor.getString(6));
			player.setParentEmail1(cursor.getString(7));
			player.setParentName2(cursor.getString(8));
			player.setParentPhone2(cursor.getString(9));
			player.setParentEmail2(cursor.getString(10));
			player.setJersey(cursor.getString(11));
			player.setTeamId(cursor.getString(12));
			
			players.add(player);
			
		}
		
		return players;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
}
